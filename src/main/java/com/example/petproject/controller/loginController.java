package com.example.petproject.controller;

import com.example.petproject.Model.UnPs;
import com.example.petproject.Model.memberData;
import com.example.petproject.Model.passwdForget;
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

    //about
    @GetMapping("/about")
    public String about(){
        return("about");
    }

    //news
    @GetMapping("/problem")
    public String problem(){
        return("problem");
    }

    //news
    @GetMapping("/news")
    public String news(){
        return("news");
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
            session.setAttribute("memberPer",memberPer);
            session.setAttribute("src","C:/temp/memberimg/" + username + "/img_1.jpg");
            return ("redirect:memberpage");
        }
        else if(result && memberPer.equals("2")){
            session.setAttribute("username",username);
            session.setAttribute("memberPer",memberPer);
            return ("redirect:/backstage");
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
            List<memberData> mD=mS.memberData((String)session.getAttribute("username"));
            model.addAttribute("memberData",mD);
            model.addAttribute("memberSrc",mS.outputPhoto((String)session.getAttribute("username")));
            return ("memberpage");
        }
        else{
            return ("index");
        }
    }

    @PostMapping("/memberpage")
    public String memberPage_Change(@ModelAttribute memberData mD,HttpSession session,Model model){
        String oldusername = (String)session.getAttribute("username");
        String newusername = mS.dataChange(mD,oldusername);
        session.setAttribute("username",newusername);
        model.addAttribute("memberSrc",mS.outputPhoto((String)session.getAttribute("username")));
        return ("memberpage");

    }



    //檢查忘記密碼驗證
    @PostMapping("/forgetpassword")
    public String returnPassword(@ModelAttribute passwdForget pF,Model model){
        String username = pF.getUsername();
        String email = pF.getEmail();
        String password = mS.returnPassword(username,email);
        model.addAttribute("password",password);
        model.addAttribute("username",username);
        return ("index");
    }

    //登出
    @GetMapping("/")
    public String signOut(HttpSession session){
        mS.signOut(session);
        //暫時返回首頁
        return "index";
    }

    //照片上傳
    @PostMapping("/uploadImg")
    public String uploadImg(@RequestPart("fileInput") MultipartFile mf,HttpSession session){

        String username = (String)session.getAttribute("username");
        String directoryPath = "C:/temp/memberimg/" + username + "/";
        // 指定文件路径
        Path memberPhoto = Paths.get(directoryPath, "img_1.jpg");
        try{
            if(!Files.exists(memberPhoto.getParent())){
                Files.createDirectories(memberPhoto.getParent());
            }
            if(Files.exists(memberPhoto)){
                Files.copy(mf.getInputStream(),memberPhoto,StandardCopyOption.REPLACE_EXISTING);
            }
            else{
                mf.transferTo(memberPhoto);
            }
            byte[] image =Files.readAllBytes(memberPhoto);
            String hash64 = java.util.Base64.getEncoder().encodeToString(image);
            session.setAttribute("src","data:image/jpeg;base64,"+hash64);
        }
        catch (Exception e){
            System.err.println("檔案存入失敗");
        }

        return "redirect:memberpage";
    }


}