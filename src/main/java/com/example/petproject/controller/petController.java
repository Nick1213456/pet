package com.example.petproject.controller;

import com.example.petproject.Service.petService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Controller
public class petController {
    @Autowired
    petService petService;
    @GetMapping("/petlist")
    public String showpetlist(Model m){
        m.addAttribute("pets",petService.getpetAll());

        return "petlist";
    }
}
