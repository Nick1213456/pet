package com.example.petproject.Service;

import com.example.petproject.Mapper.petMapper;
import com.example.petproject.Model.productModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class petService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<productModel> getPetall(){
        return jdbcTemplate.query("select * from commoditylist",new petMapper());
    }
    public List<productModel>getPetId(String on){
        return jdbcTemplate.query("select * from commoditylist where CommodityID LIKE ?",new petMapper(),"%"+on+"%");
    }
//    public List<productModel>getKeyword(String word){
//        return jdbcTemplate.query("SELECT * FROM commoditylist WHERE CommodityName LIKE '%\" + word + \"%'",new petMapper());
//    }

    public List <productModel>getKeyword(String word){
        petMapper petMapper= new petMapper();
        String sql="SELECT * FROM commoditylist WHERE CommodityName LIKE ? OR Sort LIKE ? or CommodityID like ? or Size like ?";
        //return jdbcTemplate.query(sql,new Object[]{"%" + word + "%"},petMapper);
        return jdbcTemplate.query(sql,petMapper,"%"+word+"%","%"+word+"%","%"+word+"%","%"+word+"%");
    }
}
