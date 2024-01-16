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
    private String CommodityID;
    private String CommodityName;
    private String Size;
    private String Inventory;
    private String Price;
    private String Cost;
    private String Detail;
    private String imageBytes;
}
