package com.example.petproject.Service;

import com.example.petproject.DAO.productRepository;
import com.example.petproject.Mapper.productMapper;
import com.example.petproject.Model.productModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
            try {
                Path imagePath = Paths.get("C:/temp/productimg/"+p.getCommodityID()+"/img_1.jpg");
                byte[] bytepath = Files.readAllBytes(imagePath);
                // 讀取圖片檔案
                pp.setImageBytes( "data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(bytepath));
            } catch (Exception e) {
//            e.printStackTrace();
                pp.setImageBytes("/images/close_icon.png");
            }
        }
        return pp;
    }

    //取得資料庫ID最小
    public int getproductMINID(){
        return productrepository.getproductMINID();
    }

    public void setproductditail(productModel pm,int id){
        productrepository.setproductditail(pm,id);
    }
}
