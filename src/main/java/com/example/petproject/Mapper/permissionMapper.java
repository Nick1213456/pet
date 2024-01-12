package com.example.petproject.Mapper;

import com.example.petproject.Model.UserType;
import com.example.petproject.Model.permissionModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class permissionMapper implements RowMapper {

    @Override
    public permissionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        permissionModel pM = new permissionModel();
        pM.setUsername(rs.getString("username"));
        pM.setEmployee(rs.getString("Employee"));

        return pM;
    }
}
