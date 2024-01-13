package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class orderModel {
    private int OrderNumber;
    private int GuestUID;
    private int OrderAmount;
    private Date OrderDate;
    private String Status;
    private Date ShippedDate;
    private String Picker;
    private String ShipMethod;
    private  String Note;

}
