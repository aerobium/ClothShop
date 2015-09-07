package com.test.controller;

/**
 * Created by RAYANT on 26.04.2015.
 */

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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;


//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String main(Model model) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        User user = userRepository.getUser(auth.getName());
//        model.addAttribute("user", user);
//
//        List<Resource> url = resourceRepository.listAll();
//        for (Resource pictures : url) {
//            pictures.setDescription(Coder.unCode(pictures.getDescription()));
//        }
//
//        model.addAttribute("url", url);
//
//        return "main";
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String post(ModelMap model,
                       @RequestParam(value = "idResource", required = false, defaultValue = "") String idResource) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUser(auth.getName());

        if (!idResource.isEmpty()) {
            Resource resource = resourceRepository.getResource(Long.parseLong(idResource));
            if (resource.getStatus() == 0 && resource.getTotalcost() <= user.getBalance()) {
                user.setBalance(user.getBalance() - resource.getTotalcost());
                resource.setCostumerLogin(auth.getName());
                resource.setStatus(1);
                resourceRepository.updateResource(resource);
                userRepository.updateUser(user);

                paymentRepository.add(new Payment(user.getLogin(), "SKU: " + resource.getSku(),
                        new Date(), -resource.getTotalcost()));
            }

        }

        List<Resource> url = resourceRepository.listAll();
        model.addAttribute("url", url);
        model.addAttribute("user", user);
        return "redirect:";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainContent(ModelMap model,
                              @RequestParam(value = "listParams", required = false, defaultValue = "") final HashSet<String> listParams) throws Exception {
        System.out.println(listParams);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.getUser(auth.getName());

        List<Resource> url = resourceRepository.listAll();

        for (Resource pictures : url) {
            pictures.setDescription(Coder.unCode(pictures.getDescription()));
        }

        if (!listParams.isEmpty()) {
            for (Resource resource : url) {
                if (!listParams.contains(String.valueOf(resource.getType())))
                    resource.setStatus(-1);
            }
        }

        model.addAttribute("url", url);
        model.addAttribute("user", user);
        return "main";
    }


}
