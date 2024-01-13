package com.example.petproject.Mapper;

import com.example.petproject.Model.cartModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        return cm;
    }
}