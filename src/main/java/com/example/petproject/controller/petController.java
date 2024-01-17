package com.example.petproject.controller;

import com.example.petproject.Model.petModel;
import com.example.petproject.Service.petService;
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
public class petController {
    @Autowired
    petService petService;
    @GetMapping("/petlist")
    public String showpetlist(Model m){
        m.addAttribute("pets",petService.getpetAll());

        return "petlist";
    }


    //領養上架
    @GetMapping("/adopt")
    public String adopt(){
        return "adopt";
    }

    @PostMapping("/adopt")
    public String adopt(@ModelAttribute petModel pM, @RequestPart("fileInput") MultipartFile mf, HttpSession session){
        int UID = petService.insertPetData(pM,(String)session.getAttribute("username"));
        String directoryPath="C:/temp/petimg/"+UID+"/";
        Path petphoto =Paths.get(directoryPath,"img_1.jpg");
        try{
            if(!Files.exists(petphoto.getParent())){
                Files.createDirectories(petphoto.getParent());
            }
            if(Files.exists(petphoto)){
                Files.copy(mf.getInputStream(),petphoto,StandardCopyOption.REPLACE_EXISTING);
            }
            else{
                mf.transferTo(petphoto);
            }
        }
        catch (Exception e){
            System.err.println("檔案儲存失敗");
        }
        return "redirect:/petlist";
    }


    @GetMapping("/adoptdetail")
    public String showadoptdetail(@RequestParam int id, Model m){
        m.addAttribute("adop",petService.getpetdetail(id));
        return "adoptdetail";
    }

    @PostMapping("adoptdetail")
    public String adoptdrop(int UID){
        //呼叫方法刪除上架寵物資料
        System.err.println(UID);
        petService.petDrop(UID);
        return "petlist";
    }

    @GetMapping("/adopter")
    public String adopter(Model model,@RequestParam int id){
        //寫個方法從id>>>抓取username
        //用username>>>抓取主人資料
        //把主人資料裝進model裡面
        model.addAttribute("ownerData",petService.petOwner(id));
        return "/adopter";
    }
}
