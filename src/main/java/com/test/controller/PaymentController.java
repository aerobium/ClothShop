package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Payment;
import com.test.entity.User;
import com.test.repository.PaymentRepository;
import com.test.repository.UserRepository;
import com.test.service.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RAYANT on 06.07.2015.
 */

@Controller
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/admin/payment", method = RequestMethod.GET)
    public String main(Model model) throws Exception {

        List<Payment> payments = paymentRepository.listAll();
        for (Payment payment : payments) payment.setComment(Coder.unCode(payment.getComment()));
        model.addAttribute("payments", payments);

        List<User> users = this.userRepository.listAll();
        List<String> login = new ArrayList<String>();
        for (User u : users) login.add(u.getLogin());
        StringWriter loginJson = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(loginJson, login);
        model.addAttribute("login", loginJson);

        return "payment";
    }

    @RequestMapping(value = "/admin/payment", method = RequestMethod.POST)
    public String update(Model model,
                         @RequestParam(value = "login", required = false, defaultValue = "") String login,
                         @RequestParam(value = "comment", required = false, defaultValue = "") String comment,
                         @RequestParam(value = "amount", required = false, defaultValue = "") String amount) throws Exception {

        if (!login.isEmpty() && !amount.isEmpty()) {
            User user = userRepository.getUser(login);
            String text = Coder.code(comment);
            long newBalance = user.getBalance();
            if (amount.contains("$")) newBalance = Long.parseLong(amount.substring(1))*100;
            else newBalance += Long.parseLong(amount)*100;
            paymentRepository.add(new Payment(login, text, new Date(), newBalance - user.getBalance()));
            user.setBalance(newBalance);
            userRepository.updateUser(user);
        }

        return "redirect:payment";
    }

   

}
