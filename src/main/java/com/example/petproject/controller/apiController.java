package com.example.petproject.controller;

import com.example.petproject.Service.memberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    //傳sesstion username給login.js判斷登入登出
    @PostMapping("/check-session")
    public String checkSession(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return username;
        } else {
            return "";
        }
    }
}
