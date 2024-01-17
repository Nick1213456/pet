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
    //查詢全部
    public List<petModel> getpetAll(){
        return jdbcTemplate.query("SELECT * FROM `petprogram`",new petMapper());
    }

//    public List<petModel> getpetUid(){
//        return jdbcTemplate.query("SELECT * FROM `petprogram`",new petMapper());
//    }
    //上架新增
    public int insertPetData(petModel pM,String username){
        jdbcTemplate.update("INSERT INTO `petprogram`(PetName,Gender,Ligation,HairLengh,HairColor,Age,Weight,Variety,Kind,Remark,`Ad-Name`)"+
                "VALUES('"+pM.getPetName()+"','"+pM.getGender()+"','"+pM.getLigation()+"','"+pM.getHairLength()+"','"+pM.getHairColor()+"','"+pM.getAge()+"','"+pM.getWeight()+"','"+pM.getVariety()+"','"+pM.getKind()+"','"+pM.getRemark()+"','"+username+"');");
        return jdbcTemplate.queryForObject("Select MAX(UID) FROM petprogram",Integer.class);
    }

    //給ID查全部
    public List<petModel> getpetAllForid(int uid){
        return jdbcTemplate.query("SELECT * FROM petprogram WHERE UID=?",new petMapper(),uid);
    }

    public void petDrop(int petID){
        String sql="DELETE FROM petprogram WHERE UID=?";
        jdbcTemplate.update(sql,petID);
    }
}
