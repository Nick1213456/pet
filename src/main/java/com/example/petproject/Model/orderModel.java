package com.example.petproject.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class orderModel {
    private String OrderNumber;
    private String GuestUID;
    private String OrderDate;
    private String Status;
    private String ShippedDate;
    private String OrderAmount;
    private String Note;
    private String Picker;

}
