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
        pm.setCommodityID(rs.getString("CommodityID"));
        pm.setCommodityName(rs.getString("CommodityName"));
        pm.setSize(rs.getString("Size"));
        pm.setInventory(rs.getString("Inventory"));
        pm.setPrice(rs.getString("Price"));
        pm.setCost(rs.getString("Cost"));
        pm.setDetail(rs.getString("Detail"));

        return pm;
    }
}
