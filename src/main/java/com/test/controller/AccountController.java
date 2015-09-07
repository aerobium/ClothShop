package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.Resource;
import com.test.entity.User;
import com.test.repository.PaymentRepository;
import com.test.repository.ResourceRepository;
import com.test.repository.UserRepository;
import com.test.service.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RAYANT on 13.05.2015.
 */


@Controller
public class AccountController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String main(ModelMap model) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.getUser(auth.getName());

        List<Resource> url = resourceRepository.listAll();
        for (Resource pictures : url) {
            pictures.setDescription(Coder.unCode(pictures.getDescription()));
        }

        List<Resource> usersOrder = new ArrayList<Resource>();
        for (Resource resource : url) {

            if (resource.getCostumerLogin() != null && !resource.getCostumerLogin().isEmpty() && resource.getCostumerLogin().equals(user.getLogin()))
                usersOrder.add(resource);
        }

        model.addAttribute("url", usersOrder);
        model.addAttribute("user", user);

        return "account";
    }

    @RequestMapping(value = "/accountEdit", method = RequestMethod.GET)
    public String editUser(ModelMap model) throws UnsupportedEncodingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.getUser(auth.getName());

        if (user.getAdress() == null) user.setAdress("");
        if (user.getEmail() == null) user.setEmail("");
        if (user.getFirstName() == null) user.setFirstName("");
        if (user.getLastName() == null) user.setLastName("");
        if (user.getTransport() == null) user.setTransport("");
        if (user.getBalance() == 0) user.setBalance(0);

        user.unCode();
        model.addAttribute("user", user);
        return "accountEdit";
    }

    @RequestMapping(value = "/accountEdit", method = RequestMethod.POST)
    public String update(ModelMap model, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("adress") String adress, @RequestParam("transport") String transport, @RequestParam("phone") String phone) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.getUser(auth.getName());
        user.unCode();
        if (firstName != null && !firstName.isEmpty()) user.setFirstName(firstName);
        if (lastName != null && !lastName.isEmpty()) user.setLastName(lastName);
        if (adress != null && !adress.isEmpty()) user.setAdress(adress);
        if (transport != null && !transport.isEmpty()) user.setTransport(transport);
        if (phone != null && !phone.isEmpty()) user.setPhone(phone);

        user.code();
        userRepository.updateUser(user);
        user.unCode();
        model.addAttribute("user", user);

        return "accountEdit";
    }


    @RequestMapping(value = "account/process", method = RequestMethod.POST)
    public String update(ModelMap model,
                         @RequestParam(value = "submit", required = false, defaultValue = "") String submit,
                         @RequestParam(value = "checkId", required = false, defaultValue = "") Long[] checkId) throws Exception {

        for (Long id : checkId) {
            Resource res = resourceRepository.getResource(id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.getUser(auth.getName());
            if (submit.equals("delete")) {
                res.setStatus(0);
                res.setCostumerLogin("");
                user.setBalance(user.getBalance()+res.getTotalcost());
            }
            if (submit.equals("toProcess")) res.setStatus(2);
            if (submit.equals("confirm")) res.setStatus(4);
            if (submit.equals("stash")) res.setStatus(5);
            resourceRepository.updateResource(res);
            userRepository.updateUser(user);

            paymentRepository.add(new Payment(user.getLogin(), "RETURN SKU: " + res.getSku(),
                    new Date(), res.getTotalcost()));
        }
        return "redirect:";
    }
}
