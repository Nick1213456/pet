package com.example.petproject.Service;

import com.example.petproject.DAO.memberRepository;
import com.example.petproject.DAO.petRepository;
import com.example.petproject.Mapper.petMapper;
import com.example.petproject.Model.memberData;
import com.example.petproject.Model.petModel;
import com.example.petproject.Model.productModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

@Service
public class petService {
    @Autowired
    petRepository petRepository;
    @Autowired
    memberRepository mR;

    public List<petModel> getpetAll(){
        return petRepository.getpetAll();
    }

    public int insertPetData(petModel pM,String username){
        return petRepository.insertPetData(pM,username);

    }

    public petModel getpetdetail(int id){
        petModel pm = new petModel();
        for (petModel p:petRepository.getpetAllForid(id)){
            pm.setUid(p.getUid());
            pm.setPetName(p.getPetName());
            pm.setGender(p.getGender());
            pm.setLigation(p.getLigation());
            pm.setHairLength(p.getHairLength());
            pm.setHairColor(p.getHairColor());
            pm.setAge(p.getAge());
            pm.setWeight(p.getWeight());
            pm.setVariety(p.getVariety());
            pm.setKind(p.getKind());
            pm.setN_Adopted(p.getN_Adopted());
            pm.setAdopted(p.getAdopted());
            pm.setChip(p.getChip());
            pm.setAd_Name(p.getAd_Name());
            pm.setRemark(p.getRemark());

            try {
                Path imagePath = Paths.get("C:/temp/petimg/"+p.getUid()+"/img_1.jpg");
                byte[] bytepath = Files.readAllBytes(imagePath);
                // 讀取圖片檔案
                pm.setImageBytes( "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(bytepath));
            } catch (Exception e) {
//            e.printStackTrace();//紅字錯誤
                pm.setImageBytes("/images/close_icon.png");
            }
        }
        return pm;
    }

    //使用寵物ID抓取會員資料
    public List<memberData> petOwner(int id) {
        //id>>>username
        List<petModel> petlist = petRepository.getpetAllForid(id);
        String ownerUsername = null;
        for (petModel petmodel : petlist) {
            ownerUsername = petmodel.getAd_Name();
        }
        //username>>>memberData
        List<memberData> list = mR.memberData(ownerUsername);

        return list;
    }
}
