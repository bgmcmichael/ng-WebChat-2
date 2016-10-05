package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenji on 9/26/2016.
 */
@Controller
public class ViewController {
    @Autowired
    MessageRepository messages;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model){
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        List<Message> messageList = new ArrayList<Message>();
        User savedUser = (User)session.getAttribute("user");
        if (savedUser != null) {
            Iterable<Message> messageIterater = messages.findAll();
            for (Message message: messageIterater){
                messageList.add(message);
            }
        }


        model.addAttribute("messages", messageList);
        return "home";
    }
}
