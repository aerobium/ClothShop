package com.test.controller;

/**
 * Created by RAYANT on 26.04.2015.
 */

import com.test.entity.Resource;
import com.test.entity.User;
import com.test.repository.UserRepository;
import com.test.service.Coder;
import com.test.service.Services;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    UserRepository userRepository = new UserRepository();


    @RequestMapping(method = RequestMethod.GET)
    public String Page(Model model) throws Exception {
        return "registration";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String registr(@RequestParam(value = "releFio", required = false, defaultValue = "") String releFio,
                          @RequestParam(value = "releEmail", required = false, defaultValue = "") String releEmail,
                          @RequestParam("j_username") String name,
                          @RequestParam("j_password") String password,
                          @RequestParam("j_email") String email) throws Exception {
        if (releFio.isEmpty() || releEmail.isEmpty()) {
            return "redirect:/";
        }
//        if (userRepository.getUser(name) != null
//                || userRepository.getUserByEmail(name) != null
//                || name.isEmpty()
//                || name.contains(" ")
//                || password.isEmpty()
//                || password.contains(" ")
//                || email.isEmpty()
//                || email.contains(" "))
//            return "registrationFails";

        User user = new User();

        user.setLogin(name);
        user.setPassword(Services.sha1(password));
        user.setEmail(email);
        user.setIsAdmin("0");
        user.setBalance(1000000);


        userRepository.addContact(user);

        return "redirect:/";
    }


    @RequestMapping(value = "/ajaxCheckUnique")
    @ResponseBody
    public String main(@RequestBody String input) throws Exception {
        input = input.split("=")[0];
        System.out.println(input);
        System.out.println("*****");
        try {
            List<User> userList = userRepository.listAll();
            for (User user : userList) {
                if (user.getLogin() != null && user.getLogin().equals(input) || user.getEmail() != null && user.getEmail().equals(input)) {
                    return "off";
                }
            }

        } catch (Exception e) {
            System.out.println("Нет пользователей");
            e.printStackTrace();
        }
        return "on";
    }
}