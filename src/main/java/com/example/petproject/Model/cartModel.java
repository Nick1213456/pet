package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class cartModel {
    private int orderNumber;
    private int CommodityID;
    private int ComQuantity;
    private int Amount;
}
