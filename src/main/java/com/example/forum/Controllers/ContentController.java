package com.example.forum.controllers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.forum.assemblers.PostContentAssembler;
import com.example.forum.dao.NormalUserRepository;
import com.example.forum.dao.PostContentRepository;
import com.example.forum.entities.*;

@Controller
@RequestMapping("/content")
public class ContentController {
    private final NormalUserRepository nUserRepository;
    private final PostContentRepository postContentRepository;
    private final PostContentAssembler postContentAssembler;

    private int numEachPage;

    public ContentController(NormalUserRepository nUserRepository,
                             PostContentRepository postContentRepository,
                             PostContentAssembler assembler){
        
        this.nUserRepository = nUserRepository;
        this.postContentRepository = postContentRepository;
        this.postContentAssembler = assembler;

        this.numEachPage = 2;
    }

    @RequestMapping("/posts")
    public String all(HttpServletRequest request, Model model){
        int start = Integer.parseInt(request.getParameter("start"));
        int len = Integer.parseInt(request.getParameter("len"));
        List<EntityModel<PostContent>> passages = postContentRepository.findAll().stream()
            .map(postContentAssembler :: toModel)
                .collect(Collectors.toList());
        System.out.println("receive params start : " + start + " len : " + len);
        model.addAttribute("start", start);

        for(int i = 0; start + i < passages.size() && i < len; i++){
            model.addAttribute("title", passages.get(start + i).getContent().getTitle());
            model.addAttribute("passage", passages.get(start + i).getContent().getContent());
            model.addAttribute("authorId", passages.get(start + i).getContent().getAuthorId());
            model.addAttribute("author", passages.get(start + i).getContent().getAuthor());
            model.addAttribute("num", passages.size());
            System.out.println("get passage " + passages.get(start + i).getContent().getTitle());
        }
        return "content/postsPage";
    }

    @RequestMapping("/newPost")
    public String newPost(HttpServletRequest request){
        HttpSession session = request.getSession();
        
        String name = (String)session.getAttribute("name");
        if(name != null){
            return "/content/newPostPage";
        }
        return "/user/loginPage";
    }
    
    @RequestMapping("/addNewPost")
    public String addNewPassage(HttpServletRequest request, RedirectAttributes attr){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = request.getParameter("author");

        System.out.println("recerve param title : " + title + " content " + content + " author : " + author);

        NormalUser user = new NormalUser(author);
        postContentRepository.save(new PostContent(title, content, user));

        attr.addAttribute("postsUrl", "/content/posts?start=0");
        attr.addAttribute("pageNum", 1);
        
        return "redirect:/content/posts";
    }
}
