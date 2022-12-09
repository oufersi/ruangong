package com.example.forum.controllers;

import com.example.forum.dao.MessageRepository;
import com.example.forum.dao.NormalUserRepository;
import com.example.forum.entities.Message;
import com.example.forum.entities.NormalUser;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
    private MessageRepository messageRepository;
    private NormalUserRepository normalUserRepository;

    @RequestMapping("/newMsg")
    public String newMsg(HttpServletRequest request, Model model){
        Long senderId = Long.parseLong(request.getParameter("senderId"));
        Long receiverId = Long.parseLong(request.getParameter("receiverId"));
        String receiverName = request.getParameter("receiverName");

        String targetUrl = "/message/sendMsg?senderId=" + senderId + "&receiverId=" + receiverId + "&receiverName=" + receiverName;
        
        model.addAttribute("receiverId", receiverId);
        model.addAttribute("name", receiverName);
        model.addAttribute("targetUrl", targetUrl);
        return "/message/newMessagePage";
    }

    @RequestMapping("/sendMsg")
    public String sendMsg(HttpServletRequest request, Model model){
        String content = (String)request.getAttribute("content");
        Long senderId = (Long)request.getAttribute("senderId");
        Long receiverId = (Long)request.getAttribute("recerverId");

        Message message = new Message(content, senderId);
        message = messageRepository.save(message);

        Optional<NormalUser> optional = normalUserRepository.findById(receiverId);
        if(optional.isPresent()){
            NormalUser receiver = optional.get();
            receiver.receiveMsg(message.getId());
            normalUserRepository.save(receiver);

            return "/message/sendSuccessPage";
        }

        return "/message/sendFailPage";
    }
}
