package com.example.petproject.controller;


import com.example.petproject.Service.petService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class petController {
    @Autowired
    petService petService;
    @GetMapping("/pets")
    public String getPetcommoditylist(Model m){
        m.addAttribute("pet",petService.getPetall());
        return "petorder";
    }

    @GetMapping("/petorderID")
    public String getPetID(Model m, @RequestParam String on){
        m.addAttribute("pet",petService.getPetId(on));
        return "petorder";
    }
    @GetMapping("/searchID")
    public String searchID(){
        return "petSearch";
    }

    @GetMapping("/keyList")
    public String getKeyword(Model m,@RequestParam String word){
        m.addAttribute("key",petService.getKeyword(word));
        return "searchPetKeyWord";
    }
    @GetMapping("/keySearch")
    public String getKeyWordSearch(){
        return "keywordsearch";
    }

}