package com.example.petproject.DAO;


import com.example.petproject.Mapper.productMapper;
import com.example.petproject.Model.productModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class productRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<productModel> getproductAll(){
        return jdbcTemplate.query("SELECT * FROM `commoditylist`",new productMapper());
    }

    public List<productModel> getproducdetail(int id){
        return jdbcTemplate.query("SELECT * FROM commoditylist WHERE CommodityID=?",new productMapper(),id);
    }

    //取得資料庫ID最小
    public int getproductMINID(){
        return jdbcTemplate.queryForObject("SELECT MIN(CommodityID) FROM commoditylist;",int.class);
    }

    public void setproductditail(productModel pm,int id){
        jdbcTemplate.update("UPDATE commoditylist SET CommodityID=?,CommodityName=?," +
                "CommodityKind=?,Size=?,Inventory=?, Price=?,Cost=?,Detail=? " +
                "WHERE CommodityID=?",pm.getCommodityID(),pm.getCommodityName(),pm.getCommodityKind(),pm.getSize(),pm.getInventory(),pm.getPrice(),pm.getCost(),pm.getDetail(),id);

    }
}
