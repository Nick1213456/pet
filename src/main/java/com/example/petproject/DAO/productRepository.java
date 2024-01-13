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
}
