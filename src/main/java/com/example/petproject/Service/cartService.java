package com.example.petproject.Service;

import com.example.petproject.DAO.cartRepository;
import com.example.petproject.Mapper.cartMapper;
import com.example.petproject.Model.cartModel;
import jakarta.servlet.http.HttpSession;
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
            cartRepository.addorder(username,id,num);
        }else{
            if (cartRepository.Check_orderdetaill(id,ordernum)==0){
//                有訂單但細項沒有
                cartRepository.addorderdetail(username,id,num);
            }else{
//                有細項所以直接加數量
                cartRepository.addorderdetailNum(username,id,num);
            }
        }
        return 1;
    }
}
