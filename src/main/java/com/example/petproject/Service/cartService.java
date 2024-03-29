package com.example.petproject.Service;

import com.example.petproject.DAO.cartRepository;
import com.example.petproject.Model.cartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class cartService {
    @Autowired
    cartRepository cartRepository;
    public List<cartModel> getcartfrom_username(String username){
        return cartRepository.getcartfrom_username(username);
    }

    public int joincart(int id, int num,String username){
        int ordernum = cartRepository.Check_order(username);
        if (ordernum==0){
//            完全沒訂單
            cartRepository.add_order(username,id,num);
        }else{
            if (cartRepository.Check_orderdetaill(id,ordernum)==0){
//                有訂單但細項沒有
                cartRepository.add_orderdetail(username,id,num);
            }else{
//                有細項所以直接加數量
                cartRepository.add_orderdetailNum(username,id,num);
            }
        }
        return 1;
    }

    public void deletecart(int id,String username){
        int pid = cartRepository.Check_order(username);
        cartRepository.delete_orderdetail(id,pid);
    }

    public void update_quantity(int id,String username,String method){
        int pid = cartRepository.Check_order(username);
        if (method.equals("+")){
            int qnum = cartRepository.getComQuantity(id,pid)+1;
            int price = qnum * cartRepository.getPrice(id);
            cartRepository.quantity_orderdetail(qnum,price,id,pid);
        }else if(method.equals("-")) {
            int qnum = cartRepository.getComQuantity(id,pid)-1;
            int price = qnum * cartRepository.getPrice(id);
            cartRepository.quantity_orderdetail(qnum,price,id,pid);
            if (qnum<1){
                cartRepository.delete_orderdetail(id,pid);
            }
        }
    }

}
