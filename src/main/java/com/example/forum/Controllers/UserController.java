package com.example.forum.controllers;

import com.example.forum.assemblers.NormalUserAssembler;
import com.example.forum.dao.NormalUserRepository;
import com.example.forum.entities.NormalUser;
import com.example.forum.entities.UserFactory;
import com.example.forum.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final NormalUserRepository nUserRepository;

    private final NormalUserAssembler nUserAssembler;

    private final UserFactory userFactory;

    UserController(NormalUserRepository repository, NormalUserAssembler assembler){
        this.nUserRepository = repository;
        this.nUserAssembler = assembler;
        this.userFactory = new UserFactory();
    }

    @GetMapping("/users")
    public @ResponseBody CollectionModel<EntityModel<NormalUser>> all(){
        List<EntityModel<NormalUser>> users = nUserRepository.findAll().stream()
            .map(nUserAssembler :: toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
            linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<NormalUser> one(@PathVariable Long id){

        NormalUser user = nUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        
        System.out.print("user id : " + id);

        return nUserAssembler.toModel(user);
    }

    /*
     * entrance request of WebApp
     * require login if there is no login information in request session, otherwise turn to the welcomePage
     */
    @RequestMapping("/mainPage")
    public String loginGo(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();

        String name = (String)session.getAttribute("username");
        Long uid = (Long)session.getAttribute("uid");
        session.setMaxInactiveInterval(30 * 60);
        System.out.println("receive session with name:" + name);

        if(name != null){
            System.out.println("login succeed by" + name);
            
            Optional<NormalUser> op = nUserRepository.findById(uid);
            NormalUser user;

            if(!op.isPresent()){
                return "/error/errorPage";
            }
            user = op.get();

            model.addAttribute("name", name);
            if(user.getInformStatus()){
                model.addAttribute("informMessage", "自上次登录有文章更新");
            }
            else{
                model.addAttribute("informMessage", "自上次登录无文章更新");
            }
            user.infoChecked();
            // problem ?
            nUserRepository.save(user);

            return "user/welcomePage";
        }

        return "user/loginPage";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(name + " " + password);

        List<NormalUser> users = nUserRepository.findByName(name);
        for (NormalUser user : users) {
            System.out.println(user.getName() + user.getPassword());
            if(user.getPassword().equals(password)){
                System.out.println("login succeed by" + user.getName());
                
                session.setAttribute("username", name);
                // session.setAttribute("password", password);
                session.setAttribute("uid", user.getId());
                session.setMaxInactiveInterval(30 * 60);
                System.out.println("create session " + session.getId());

                model.addAttribute("name", name);
                if(user.getInformStatus()){
                    model.addAttribute("informMessage", "自上次登录有文章更新");
                }
                else{
                    model.addAttribute("informMessage", "自上次登录无文章更新");
                }
                user.infoChecked();

                return "user/welcomePage";
            }
        }
        return "user/loginFailPage";
    }

    @RequestMapping("/administrater_login")
    public String administraterLogin(HttpServletRequest request, Model model){
        return "user/admLoginPage";
    }

    @RequestMapping("/register.go")
    public String registerGo(){
        return "user/registerPage";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        System.out.println("receive user : " + name + " " + password + " " + confirmPassword);

        List<NormalUser> users = nUserRepository.findByName(name);
        if(users.size() != 0 || !password.equals(confirmPassword)){
            return "registerFailPage";
        }
        nUserRepository.save(userFactory.createUser(name, password));
        System.out.println("add new user : " + name);
        return "user/registerSuccessPage";
    }

    @RequestMapping("/searchUser")
    public void searchUser(HttpServletRequest request){
        
    }

    @RequestMapping("/getUserPage")
    public ModelAndView getUserPage(HttpServletRequest request){
        ModelAndView MV = new ModelAndView("user/userPage");

        Long uid = Long.parseLong(request.getParameter("uid"));
        String messageUrl = "message/newMsg" + uid;

        MV.addObject("uid", uid);
        MV.addObject("messageUrl", messageUrl);

        return MV;
    }
}
