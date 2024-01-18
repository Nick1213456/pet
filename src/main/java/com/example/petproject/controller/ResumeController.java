package com.example.petproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {
    @GetMapping("/aboutNetwork")
    public String aboutNetwork(){
        return ("aboutNetwork");
    }

    @GetMapping("/Resume_Norin")
    public String Norin(){
        return ("Resume_Norin");
    }

    @GetMapping("/Resume_Nick")
    public String Nick(){
        return ("Resume_Nick");
    }

    @GetMapping("/Resume_Chi")
    public String Chi(){
        return ("Resume_Chi");
    }

    @GetMapping("/Resume_Yi_Dong")
    public String Yi_Dong(){
        return ("Resume_Yi_Dong");
    }

    @GetMapping("/Resume_Alex")
    public String Alex(){
        return ("Resume_Alex");
    }

    @GetMapping("/Resume_吉")
    public String 吉(){
        return ("Resume_吉");
    }
    @GetMapping("/Resume_p")
    public String p(){
        return ("Resume_p");
    }


}
