package com.example.petproject.Service;

import com.example.petproject.DAO.productRepository;
import com.example.petproject.Mapper.productMapper;
import com.example.petproject.Model.productModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {
    @Autowired
    productRepository productrepository;

    public List<productModel> getproductAll(){
        return productrepository.getproductAll();
    }

    public productModel getproducdetail(int id){
        productModel pp = new productModel();
        for (productModel p:productrepository.getproducdetail(id)){
           pp.setSize(p.getSize());
           pp.setCommodityName(p.getCommodityName());
           pp.setInventory(p.getInventory());
           pp.setCost(p.getCost());
           pp.setPrice(p.getPrice());
           pp.setDetail(p.getDetail());
           pp.setCommodityID(p.getCommodityID());
           pp.setCommodityKind(p.getCommodityKind());
        }
        return pp;
    }
}
