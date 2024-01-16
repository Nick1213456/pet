package com.example.petproject.Mapper;

import com.example.petproject.Model.orderModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class orderMapper implements RowMapper<orderModel> {

    @Override
    public orderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        orderModel om= new orderModel();
        om.setOrderNumber(rs.getInt("OrderNumber"));
        om.setGuestName(rs.getString("GuestName"));
        om.setOrderAmount(rs.getInt("OrderAmount"));
        om.setOrderDate(rs.getDate("OrderDate"));
        om.setStatus(rs.getInt("Status"));
        om.setShippedDate(rs.getDate("ShippedDate"));
        om.setPicker(rs.getString("Picker"));
        om.setShipMethod(rs.getString("ShipMethod"));
        om.setNote(rs.getString("Note"));
        return om;
    }
}
