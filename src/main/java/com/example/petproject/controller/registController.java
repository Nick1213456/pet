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
    public String regist(Model model){
        RegistModel registModel = new RegistModel();
        model.addAttribute("registModel",registModel);
        return("/reg");
    }


    @Autowired
    memberService mS;

    //註冊結果判定
    @PostMapping("/reg")
    public String registCheck(HttpSession session,Model model, @ModelAttribute RegistModel registModel, @RequestPart("fileInput") MultipartFile mf){
        int result = mS.memberRegist(registModel);
        String msg;
        switch (result){
            case 0:
                msg = "註冊成功!";
                String username = registModel.getUsername();
                Path memberPhoto= Paths.get("C:/temp/memberimg/"+username+"_01.jpg");
                try{
                    if(Files.exists(memberPhoto)){
                        Files.copy(mf.getInputStream(),memberPhoto, StandardCopyOption.REPLACE_EXISTING);
                        byte[] image =Files.readAllBytes(memberPhoto);
                        String hash64 = java.util.Base64.getEncoder().encodeToString(image);
                        session.setAttribute("src","data:image/jpeg;base64,"+hash64);
                    }
                    else{
                        mf.transferTo(memberPhoto);
                    }
                }
                catch (Exception e){
                    System.err.println("檔案存入失敗");
                }
                model.addAttribute("mesg",msg);
                return("index");
            case 1:
                msg = "密碼與密碼確認不同，請再重新輸入";
                model.addAttribute("mesg",msg);
                return("reg");
            case 2:
                msg = "帳號已經註冊完成過,請更換註冊帳號";
                model.addAttribute("mesg",msg);
                return("reg");
            case 3:
                msg = "帳號不可包含禁止詞彙(delete,drop,select,update...等),請更換帳號";
                model.addAttribute("mesg",msg);
                return("reg");
            default:
                msg = "發生不明錯誤，請與客服人員聯絡解決問題";
                model.addAttribute("mesg",msg);
                return("reg");

        }
    }



    @GetMapping("result")
    public String result(){
        return("result");
    }

}
