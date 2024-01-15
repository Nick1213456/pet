package com.example.petproject.DAO;

import com.example.petproject.Mapper.petMapper;
import com.example.petproject.Model.petModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class petRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<petModel> getpetAll(){
        return jdbcTemplate.query("SELECT * FROM `petprogram`",new petMapper());
    }

//    public List<petModel> getpetUid(){
//        return jdbcTemplate.query("SELECT * FROM `petprogram`",new petMapper());
//    }

    public int insertPetData(petModel pM){
        jdbcTemplate.update("INSERT INTO `petprogram`(PetName,Gender,Ligation,HairLengh,HairColor,Age,Weight,Variety,Kind,Remark)"+
                "VALUES('"+pM.getPetName()+"','"+pM.getGender()+"','"+pM.getLigation()+"','"+pM.getHairLength()+"','"+pM.getHairColor()+"','"+pM.getAge()+"','"+pM.getWeight()+"','"+pM.getVariety()+"','"+pM.getKind()+"','"+pM.getRemark()+"');");
        return jdbcTemplate.queryForObject("Select MAX(UID) FROM petprogram",Integer.class);
    }
}
