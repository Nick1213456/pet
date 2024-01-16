package com.example.petproject.Mapper;

import com.example.petproject.Model.orderModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class orderModelMapper implements RowMapper {

    @Override
    public orderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        orderModel oM = new orderModel();
        oM.setOrderNumber(rs.getInt("OrderNumber"));
        oM.setGuestName(rs.getString("GuestName"));
        oM.setOrderAmount(rs.getInt("OrderAmount"));
        oM.setOrderDate(rs.getDate("OrderDate"));
        oM.setShippedDate(rs.getDate("ShippedDate"));
        oM.setStatus(rs.getString("Status"));
        oM.setNote(rs.getString("Note"));
        oM.setPicker(rs.getString("Picker"));
        return oM;
    }
}
