package com.example.petproject.controller;

import com.example.petproject.Model.RegistModel;
import com.example.petproject.Service.memberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class registController {

    //註冊
    @GetMapping("/reg")
    public String regist(){
        return("reg");
    }


    @Autowired
    memberService mS;

    //註冊結果判定
    @PostMapping("/reg")
    public String registCheck(Model model,HttpSession session, @ModelAttribute RegistModel registModel){
        int result = mS.memberRegist(registModel);
        String msg = null;
        switch (result){
            case 0:
                String username = registModel.getRegistUsername();
                session.setAttribute("username",username);
                msg="註冊成功";
                break;
            case 1:
                msg = "密碼與密碼確認不同，請再重新輸入";
                break;
            case 2:
                msg="帳號已經註冊完成過,請更換註冊帳號";
                break;
            case 3:
                msg="帳號不可包含禁止詞彙(delete,drop,select,update...等),請更換帳號";
                break;
            default:
                msg="發生不明錯誤，請與客服人員聯絡解決問題";
        }
        model.addAttribute("mesg",msg);
        return("regResult");
    }



    @GetMapping("result")
    public String result(){
        return("result");
    }

}
