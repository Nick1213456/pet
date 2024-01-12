package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistModel {
    private String username;
    private String password;
    private String passwordCk;
    private String name;
    private String Birth;
    private String phone;
    private String email;
    private String address;
    private String adpExp;
    private String meter;

}
