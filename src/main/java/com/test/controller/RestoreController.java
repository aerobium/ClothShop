package com.test.controller;

import com.test.entity.User;
import com.test.repository.UserRepository;
import com.test.service.Sender;
import com.test.service.Services;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by RAYANT on 17.05.2015.
 */
@Controller
@RequestMapping("/restore")
public class RestoreController {

    UserRepository userRepository = new UserRepository();

    Sender sender = new Sender("info.loskut@gmail.com", "afcvyvopqfeztpdg"); //Application password for Google mail

    @RequestMapping(method = RequestMethod.GET)
    public String Page(Model model) throws Exception{
        return "restore";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registr(@RequestParam("j_email") String name) throws Exception {

        User user = null;

        if (userRepository.getUser(name)!=null) {
            user = userRepository.getUser(name);
        }
            else
            if (userRepository.getUserByEmail(name)!=null) user = userRepository.getUserByEmail(name);
                else return "restoreFails";

        String password = Services.getRandomPassword();
        user.setPassword(Services.sha1(password));
        userRepository.updateUser(user);

        String s1 = new String("Восстановление пароля".getBytes("UTF-8"),"UTF-8");
        String s2 = new String("Ваш новый пароль: ".getBytes("UTF-8"),"UTF-8");
        sender.send(s1,s2+password, "informer@test-tkani.com", user.getEmail());

        return "login";
    }
}
