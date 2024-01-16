package com.example.petproject.controller;


import com.example.petproject.Service.cartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class caetController {
    @Autowired
    cartService cartService;
    @GetMapping("/shoppingcart")
    public String showshoppingcart(Model m, HttpSession httpsession){

        if (httpsession.getAttribute("username")==null){

            return "/failed";
        }else {
            m.addAttribute("carts",cartService.getcartfrom_username((String)httpsession.getAttribute("username")));
            return "shoppingcart";
        }
    }

    @PostMapping("/joincart")
    @ResponseBody
    public String joincart(@RequestParam int id, int num,HttpSession httpsession){
        if ((String)httpsession.getAttribute("username")!=null){
            cartService.joincart(id,num,(String)httpsession.getAttribute("username"));
            return "新增成功";
        }else {
            return "尚未登入";
        }

    }
    @PostMapping("deletecat")
    @ResponseBody
    public String delete(@RequestParam int id,HttpSession httpsession){
        cartService.deletecart(id,(String)httpsession.getAttribute("username"));
        return "刪除成功";
    }
    @PostMapping("updateqnum")
    @ResponseBody
    public String updateqnum(@RequestParam int id,String method,HttpSession httpsession){
        System.out.println(id);
        System.out.println(method);
        cartService.update_quantity(id,(String)httpsession.getAttribute("username"),method);
        return "修改成功";
    }
}
