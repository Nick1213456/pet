package com.example.petproject.controller;


import com.example.petproject.Service.cartService;
import com.example.petproject.Service.petService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class cartController {
    @Autowired
    cartService cartService;
    @Autowired
    petService petService;
    @PostMapping("/cart")
    public String goCart(Model m, @RequestBody String id, HttpSession httpSession){
        httpSession.setAttribute("username","3");
        String[] parts = id.split("=");
        String idValue = parts[1]; //轉int
        cartService.getcartid(Integer.parseInt(idValue),Integer.parseInt((String) httpSession.getAttribute("username")));
        m.addAttribute("allCart", cartService.getAllcart());
        return "shoppingCart";
    }

    @GetMapping("/cart")
    public String seeAll(Model m){
        m.addAttribute("allCart", cartService.getAllcart());
        return "shoppingCart";
    }

    @PostMapping("/ALLdelete")
    public String ALLdelete(@RequestParam String plusone, int ordernum){
        cartService.aadelete(plusone,ordernum);
        return "shoppingCart";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam int plusone,int ordernum){

        cartService.bbdelete(plusone,ordernum);
        return "shoppingCart";
    }


    @PostMapping("/plus")
    public String plusone(@RequestParam int plus,int ordernum){
        System.out.println(ordernum);
        cartService.plus(plus,ordernum);
        System.out.println("PLUS");
        return "shoppingCart";
    }

    @PostMapping("/deleteAll")
    public String deleteaAllList(HttpSession httpSession){
        httpSession.setAttribute("username","4");
        cartService.ccdelete(Integer.parseInt((String) httpSession.getAttribute("username")));

        return "shoppingCart";
    }
}
