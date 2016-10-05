package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenji on 9/26/2016.
 */
@RestController
public class ChatRestController {
    @Autowired
    MessageRepository messages;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/login.json", method = RequestMethod.POST)
    public User login(HttpSession session, @RequestBody User userLogin) throws Exception{
        User user = null;
//        String name = (String)session.getAttribute("name");
//        String password = (String)session.getAttribute("password");

        if (users.findByNameAndPassword(userLogin.name, userLogin.password) != null) {
            user = users.findByNameAndPassword(userLogin.name, userLogin.password);
        } else {
            user = new User(userLogin.name, userLogin.password);
            users.save(user);
        }
        return user;
    }

    @RequestMapping(path = "/messages.json", method = RequestMethod.GET)
    public ArrayList<Message> getMessages() throws Exception{
        ArrayList<Message> messageList = new ArrayList<Message>();
        Iterable<Message> allmessages = messages.findAll();
        for (Message message : allmessages) {
            messageList.add(message);
        }
        return messageList;
    }

    @RequestMapping(path = "/addMessage.json", method = RequestMethod.POST)
    public List<Message> addMessage(HttpSession session, @RequestBody Message message) throws Exception {
        messages.save(message);

        return getMessages();
    }
}
