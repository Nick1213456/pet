package com.example.petproject.Mapper;

import com.example.petproject.Model.petModel;
import org.springframework.jdbc.core.RowMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class petMapper implements RowMapper<petModel> {
    Path imagePath;
    byte[] bytepath;
    @Override
    public petModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        petModel pm = new petModel();
        pm.setUid(rs.getLong("UID"));
        pm.setPetName(rs.getString("PetName"));
        pm.setGender(rs.getString("Gender"));
        pm.setLigation(rs.getString("Ligation"));
        pm.setHairLength(rs.getString("HairLengh"));
        pm.setHairColor(rs.getString("HairColor"));
        pm.setAge(rs.getDouble("Age"));
        pm.setWeight(rs.getInt("Weight"));
        pm.setVariety(rs.getString("Variety"));
        pm.setKind(rs.getString("Kind"));
        pm.setN_Adopted(rs.getString("N_Adopted"));
        pm.setAdopted(rs.getString("Adopted"));
        pm.setChip(rs.getString("Chip"));
        pm.setAd_Name(rs.getString("Ad-Name"));
        pm.setRemark(rs.getString("Remark"));



        try {
            imagePath = Paths.get("C:/temp/petimg/"+rs.getLong("UID")+"/img_1.jpg");
            bytepath = Files.readAllBytes(imagePath);
            // 讀取圖片檔案
            pm.setImageBytes( "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bytepath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pm;
    }
}
