package com.example.petproject.Mapper;

import com.example.petproject.Model.petModel;
import com.example.petproject.Model.productModel;
import org.springframework.jdbc.core.RowMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class productMapper implements RowMapper<productModel> {
    @Override
    public productModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        productModel pm = new productModel();
        pm.setCommodityKind(rs.getString("CommodityKind"));
        pm.setCommodityID(rs.getInt("CommodityID"));
        pm.setCommodityName(rs.getString("CommodityName"));
        pm.setSize(rs.getString("Size"));
        pm.setInventory(rs.getInt("Inventory"));
        pm.setPrice(rs.getInt("Price"));
        pm.setCost(rs.getDouble("Cost"));
        pm.setDetail(rs.getString("Detail"));

        try {
            Path imagePath = Paths.get("C:/temp/productimg/"+rs.getInt("CommodityID")+"/img_1.jpg");
            byte[] bytepath = Files.readAllBytes(imagePath);
            // 讀取圖片檔案
            pm.setImageBytes( "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bytepath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pm;
    }
}
