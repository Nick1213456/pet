package com.example.petproject.controller;

import com.example.petproject.Model.orderInsert;
import com.example.petproject.Model.orderModel;
import com.example.petproject.Model.productModel;
import com.example.petproject.Service.staffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Controller
public class staffController {
    //工作人員頁面:1.會員管理頁面 2.商品上架頁面 3.訂單管理頁面
    @Autowired
    staffService sS;

    @GetMapping("/staff/staff_page")
    public String staff_page(){
        return("/staff/staff_page");
    }

    //訂單管理
    @GetMapping("/staff/order_Manage")
    public String order_Manage(Model model){
        List<orderModel> orderlist = sS.orderList();
        model.addAttribute("orderlist",orderlist);
        return("/staff/order_Manage");
    }

    //訂單出貨狀態更改
    @PostMapping("/staff/order_Manage")
    public String order_commit(Model model, @ModelAttribute orderInsert oi, HttpSession session){
        String username = (String)session.getAttribute("username");
        sS.insertOrderList(oi.getStatus(),oi.getShippedDate(),oi.getOrderNumber(),username);
        return("/staff/order_Manage");
    }

    //會員管理
    @GetMapping("/staff/member_Manage")
    public String member_Manage(Model model){
        model.addAttribute("memberList",sS.memberList());
        return("/staff/member_Manage");
    }

    //更改會員權限
    @PostMapping("/staff/memberPerChange0")
    public String member_Manage0(@RequestBody String username){
        sS.changePer0(username);
        return("/staff/member_Manage");
    }
    @PostMapping("/staff/memberPerChange1")
    public String member_Manage1(@RequestBody String username){
        sS.changePer1(username);
        return("/staff/member_Manage");
    }
    @PostMapping("/staff/memberPerChange2")
    public String member_Manage2(@RequestBody String username){
        sS.changePer2(username);
        return("/staff/member_Manage");
    }

    //貨品上架頁面
    @GetMapping("/staff/upload_Product")
    public String upload_ProductController(){
        return("/staff/upload_Product");
    }

    //貨品上架處理
    @PostMapping("/staff/upload_Product")
    public String upload_ProductService(@ModelAttribute productModel pM){
        //將傳過來的插入資料庫
        if(sS.product_upload(pM)){
            return("/staff/upload_Product");
        }
        else {
            return("/staff/productUpload_Failed");
        }
    }




}
