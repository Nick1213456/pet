package com.example.petproject.DAO;

import com.example.petproject.Mapper.orderMapper;
import com.example.petproject.Mapper.permissionMapper;
import com.example.petproject.Model.orderModel;
import com.example.petproject.Model.permissionModel;
import com.example.petproject.Model.productModel;
import com.example.petproject.Model.productModel2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class staffRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    permissionMapper permissionmapper;

    @Autowired
    orderMapper ordermodelmapper;

    //回傳帳號和權限
    public List<permissionModel> memberList(){
        List<permissionModel> pMs = jdbcTemplate.query("SELECT username,Employee FROM member",permissionmapper);
        return pMs;
    }

    //從DB更改權限
    public void changePer0(String username){
        jdbcTemplate.update("UPDATE member SET Employee='0' WHERE username='"+username+"';");
    }
    public void changePer1(String username){
        jdbcTemplate.update("UPDATE member SET Employee='1' WHERE username='"+username+"';");
    }
    public void changePer2(String username){
        jdbcTemplate.update("UPDATE member SET Employee='2' WHERE username='"+username+"';");
    }

    //訂單資料拉出
    public List<orderModel> OrderList(){
        List<orderModel> OL = jdbcTemplate.query("SELECT * FROM `order`",ordermodelmapper);
        return OL;
    }

    //訂單撿貨和出貨日期和狀態更新
    public void ShipUpdate(int status, String ShippedDate, int orderNumber,String Picker){
        if(ShippedDate == null) {
            jdbcTemplate.update("UPDATE `order` SET ShippedDate=("+ShippedDate+"),`Status`='" + status + "',Picker='" + Picker + "' WHERE OrderNumber='" + orderNumber + "';");
        }
        else {
            jdbcTemplate.update("UPDATE `order` SET ShippedDate= '" + ShippedDate + "',`Status`='" + status + "',Picker='" + Picker + "' WHERE OrderNumber='" + orderNumber + "';");
        }
    }

    public void product_Upload(productModel2 pM){
        String kind = pM.getCommodityKind();
        int ID=pM.getCommodityID();
        String name = pM.getCommodityName();
        String size = pM.getSize();
        int inventory = pM.getInventory();
        int price = pM.getPrice();
        double cost = pM.getCost();
        String Detail=pM.getDetail();
        String sql = "INSERT INTO Commoditylist(CommodityKind,CommodityID,CommodityName,Size,Inventory,Price,Cost,Detail) VALUES ('"+kind+"','"+ID+"','"+name+"','"+size+"','"+inventory+"','"+cost+"','"+price+ "','"+Detail+"');";
        System.err.println(sql);
        jdbcTemplate .update(sql);
    }

    public int CommodityIDCheck(productModel2 pM){
        int ID=pM.getCommodityID();
        String sql = "SELECT count(*) FROM Commoditylist WHERE CommodityID="+ID+";";
        int IDresult = jdbcTemplate.queryForObject(sql,Integer.class);
        return IDresult;
    }

}
