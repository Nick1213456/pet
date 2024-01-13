package com.example.petproject.controller;


import com.example.petproject.Service.cartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class caetController {
    @Autowired
    cartService cartService;
    @GetMapping("/shoppingcart")
    public String showshoppingcart(Model m, HttpSession httpsession){

        if ((String)httpsession.getAttribute("username")==null){
            return "index";
        }else {
            m.addAttribute("carts",cartService.getcartfrom_username((String)httpsession.getAttribute("username")));
            return "shoppingcart";
        }
    }

    @PostMapping("/joincart")
    public String joincart(@RequestParam int id, int num,HttpSession httpsession){
        cartService.joincart(id,num,(String)httpsession.getAttribute("username"));
        return "index";
    }

}
