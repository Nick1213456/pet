package com.example.petproject.DAO;

import com.example.petproject.Mapper.cartMapper;
import com.example.petproject.Mapper.cartMapper2;
import com.example.petproject.Model.cartModel;
import com.example.petproject.Model.cartModel2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class cartRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //找訂單ID
    public int Check_order(String username){
        try {
            int orderNumber = jdbcTemplate.queryForObject("SELECT orderNumber FROM `order` WHERE GuestName='"+username+"' AND Status=1", int.class);
            return orderNumber;
        } catch (Exception e) {
            // 當查詢未找到結果時的處理邏輯
            return 0;
        }
    }

    public int Check_orderdetaill(int id,int onum){
        try {
            int CommodityID = jdbcTemplate.queryForObject("SELECT CommodityID FROM `orderdetail` WHERE CommodityID="+id+" AND OrderNumber="+onum, int.class);
            return 1;
        } catch (Exception e) {
            // 當查詢未找到結果時的處理邏輯
            return 0;
        }
    }

    public int getPrice(int id){
        return jdbcTemplate.queryForObject("SELECT Price FROM commoditylist WHERE CommodityID="+id,int.class);
    }

    public int getComQuantity(int id,int Onum){
        return jdbcTemplate.queryForObject("SELECT ComQuantity FROM orderdetail WHERE CommodityID="+id+" and OrderNumber="+Onum,int.class);
    }

    public void updateOrderAmount(String username){
        int oid = Check_order(username);
        try{
            jdbcTemplate.update("UPDATE `order`SET OrderAmount=(SELECT sum(Amount) FROM orderdetail WHERE OrderNumber=?) WHERE OrderNumber=?",oid,oid);
        }catch(Exception e){
            jdbcTemplate.update("UPDATE `order`SET OrderAmount=0 WHERE OrderNumber=?",oid);
        }
    }
    public void updateOrderAmount(int oid){
        try{
            jdbcTemplate.update("UPDATE `order`SET OrderAmount=(SELECT sum(Amount) FROM orderdetail WHERE OrderNumber=?) WHERE OrderNumber=?",oid,oid);
        }catch(Exception e){
            jdbcTemplate.update("UPDATE `order`SET OrderAmount=0 WHERE OrderNumber=?",oid);
        }

    }



    public List<cartModel> getcartfrom_username(String username){
        int orderNumber = Check_order(username);
        if (orderNumber==0){
            List<cartModel> list = new ArrayList<>();
            return list;
        }else {
            return jdbcTemplate.query("SELECT o.*,c.CommodityName,c.Size,c.Price FROM `orderdetail` o " +
                    "JOIN commoditylist c JOIN member m ON c.CommodityID=o.CommodityID " +
                    "WHERE o.OrderNumber=? AND m.username=?",new cartMapper(),orderNumber,username);
        }
    }

    public void add_order(String username, int id, int num){
        jdbcTemplate.update("INSERT INTO `petdb`.`order` ( `GuestName`,Status) VALUES ('"+username+"',"+num+");");
        int onum = Check_order(username);
        int price = num * getPrice(id);
        jdbcTemplate.update("INSERT INTO orderdetail (OrderNumber,CommodityID,ComQuantity,Amount) VALUES (?,?,?,?)",onum,id,num,price);
        updateOrderAmount(username);
    }

    public void add_orderdetail(String username, int id, int num){
        int onum = Check_order(username);
        int price = num * getPrice(id);
        jdbcTemplate.update("INSERT INTO orderdetail (OrderNumber,CommodityID,ComQuantity,Amount) VALUES (?,?,?,?)",onum,id,num,price);
        updateOrderAmount(username);
    }

    public void add_orderdetailNum(String username, int id, int num){
        int onum = Check_order(username);
        int AllComQuantity = num + getComQuantity(id,onum);
        int price = AllComQuantity * getPrice(id);
        jdbcTemplate.update("update orderdetail set ComQuantity=?,Amount=? WHERE OrderNumber=? AND CommodityID=?",AllComQuantity,price,onum,id);
        updateOrderAmount(username);
    }

    public void delete_orderdetail(int id, int pid){
        jdbcTemplate.update("DELETE FROM orderdetail WHERE CommodityID=? AND OrderNumber=?",id,pid);

        List<cartModel2>deleteorder=jdbcTemplate.query("select * from orderdetail where OrderNumber=?",new cartMapper2(),pid);

            if (deleteorder.size() == 0) {
                jdbcTemplate.update("delete from `order` where OrderNumber=? and status=1", pid);
            }

}

public void quantity_orderdetail(int num,int price,int id,int pid){
    jdbcTemplate.update("update orderdetail SET ComQuantity=?,Amount=? WHERE CommodityID=? AND OrderNumber=?",num,price,id,pid);
    updateOrderAmount(pid);
}

}
