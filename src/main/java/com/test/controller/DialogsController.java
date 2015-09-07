package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Massage;
import com.test.entity.User;
import com.test.repository.MassageRepository;
import com.test.repository.UserRepository;
import com.test.service.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by RAYANT on 04.06.2015.
 */

@Controller
public class DialogsController {
    @Autowired
    private MassageRepository mailRepository;
    @Autowired
    private UserRepository userRepository;

    private String dialogWith;

    @RequestMapping(value = "/dialogs", method = RequestMethod.GET)
    public String loginPage(Model model,
                            @RequestParam(value = "dialogWith", required = false, defaultValue = "") String dialogWith) throws Exception {

        model.addAttribute("dialogWith", dialogWith);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUser(auth.getName());
        if (!user.getIsAdmin().equals("0")) {
            user.setId(0);
            user.setLogin("admin");
            this.dialogWith = dialogWith;
        }
        if (!dialogWith.isEmpty()&&!dialogWith.equals("admin"))  user = userRepository.getUser(dialogWith);


        TreeSet<Massage> userMassages = new TreeSet<Massage>();
        List<Massage> post = mailRepository.getMassagesPost(user.getLogin());
        List<Massage> recive = mailRepository.getMassagesRecive(user.getLogin());
        userMassages.addAll(post);
        userMassages.addAll(recive);
        for (Massage massage : userMassages) {
            massage.setText(Coder.unCode(massage.getText()));
        }


        model.addAttribute("userMassages", userMassages);

        List<User> users = this.userRepository.listAll();

        List<String> login = new ArrayList<String>();
        for (User u : users) {
            login.add(u.getLogin());
        }

        StringWriter loginJson = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(loginJson, login);

        model.addAttribute("login", loginJson);
        return "dialogs";
    }

    @RequestMapping(value = "/dialogs", method = RequestMethod.POST)
    public String update(ModelMap model,
                         @RequestParam(value = "text", required = false, defaultValue = "") String text,
                         @RequestParam(value = "delete", required = false, defaultValue = "") String delete,
                         @RequestParam(value = "loginToSend", required = false, defaultValue = "admin") String loginToSend) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.getUser(auth.getName());
        if (!user.getIsAdmin().equals("0")) user.setId(0);
        if (!user.getIsAdmin().equals("0")) user.setLogin("admin");


        List<Massage> post = mailRepository.getMassagesPost(user.getLogin());
        if (!post.isEmpty()) {
            Massage last = post.get(post.size() - 1);
            if (text.equals(Coder.unCode(last.getText()))) text ="";
        }

        if (!text.isEmpty() && !loginToSend.isEmpty()) {

            Massage massage = new Massage();
            massage.setAuthorId(user.getId());
            massage.setAuthor(user.getLogin());
            massage.setDate(new Date());
            massage.setPlace(0);
            massage.setText(Coder.code(text));
            massage.setVisibility(1);
            massage.setRecipient(loginToSend);
            mailRepository.addMassage(massage);

        }


        List<User> users = this.userRepository.listAll();

        List<String> login = new ArrayList<String>();
        for (User u : users) {
            login.add(u.getLogin());
        }

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, login);

        model.addAttribute("login", writer);
        String userToSend;
        if (loginToSend.equals("admin"))
            userToSend = user.getLogin();    // не Админ
        else {
            userToSend = loginToSend;
            this.dialogWith = loginToSend;   // Админ
        }

        model.addAttribute("dialogWith", loginToSend);


        if(delete.equals("delete")){
            TreeSet<Massage> userMassages = new TreeSet<Massage>();
            post = mailRepository.getMassagesPost(userToSend);
            List<Massage> recive = mailRepository.getMassagesRecive(userToSend);
            userMassages.addAll(post);
            userMassages.addAll(recive);
            mailRepository.removeMassages(userMassages);
        }
        if(delete.equals("hide")){
            TreeSet<Massage> userMassages = new TreeSet<Massage>();
            post = mailRepository.getMassagesPost(userToSend);
            List<Massage> recive = mailRepository.getMassagesRecive(userToSend);
            userMassages.addAll(post);
            userMassages.addAll(recive);
            for (Massage massage : userMassages) {
                massage.setVisibility(2);
            }
            mailRepository.updateMassages(userMassages);
        }

        TreeSet<Massage> userMassages = new TreeSet<Massage>();
        post = mailRepository.getMassagesPost(userToSend);
        List<Massage> recive = mailRepository.getMassagesRecive(userToSend);
        userMassages.addAll(post);
        userMassages.addAll(recive);

        for (Massage mas : userMassages) {
            mas.setText(Coder.unCode(mas.getText()));
        }

        model.addAttribute("userMassages", userMassages);

        return "redirect:dialogs";
    }

    @RequestMapping(value = "/ajaxDialogs")
    public @ResponseBody
    TreeSet<Massage> getDialogs() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUser(auth.getName());
        if (!user.getIsAdmin().equals("0"))  user = userRepository.getUser(dialogWith);

        TreeSet<Massage> userMassages = new TreeSet<Massage>();
        List<Massage> post = mailRepository.getMassagesPost(user.getLogin());
        List<Massage> recive = mailRepository.getMassagesRecive(user.getLogin());
        userMassages.addAll(post);
        userMassages.addAll(recive);
        for (Massage massage : userMassages) {
            massage.setText(Coder.unCode(massage.getText()));
        }

        return userMassages;
    }



}
