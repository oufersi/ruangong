package com.example.forum.Controllers;

import com.example.forum.Entities.NormalUser;
import com.example.forum.Exceptions.UserNotFoundException;
import com.example.forum.Repositories.UserRepository;
import com.example.forum.Assemblers.UserModelAssembler;
import com.example.forum.Entities.UserFactory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Controller
public class UserController {
    private final UserRepository repository;

    private final UserModelAssembler assembler;

    private final UserFactory userFactory;

    private NormalUser curUser;

    UserController(UserRepository repository, UserModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
        this.userFactory = new UserFactory();
    }

    @GetMapping("/users")
    public @ResponseBody CollectionModel<EntityModel<NormalUser>> all(){
        List<EntityModel<NormalUser>> users = repository.findAll().stream()
            .map(assembler :: toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
            linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<NormalUser> one(@PathVariable Long id){

        NormalUser user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        
        System.out.print("user id : " + id);

        return assembler.toModel(user);
    }

    @RequestMapping("/login.go")
    public String loginGo(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(name + " " + password);
        List<NormalUser> users = repository.findByName(name);
        for (NormalUser user : users) {
            System.out.println(user.getName() + user.getPassword());
            if(user.getPassword().equals(password)){
                System.out.println("login succeed by" + user.getName());
                curUser = userFactory.createUser(name);
                model.addAttribute("name", name);
                if(user.getInformStatus()){
                    model.addAttribute("informMessage", "自上次登录有文章更新");
                }
                else{
                    model.addAttribute("informMessage", "自上次登录无文章更新");
                }
                user.infoChecked();
                return "loginSuccess";
            }
        }
        return "loginFail";
    }

    @RequestMapping("/register.go")
    public String registerGo(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        System.out.println("receive user : " + name + " " + password + " " + confirmPassword);

        List<NormalUser> users = repository.findByName(name);
        if(users.size() != 0 || !password.equals(confirmPassword)){
            return "registerFail";
        }
        repository.save(userFactory.createUser(name, password));
        System.out.println("add new user : " + name);
        return "registerSucceed";
    }

    @RequestMapping("/newPassage.go")
    public String newPassageGo(){
        return "newPassageGo";
    }

    @RequestMapping("/turnToPC")
    public String turnToPC(HttpServletRequest request, RedirectAttributes attr){
        attr.addAttribute("title", request.getParameter("title"));
        attr.addAttribute("content", request.getParameter("content"));
        attr.addAttribute("author", curUser.getName());

        List<NormalUser> users = repository.findAll();
        for(NormalUser user : users){
            user.getInformed();
            repository.save(user);
            System.out.println("user : " + user.getName() + " get informed");
        }

        return "redirect:/newPassage";
    }
}
