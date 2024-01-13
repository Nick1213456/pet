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
}
