package com.example.petproject.Mapper;

import com.example.petproject.Model.petModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class petMapper implements RowMapper<petModel> {
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
        return pm;
    }
}
