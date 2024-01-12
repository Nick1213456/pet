package com.example.petproject.Mapper;

import com.example.petproject.Model.UserType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class userTypeRowMapper implements RowMapper {

    @Override
    public UserType mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserType ut = new UserType();
        ut.setUsername(rs.getString("username"));
        ut.setEmployee(rs.getString("Employee"));

        return ut;
    }
}
