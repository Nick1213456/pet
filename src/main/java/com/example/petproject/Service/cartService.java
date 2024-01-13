package com.example.petproject.Service;

import com.example.petproject.Mapper.cartMapper;
import com.example.petproject.Mapper.orderMapper;
import com.example.petproject.Model.cartModel;
import com.example.petproject.Model.orderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class cartService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<cartModel> getAllcart(){
        String sql="SELECT orderdetail.OrderNumber, orderdetail.CommodityID, orderdetail.ComQuantity, (commoditylist.Price * orderdetail.ComQuantity) as Amount  " +
                "FROM orderdetail " +
                "INNER JOIN commoditylist ON orderdetail.CommodityID = commoditylist.CommodityID;";

        return jdbcTemplate.query(sql,new cartMapper());
    }
    public List<orderModel>getorder(){
        String sqlorder="SELECT * from `order`";
        return jdbcTemplate.query(sqlorder,new orderMapper());
    }
    public int getordernum(int id){
        String sql="SELECT * from `order` WHERE Status=1 AND GuestUID=?";
        List<orderModel> od=jdbcTemplate.query(sql,new orderMapper(),id);

        if(od.isEmpty()){
            return 0;
        }
        else {
            return od.get(0).getOrderNumber();
        }

    }
    public String getorderdetailnum(int id){
        String sql="SELECT * FROM `orderdetail` WHERE Ordernumber = ?";
        List<cartModel> om=jdbcTemplate.query(sql,new cartMapper(),id);
        if(om.isEmpty()){
            return "0";
        }else {
            return "1";
        }
    }
    public String getcartid(int id,int username){

        boolean repeat=false;
        int guestUid = username;
        List <cartModel>cartMapper = getAllcart();
        System.out.println(cartMapper.isEmpty());
        System.out.println(getordernum(guestUid));
        System.out.println(getordernum(guestUid)+"1234567890");
        if(getordernum(guestUid)==0){
            jdbcTemplate.update("INSERT INTO `petdb`.`order` ( `GuestUID`,Status) VALUES ( "+guestUid+",'1');");
            int onum = jdbcTemplate.queryForObject("select OrderNumber from `order` where GuestUID = "+guestUid,int.class);
            jdbcTemplate.update("INSERT INTO orderdetail (OrderNumber,CommodityID,ComQuantity) VALUES ("+onum+","+id+",1)");
        }
        else {
            int onum1= jdbcTemplate.queryForObject("select OrderNumber from `order` where Status=1 AND GuestUID = "+guestUid,int.class);
            for (cartModel cm : cartMapper) {
                System.out.println("這是判斷是否加1");
                System.out.println(cartMapper.isEmpty());
                System.out.println(id);
                System.out.println(cm.getCommodityID());

                if (id==cm.getCommodityID() && onum1 == cm.getOrderNumber()) {
                    System.out.println("比對相同");
                    int count;
                    count=cm.getComQuantity();
                    count++;
                    jdbcTemplate.update("update orderdetail set ComQuantity=" + count + " where OrderNumber="+onum1+" and CommodityID=" + id);
                    System.out.println("update orderdetail set ComQuantity=" + count + " where CommodityID='" + id + "'");
                    repeat=true;
                    break;
                }
            }
            if (repeat==false){
                int onum = jdbcTemplate.queryForObject("select OrderNumber from `order` where GuestUID = "+guestUid+" and Status=1",int.class);
                jdbcTemplate.update("INSERT INTO orderdetail (OrderNumber,CommodityID,ComQuantity) VALUES ("+onum+","+id+",1)");
            }
        }
        return "addcartQuantity";
    }
    public String aadelete(String id,int ordernumber){
        jdbcTemplate.update("Delete FROM orderdetail where  CommodityID='"+id+"'AND OrderNumber='"+ordernumber+"';");

        if(getorderdetailnum(ordernumber).equals("0")){
            jdbcTemplate.update("DELETE  FROM `order` WHERE  OrderNumber='"+ordernumber+"'; ");
        }

        return "delete";
    }
    public String bbdelete(int id,int ordernum) {

        List<cartModel> cartMapper = getAllcart();
        for (cartModel cm : cartMapper) {
            if (cm.getCommodityID()==id && cm.getOrderNumber()==ordernum){
                int count = cm.getComQuantity()-1;
                if (count>=1){
                    jdbcTemplate.update("update orderdetail set ComQuantity=" + count + " where OrderNumber="+ordernum+" and CommodityID=" + id );
                }else {
                    jdbcTemplate.update("DELETE FROM orderdetail WHERE OrderNumber="+ordernum+" and CommodityID=" + id );
                }
            }
        }




//        List<cartModel> cartMapper = getAllcart();
//            for (cartModel cm : cartMapper) {
//                if (id.equals(String.valueOf(cm.getCommodityID()))) {
//                    int count = cm.getComQuantity();
//                    if (count > 1) {
//                        count--;
//                        jdbcTemplate.update("update orderdetail set ComQuantity=" + count + " where OrderNumber="+ordernum+" and CommodityID='" + id + "'");
//                    }else {
//                        jdbcTemplate.update("DELETE FROM orderdetail WHERE OrderNumber="+ordernum+" and CommodityID='" + id + "'");
//                    }
//                    break;
//                }
//            }
        return "deleteOne";
    }
    public String plus(int plus,int ordernum){

        List<cartModel> cartMapper = getAllcart();
        for (cartModel cm : cartMapper) {
            if (cm.getCommodityID()==plus && cm.getOrderNumber()==ordernum){
                int count = cm.getComQuantity()+1;
                jdbcTemplate.update("update orderdetail set ComQuantity=" + count + " where OrderNumber="+ordernum+" and CommodityID=" + plus );
            }
        }





//        List<cartModel> cartMapper = getAllcart();
//        for (cartModel cm : cartMapper) {
//            if (plus.equals(String.valueOf(cm.getCommodityID()))) {
//                int count = cm.getComQuantity();
//                if (count > 0) {
//                    count++;
//                    jdbcTemplate.update("update orderdetail set ComQuantity=" + count + " where OrderNumber="+ordernum+" and CommodityID='" + plus + "'");
//                }
//            }
//        }
        return "plusone";
    }

    public String ccdelete(int username){
        int aa = getordernum(username);
        jdbcTemplate.update("DELETE FROM orderdetail WHERE OrderNumber="+aa);
        jdbcTemplate.update("DELETE FROM `order` where OrderNumber="+aa);
        return "delete";
    }
}
