package com.example.petproject.controller;

import com.example.petproject.Model.UnPs;
import com.example.petproject.Model.memberData;
import com.example.petproject.Model.passwdForget;
import com.example.petproject.Service.memberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;


@Controller
public class loginController {

    @Autowired
    memberService mS;

    //首頁
    @GetMapping("/index")
    public String index(){
        return("index");
    }

    //登入頁面
    @GetMapping("/login")
    public String doDefault(){
        return("login");
    }

    //帳號密碼檢查
    @PostMapping("/pscheck")
    public String loginCheck(UnPs unps, HttpSession session) {
        String username = unps.getUsername();
        String password = unps.getPassword();
        String memberPer = mS.memberPer(username);
        boolean result = mS.passcheck(username,password);
        if(result && memberPer.equals("1")) {
            session.setAttribute("username",username);
            return ("redirect:memberpage");
        }
        else if(result && memberPer.equals("2")){
            session.setAttribute("username",username);
            return ("redirect:backstage");
        }
        else{
            //給個登入失敗的東西
            return ("redirect:failed");
        }
    }

    //登入失敗
    @GetMapping("/failed")
    public String loginFailed(){
        return("failed");
    }

    //會員頁面
    @GetMapping("/memberpage")
    public String memberPage_Controller(HttpSession session,Model model){
        boolean result = mS.sessionCheck(session);
        if(result) {
            List<memberData> mD= mS.memberData((String)session.getAttribute("username"));
            model.addAttribute("memberData",mD);
            return ("memberpage");
        }
        else{
            return ("index");
        }
    }

    //忘記密碼
    @GetMapping("/forgetpassword")
    public String forgetpassword(){
        return("forgetpassword");
    }

    //檢查忘記密碼驗證
    @PostMapping("/forgetpassword")
    public String returnPassword(passwdForget pF,Model model){
        String username = pF.getUsername();
        String email = pF.getEmail();
        String password = mS.returnPassword(username,email);
        model.addAttribute("password",password);
        model.addAttribute("username",username);
        return ("returnpasswd");
    }

    //登出
    @PostMapping("/signOut")
    public String signOut(HttpSession session){
        mS.signOut(session);
        return ("signOut");
    }

    //照片上傳
    @PostMapping("/uploadImg")
    public String uploadImg(@RequestPart("fileInput") MultipartFile mf,HttpSession session){

        String username = (String)session.getAttribute("username");
        Path memberPhoto=Paths.get("C:/temp/"+username+"_01.jpg");
        try{
            if(Files.exists(memberPhoto)){
                Files.copy(mf.getInputStream(),memberPhoto,StandardCopyOption.REPLACE_EXISTING);
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

        return "redirect:memberpage";
    }



}