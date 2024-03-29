package com.example.petproject.controller;

import com.example.petproject.Model.orderInsert;
import com.example.petproject.Model.orderModel;
import com.example.petproject.Model.productModel;
import com.example.petproject.Model.productModel2;
import com.example.petproject.Service.memberService;
import com.example.petproject.Service.productService;
import com.example.petproject.Service.staffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;



@Controller
public class staffController {
    //工作人員頁面:1.會員管理頁面 2.商品上架頁面 3.訂單管理頁面
    @Autowired
    staffService sS;
    @Autowired
    memberService mS;
    @Autowired
    productService productservice;

    @GetMapping("backstage")
    public String staff_page(Model model,HttpSession session){
        if(session.getAttribute("username")!=null){
            //工作人員資料印出
            String username = (String) session.getAttribute("username");
            model.addAttribute("memberData", mS.memberData(username));
            //訂單管理印出
            List<orderModel> orderlist = sS.orderList();
            model.addAttribute("orderlist", orderlist);
            //會員管理印出
            model.addAttribute("memberList", sS.memberList());
            //後臺全部商品列出
            model.addAttribute("shopping",productservice.getproductAll());
            return ("backstage");
        }
        else{
            return("index2");
        }
    }

    //訂單狀態管理
    @PostMapping("/orderManage")
    public String orderChange( @ModelAttribute orderInsert oi, HttpSession session){
        String username = (String)session.getAttribute("username");
        sS.insertOrderList(oi.getStatus(),oi.getShippedDate(),oi.getOrderNumber(),username);
        return ("redirect:/backstage");
    }

    //更改會員權限
    @PostMapping("/memberPerChange0")
    public String member_Manage0(@RequestBody String username){
        sS.changePer0(username);
        return("redirect:/backstage");
    }
    @PostMapping("/memberPerChange1")
    public String member_Manage1(@RequestBody String username){
        sS.changePer1(username);
        return("redirect:/backstage");
    }
    @PostMapping("/memberPerChange2")
    public String member_Manage2(@RequestBody String username){
        sS.changePer2(username);
        return("redirect:/backstage");
    }

    //貨品上架頁面
    @GetMapping("/upload_Product")
    public String upload_ProductController(@ModelAttribute productModel2 pM){

        return("/backstage");
    }

    //貨品上架處理
    @PostMapping("/upload_Product")
    public String upload_ProductService(@ModelAttribute productModel2 pM, @RequestPart("fileInput") MultipartFile mf){
        //將傳過來的插入資料庫
        if(sS.product_upload(pM)){
            String directoryPath = "C:/temp/productimg/" + pM.getCommodityID() + "/";
                Path productPhoto = Paths.get(directoryPath, "img_1.jpg");
                try {
                    if (!Files.exists(productPhoto.getParent())) {
                        Files.createDirectories(productPhoto.getParent());
                    }
                    if (Files.exists(productPhoto)) {
                        Files.copy(mf.getInputStream(), productPhoto, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        mf.transferTo(productPhoto);
                    }
//                byte[] image =Files.readAllBytes(productPhoto);
//                String hash64 = java.util.Base64.getEncoder().encodeToString(image);
//                model.addAttribute("src","data:image/jpeg;base64,"+hash64);
                } catch (Exception e) {
                    System.err.println("檔案存入失敗");
                }

            return("redirect:/shopping");
        }
        else{
            return("productUpload_Failed");
        }
    }

    @GetMapping("/productUpload_Failed")
    public String productUpload_Failed(){
        return ("productUpload_Failed");
    }

    @PostMapping("/remove")
    public String remove(@RequestParam int remove){
        sS.remove0(remove);
        return "index";
    }
    //後臺商品編輯功能
    @PostMapping("/editover")
    public String editover(productModel productModel){
        System.out.println("1");
        productservice.setproductditail(productModel,productModel.getCommodityID());
        return("redirect:/backstage");
    }


}
