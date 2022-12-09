package com.example.forum.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("uidHref", "/test/paramTest?uid=12345");
        return "/test/testTarget";
    }

    @RequestMapping("/paramTest")
    public ModelAndView paramTest(HttpServletRequest request){
        ModelAndView MV = new ModelAndView("/test/paramResult");
        
        Long id =Long.parseLong(request.getParameter("uid"));

        MV.addObject("uid", id);

        return MV;
    }

    @PostMapping("/jsTest")
    @ResponseBody
    public Object jsTest(HttpServletRequest request){
        String name = request.getParameter("username");

        System.out.println(name);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "hello");

        return map;
    }
}
