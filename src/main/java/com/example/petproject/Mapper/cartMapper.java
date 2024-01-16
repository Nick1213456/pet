package com.example.petproject.Mapper;

import com.example.petproject.Model.cartModel;
import org.springframework.jdbc.core.RowMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class cartMapper implements RowMapper<cartModel> {
    @Override
    public cartModel mapRow(ResultSet rs, int rowNum)throws SQLException {
        cartModel cm=new cartModel();
        cm.setOrderNumber(rs.getInt("orderNumber"));
        cm.setCommodityID(rs.getInt("CommodityID"));
        cm.setComQuantity(rs.getInt("ComQuantity"));
        cm.setAmount(rs.getInt("Amount"));
        cm.setCommodityName(rs.getString("CommodityName"));
        cm.setSize(rs.getString("Size"));
        cm.setPrice(rs.getInt("price"));
        try {
            Path imagePath = Paths.get("C:/temp/productimg/"+rs.getInt("CommodityID")+"/img_1.jpg");
            byte[] bytepath = Files.readAllBytes(imagePath);
            // 讀取圖片檔案
            cm.setImageBytes( "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bytepath));
        } catch (Exception e) {
//            e.printStackTrace();//紅字錯誤
            cm.setImageBytes("/images/close_icon.png");
        }
        return cm;
    }
}