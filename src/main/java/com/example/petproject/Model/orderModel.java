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
    private String GuestName;
    private int OrderAmount;
    private Date OrderDate;
    private int Status;
    private Date ShippedDate;
    private String Picker;
    private String ShipMethod;
    private  String Note;

}
