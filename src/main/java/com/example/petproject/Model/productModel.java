package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class productModel {
    private String CommodityKind;
    private int CommodityID;
    private String CommodityName;
    private String Size;
    private int Inventory;
    private int Price;
    private double Cost;
    private String Detail;
}
