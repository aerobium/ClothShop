package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Optical Illusion on 05.09.2015.
 */
@Controller

public class InfoController {

    @RequestMapping(value="/about",  method = RequestMethod.GET)
    public String aboutPage(Model model){
        return "about";
    }

    @RequestMapping(value="/faq",  method = RequestMethod.GET)
    public String faqPage(Model model){
        return "faq";
    }

}
