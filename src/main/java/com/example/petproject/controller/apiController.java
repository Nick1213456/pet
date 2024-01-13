package com.example.petproject.controller;

import com.example.petproject.Model.orderModel;
import com.example.petproject.Service.memberService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

    @Autowired
    memberService mS;

    @PostMapping("/usernameCk")
    public boolean usernameCk(@RequestBody String username){
        boolean result2 = mS.usernameCheck(username);
        //result:表示帳號是否存在 true = 存在
        return result2;
    }


}
