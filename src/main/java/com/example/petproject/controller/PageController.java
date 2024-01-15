package com.example.petproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
//    @GetMapping("/index")
//    public String showindex(){
//        return "index";
//    }

//    @GetMapping("/memberpage")
//    public String showmemberpage(){
//        return "memberpage";
//    }


//    @GetMapping("/backstage")
//    public String showbackstage(){
//        return "backstage";
//    }

    @GetMapping("/checkout")
    public String showcheckout(){
        return "checkout";
    }



}
