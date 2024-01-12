package com.example.petproject.controller;

import com.example.petproject.Service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

    @Autowired
    memberService mS;

    @PostMapping("/regist/usernameCk")
    public boolean usernameCk(@RequestBody String username){
        boolean result2 = mS.usernameCheck(username);
        //result:表示帳號是否存在 true = 存在
        return result2;
    }
}
