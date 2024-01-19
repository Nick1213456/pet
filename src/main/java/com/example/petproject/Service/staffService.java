package com.example.petproject.Service;

import com.example.petproject.DAO.staffRepository;
import com.example.petproject.Model.orderModel;
import com.example.petproject.Model.permissionModel;
import com.example.petproject.Model.productModel;
import com.example.petproject.Model.productModel2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class staffService {
    @Autowired
    staffRepository sR;

    //會員狀態頁面查詢,順便轉換
    public List<permissionModel> memberList(){
        List<permissionModel> list = new ArrayList<>() ;
        for(permissionModel member:sR.memberList()){
            if(member.getEmployee().equals("0")){
                member.setEmployee("封鎖");
            }
            else if(member.getEmployee().equals("1")){
                member.setEmployee("會員");
            }
            else {
                member.setEmployee("管理員");
            }
            list.add(member);
        }

        return list;
    }

    //更改會員權限
    public void changePer0(String username){
        sR.changePer0(username);
    }
    public void changePer1(String username){
        sR.changePer1(username);
    }
    public void changePer2(String username){
        sR.changePer2(username);
    }

    //呼叫訂單管理頁面
    public List<orderModel> orderList(){
        return sR.OrderList();
    }

    //更新狀態和出貨日期
    public void insertOrderList(int status, String ShippedDate,int orderNumber,String Picker){
        sR.ShipUpdate(status,ShippedDate,orderNumber,Picker);
    }

    //判斷資料庫內是否有同樣ID的資料
    //如果沒有上架商品,將資料插入資料庫
    public boolean product_upload(productModel2 pM){
        if(sR.CommodityIDCheck(pM) == 0) {
            sR.product_Upload(pM);
            return true;
        }else{
            return false;
        }
    }

    public void remove0(int id){
        sR.remove(id);
    }

}
