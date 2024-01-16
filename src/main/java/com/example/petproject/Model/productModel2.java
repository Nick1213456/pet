package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class productModel2 {
    private String CommodityKind;
    private int CommodityID;
    private String CommodityName;
    private String Size;
    private int Inventory;
    private int Price;
    private Double Cost;
    private String Detail;
}
