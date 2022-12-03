package com.example.forum.Controllers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.forum.Assemblers.PassageAssembler;
import com.example.forum.Entities.*;
import com.example.forum.Repositories.PassageRepository;

@Controller
public class PassageController {
    private final PassageRepository repository;

    private final PassageAssembler assembler;

    public PassageController(PassageRepository repository, PassageAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @RequestMapping("/passages")
    public String all(HttpServletRequest request, Model model){
        int start = Integer.parseInt(request.getParameter("start"));
        int len = Integer.parseInt(request.getParameter("len"));
        List<EntityModel<Passage>> passages = repository.findAll().stream()
            .map(assembler :: toModel)
                .collect(Collectors.toList());
        System.out.println("receive params start : " + start + " len : " + len);
        model.addAttribute("start", start);

        for(int i = 0; start + i < passages.size() && i < len; i++){
            model.addAttribute("title", passages.get(start + i).getContent().getTitle());
            model.addAttribute("passage", passages.get(start + i).getContent().getContent());
            model.addAttribute("authorId", passages.get(start + i).getContent().getAuthorId());
            model.addAttribute("author", passages.get(start + i).getContent().getAuthor());
            model.addAttribute("num", passages.size());
            System.out.println("g`et passage " + passages.get(start + i).getContent().getTitle());
        }
        return "passages";
    }
    
    @RequestMapping("/newPassage")
    public String newPassage(HttpServletRequest request, RedirectAttributes attr){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        System.out.println("recerve param title : " + title + " content " + content + " author : " + author);

        NormalUser user = new NormalUser(author);
        repository.save(new Passage(title, content, user));

        attr.addAttribute("start", 0);
        attr.addAttribute("len", 1);
        
        return "redirect:/passages";
    }
}
