package com.example.petproject.controller;

import com.example.petproject.Model.RegistModel;
import com.example.petproject.Model.productModel;
import com.example.petproject.Service.memberService;
import com.example.petproject.Service.productService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class apiController {

    @Autowired
    memberService mS;
    @Autowired
    productService productservice;

    @PostMapping("/regist/usernameCk")
    public boolean usernameCk(@RequestBody String username){
        boolean result2 = mS.usernameCheck(username);
        return result2;
    }
    //傳sesstion username給login.js判斷登入登出
    @PostMapping("/check-session")
    public String checkSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String memberper = (String) session.getAttribute("memberPer");
        if (username == null && memberper == null ) {
            return "";

        }else {
            return username+memberper;
        }
    }
    //給後臺商品編輯內容
    @PostMapping("/edit")
    public productModel pedit(@RequestParam int pid){
        return productservice.getproducdetail(pid);
    }


}
