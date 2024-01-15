package com.example.petproject.controller;

import com.example.petproject.Model.petModel;
import com.example.petproject.Service.petService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

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
        int UID = petService.insertPetData(pM);
        String directoryPath="C:/temp/petimg/"+UID+"/";
        Path petphoto =Paths.get(directoryPath,"01.jpg");
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
        return "index";
    }
}
