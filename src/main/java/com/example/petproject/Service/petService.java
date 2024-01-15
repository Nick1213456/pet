package com.example.petproject.Service;

import com.example.petproject.DAO.petRepository;
import com.example.petproject.Mapper.petMapper;
import com.example.petproject.Model.petModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class petService {
    @Autowired
    petRepository petRepository;

    public List<petModel> getpetAll(){
        return petRepository.getpetAll();
    }

    public int insertPetData(petModel pM){
        return petRepository.insertPetData(pM);

    }
}
