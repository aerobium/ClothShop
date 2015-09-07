package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.Resource;
import com.test.entity.User;
import com.test.repository.PaymentRepository;
import com.test.repository.ResourceRepository;
import com.test.repository.UserRepository;
import com.test.service.Coder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RAYANT on 13.05.2015.
 */

@Controller
public class AdminController {
    private final UserRepository userRepository = new UserRepository();

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String welcomeAdmin(ModelMap model) throws Exception {

        List<Resource> resources = this.resourceRepository.listAll();

        List<Resource> currentOrders = new ArrayList<Resource>();

        for (Resource resource : resources) {
            if (resource.getStatus() != 0) currentOrders.add(resource);
        }

        model.addAttribute("orders", currentOrders);
        return "admin";

    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String registr(@RequestParam(value = "j_type", required = false, defaultValue = "0") String type,
                          @RequestParam(value = "j_sku", required = false, defaultValue = "") String sku,
                          @RequestParam(value = "j_description", required = false, defaultValue = "") String description,
                          @RequestParam(value = "j_width", required = false, defaultValue = "0") String width,
                          @RequestParam(value = "j_length", required = false, defaultValue = "0") String length,
                          @RequestParam(value = "j_costpermetr", required = false, defaultValue = "0") String costpermetr,
                          @RequestParam(value = "j_totalcost", required = false, defaultValue = "10") String totalcost,
                          @RequestParam(value = "j_cost", required = false, defaultValue = "10") String cost,
                          @RequestParam(value = "j_profit", required = false, defaultValue = "10") String profit,
                          @RequestParam(value = "j_imgurl", required = false, defaultValue = "") String imgurl,
                          ModelMap model) throws Exception {


        if (!sku.isEmpty()) {
            Resource pic = new Resource();

            pic.setSku(Integer.parseInt(sku));
            pic.setType(Integer.valueOf(type));
            pic.setDescription(Coder.code(description));
            pic.setWidth(Integer.valueOf(width));
            pic.setLength(Integer.valueOf(length));
            pic.setCostpermetr(Integer.valueOf(costpermetr));
            pic.setTotalcost(Integer.valueOf(totalcost));
            pic.setCost(Integer.valueOf(cost));
            pic.setProfit(Integer.valueOf(profit));
            pic.setImgurl(imgurl);
            pic.setStatus(0);

            resourceRepository.addContact(pic);
        }


        List<User> users = this.userRepository.listAll();
        model.addAttribute("users", users);
        return "redirect:admin";

    }

    @RequestMapping(value = "admin/process", method = RequestMethod.POST)
    public String update(ModelMap model,
                         @RequestParam(value = "submit", required = false, defaultValue = "") String submit,
                         @RequestParam(value = "checkId", required = false, defaultValue = "") Long[] checkId) throws Exception {

        for (Long id : checkId) {
            Resource res = resourceRepository.getResource(id);
            if (submit.equals("delete")) {
                res.setStatus(0);
                try {
                        User user = userRepository.getUser(res.getCostumerLogin());
                        paymentRepository.add(new Payment(user.getLogin(), "ADMIN RETURN SKU: " + res.getSku(),
                                new Date(), res.getTotalcost()));
                        user.setBalance(user.getBalance() + res.getTotalcost());
                        res.setCostumerLogin("");
                        userRepository.updateUser(user);
                }catch (NullPointerException e){}
            }
            if (submit.equals("toPack")) res.setStatus(3);
            if (submit.equals("reservation")) res.setStatus(1);
            if (submit.equals("unpack")) res.setStatus(2);
            if (submit.equals("receive")) res.setStatus(4);
            if (submit.equals("archive")) res.setStatus(5);
            resourceRepository.updateResource(res);
        }
        return "redirect:";
    }
}
