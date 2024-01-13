package com.example.petproject.controller;

import com.example.petproject.Service.productService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class productController {
    @Autowired
    productService productservice;
    @GetMapping("/shopping")
    public String showshopping(Model m){
        m.addAttribute("shopping",productservice.getproductAll());
        return "shopping";
    }

    @GetMapping("/shopping-single")
    public String showshopping_single(Model m,@RequestParam int id){
        m.addAttribute("shopping2",productservice.getproducdetail(id));
        return "shopping-single";
    }



}
