package com.example.petproject.Mapper;

import com.example.petproject.Model.cartModel2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class cartMapper2 implements RowMapper<cartModel2> {
    @Override
    public cartModel2 mapRow(ResultSet rs,int rowNum) throws SQLException {
        cartModel2 cm2=new cartModel2();
        cm2.setOrderNumber(rs.getInt("OrderNumber"));
        cm2.setCommodityID(rs.getInt("CommodityID"));
        cm2.setComQuantity(rs.getInt("ComQuantity"));
        cm2.setAmount(rs.getInt("Amount"));
        return cm2;
    }
}
