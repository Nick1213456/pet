package com.example.petproject.Mapper;


import com.example.petproject.Model.memberData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class memberDataMapper implements RowMapper {
    public memberData mapRow(ResultSet rs, int rowNum) throws SQLException {
        memberData memberdata = new memberData();
        memberdata.setUsername(rs.getString("username"));
        memberdata.setPassword(rs.getString("password"));
        memberdata.setName(rs.getString("name"));
        memberdata.setBirth(rs.getString("birth"));
        memberdata.setCellphone(rs.getString("cellphone"));
        memberdata.setEmail(rs.getString("email"));
        memberdata.setAddress(rs.getString("address"));
        memberdata.setMeter(rs.getString("meter"));
        memberdata.setAdpExp(rs.getString("adpExp"));

        return memberdata;
    }
}
