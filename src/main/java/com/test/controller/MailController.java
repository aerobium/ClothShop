package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Massage;
import com.test.entity.User;
import com.test.repository.MassageRepository;
import com.test.repository.UserRepository;
import com.test.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by RAYANT on 22.05.2015.
 */
@Controller
public class MailController {

    @Autowired
    private MassageRepository mailRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String hello(Model model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUser(auth.getName());
        if (!user.getIsAdmin().equals("0")) user.setId(0);
        if (!user.getIsAdmin().equals("0")) user.setLogin("admin");
        List<Massage> massages = this.mailRepository.getMassagesRecive(user.getLogin());

        ArrayList<Massage> visible = new ArrayList<Massage>();
        ArrayList<Massage> readed = new ArrayList<Massage>();

        for (Massage mas : massages) {
            mas.setText(new String(mas.getText().getBytes(), "UTF-8"));
            if (mas.getVisibility() != 0) visible.add(mas);
            else readed.add(mas);
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
        model.addAttribute("massages", visible);
        model.addAttribute("readed", readed);


        return "mail";
    }


    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public String update(ModelMap model,
                         @RequestParam(value = "text", required = false, defaultValue = "") String text,
                         @RequestParam(value = "recipients", required = false, defaultValue = "") String recipient,
                         @RequestParam(value = "visibility", required = false, defaultValue = "") String visibility,
                         @RequestParam(value = "loginToSend", required = false, defaultValue = "") String loginToSend,
                         @RequestParam(value = "deleted", required = false, defaultValue = "") String deleted,
                         @RequestParam(value = "place", required = false, defaultValue = "0") String place) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUser(auth.getName());
        if (!user.getIsAdmin().equals("0")) user.setId(0);
        if (!user.getIsAdmin().equals("0")) user.setLogin("admin");


        Massage massage = new Massage();
        massage.setAuthorId(user.getId());
        massage.setAuthor(user.getLogin());
        massage.setDate(new Date());
        if (place.isEmpty() || place == null) massage.setPlace(0);
        else massage.setPlace(Integer.parseInt(place));

        massage.setText(text);
        massage.setVisibility(1);
        if (!massage.getText().isEmpty()) {

            HashSet<String> massagesId = Services.parser(recipient);
            HashSet<String> recipients = new HashSet<String>();

            for (String s : massagesId) {
                Massage forSend = mailRepository.getMassage(Integer.parseInt(s));
                recipients.add(forSend.getAuthor());
            }

            if (!loginToSend.isEmpty()) {
                if (!userRepository.getUser(loginToSend).getIsAdmin().equals("0")) loginToSend = "admin";
                recipients.add(loginToSend);

            }


            if (!recipients.isEmpty()) {
                for (String s : recipients) {

                    massage.setRecipient(s);
                    mailRepository.addMassage(massage);
                }
            }

        }

        if (!visibility.isEmpty()) {
            HashSet<String> notVisible = Services.parser(visibility);

            for (String s : notVisible) {

                Massage forUpdate = mailRepository.getMassage(Integer.parseInt(s));
                forUpdate.setVisibility(0);
                mailRepository.updateMassage(forUpdate);
            }

        }

        if (!deleted.isEmpty()) {
            HashSet<String> toDelete = Services.parser(deleted);

            for (String s : toDelete) {
                Massage forDelete = mailRepository.getMassage(Integer.parseInt(s));
                mailRepository.removeMassage(forDelete);
            }

        }

        List<Massage> massages = mailRepository.getMassagesRecive(user.getLogin());

        ArrayList<Massage> visible = new ArrayList<Massage>();
        ArrayList<Massage> readed = new ArrayList<Massage>();

        for (Massage mas : massages) {
            mas.setText(new String(mas.getText().getBytes(), "UTF-8"));
            if (mas.getVisibility() != 0) visible.add(mas);
            else readed.add(mas);
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
        model.addAttribute("massages", visible);
        model.addAttribute("readed", readed);
        return "mail";
    }


}
