package com.example.petproject.controller;

import com.example.petproject.Model.RegistModel;
import com.example.petproject.Service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

@RequestMapping("/regist")
public class registController {

    //註冊
    @GetMapping("/regist")
    public String regist(Model model){
        RegistModel registModel = new RegistModel();
        model.addAttribute("registModel",registModel);
        return("/regist/regist");
    }


    @Autowired
    memberService mS;

    //註冊結果判定
    @PostMapping("/regist")
    public String registCheck(Model model, @ModelAttribute RegistModel registModel){
        int result = mS.memberRegist(registModel);
        String msg;
        switch (result){
            case 0:
                msg = "註冊成功!";
                break;
            case 1:
                msg = "密碼與密碼確認不同，請再重新輸入";
                break;
            case 2:
                msg = "帳號已經註冊完成過,請更換註冊帳號";
                break;
            case 3:
                msg = "帳號不可包含禁止詞彙(delete,drop,select,update...等),請更換帳號";
                break;
            default:
                msg = "發生不明錯誤，請與客服人員聯絡解決問題";
        }
        model.addAttribute("mesg",msg);
        return("regist/result");
    }



    @GetMapping("result")
    public String result(){
        return("result");
    }

}
