package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class petModel {
    private long uid;
    private String PetName;
    private String Gender;
    private String ligation;
    private String hairLength;
    private String hairColor;
    private double age;
    private int weight;
    private String variety;
    private String kind;
    private String n_Adopted;
    private String adopted;
    private String chip;
    private String ad_Name;
    private String remark;
    private String imageBytes;
}
