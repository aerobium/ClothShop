package com.test.controller;

import com.test.entity.Resource;
import com.test.entity.User;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by Optical Illusion on 22.06.2015.
 */
@Controller
public class EditorController {
    private final UserRepository userRepository = new UserRepository();

    @Autowired
    private ResourceRepository resourceRepository;

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public String productEditor(ModelMap model) throws Exception {

        List<Resource> resources = this.resourceRepository.listAll();
        for (Resource resource : resources) {
            resource.setDescription(Coder.unCode(resource.getDescription()));
        }
        model.addAttribute("orders", resources);
        return "editor";
    }

    @RequestMapping(value = "/editor", method = RequestMethod.POST)
    public String registr(@RequestParam(value = "j_id", required = false, defaultValue = "") Long id,
                          @RequestParam(value = "j_type", required = false, defaultValue = "") String type,
                          @RequestParam(value = "j_sku", required = false, defaultValue = "") String sku,
                          @RequestParam(value = "j_description", required = false, defaultValue = "") String description,
                          @RequestParam(value = "j_width", required = false, defaultValue = "") String width,
                          @RequestParam(value = "j_length", required = false, defaultValue = "") String length,
                          @RequestParam(value = "j_costpermetr", required = false, defaultValue = "") String costpermetr,
                          @RequestParam(value = "j_totalcost", required = false, defaultValue = "") String totalcost,
                          @RequestParam(value = "j_cost", required = false, defaultValue = "") String cost,
                          @RequestParam(value = "j_profit", required = false, defaultValue = "") String profit,
                          @RequestParam(value = "j_imgurl", required = false, defaultValue = "") String imgurl,
                          @RequestParam(value = "j_status", required = false, defaultValue = "") String status,
                          @RequestParam(value = "j_user", required = false, defaultValue = "") String user,
                          ModelMap model) throws Exception {
        System.out.println(length);

        Resource pic = resourceRepository.getResource(id);
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
        pic.setStatus(Integer.valueOf(status));
        pic.setCostumerLogin(user);
        resourceRepository.updateResource(pic);

        return "redirect:editor";
    }

    @RequestMapping(value = "/editor/new", method = RequestMethod.POST)
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
                          @RequestParam(value = "j_status", required = false, defaultValue = "") String status,
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
            pic.setStatus(Integer.valueOf(status));

            resourceRepository.addContact(pic);
        }
        return "redirect:";
    }


    @RequestMapping(value = "editor/delete", method = RequestMethod.POST)
    public String update(ModelMap model,
                         @RequestParam(value = "checkId", required = false, defaultValue = "") Long[] checkId) throws Exception {
        for (Long id : checkId) {
            Resource res = resourceRepository.getResource(id);
            resourceRepository.deleteResource(res);
        }
        return "redirect:";
    }

}
