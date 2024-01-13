package com.example.petproject.Mapper;

import com.example.petproject.Model.productModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class petMapper implements RowMapper<productModel> {
    @Override
    public productModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        productModel pm=new productModel();
        pm.setCommodityKind(rs.getString("CommodityKind"));
        pm.setCommodityID(rs.getInt("CommodityID"));
        pm.setCommodityName(rs.getString("CommodityName"));
        pm.setSize(rs.getString("Size"));
        pm.setInventory(rs.getInt("Inventory"));
        pm.setPrice(rs.getInt("Price"));
        pm.setCost(rs.getDouble("Cost"));
        pm.setDetail(rs.getString("Detail"));
        return pm;
    }
}
