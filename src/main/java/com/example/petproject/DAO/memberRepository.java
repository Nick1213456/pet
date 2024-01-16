package com.example.petproject.DAO;

import com.example.petproject.Mapper.memberDataMapper;
import com.example.petproject.Model.RegistModel;
import com.example.petproject.Model.memberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class memberRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    memberDataMapper memberdatamapper;


    //新增一筆會員資料
    public void InsertDB(RegistModel registModel){
        String meter = registModel.getMeter();
        int m;
        if(meter.isEmpty()) {
            m = 0;
        }
        else{
            m = 0;
            m = Integer.parseInt(meter);
        }
        String sql = "INSERT INTO member (username,password,name,birth,cellphone,email,address,meter,adpExp)" +
                "VALUES('" + registModel.getRegistUsername() + "','" + registModel.getPassword() + "','" + registModel.getName() + "','" + registModel.getBirth() + "','" + registModel.getCellphone() + "','" + registModel.getEmail() + "','" + registModel.getAddress() + "'," + m + ",'" + registModel.getAdpExp() + "');";
        jdbcTemplate.update(sql);
    }

    //計算某帳號數量
    public long usernameCount(String username){
        String sql = "Select count(*) FROM member WHERE username=?";
        long result = jdbcTemplate.queryForObject(sql,Long.class,username);
        return result;
    }

    //計算某email數量
    public long emailCount(String email){
        String sql = "Select count(*) FROM member WHERE email=?";
        long result = jdbcTemplate.queryForObject(sql,Long.class,email);
        return result;
    }

    //取得密碼
    public String getPassword(String username,String email){
        String sql = "Select password FROM member WHERE email=? AND username=?";
        String password = jdbcTemplate.queryForObject(sql,String.class,email,username);
        return password;
    }

    //取得會員資料
    public List<memberData> memberData(String username){
        String sql="SELECT username,password,name,birth,cellphone,email,address,meter,adpExp,Intro FROM member WHERE username='"+username+"';";

        List<memberData> list = jdbcTemplate.query(sql,memberdatamapper);
        return list;
    }

    public void memberDataChange(memberData mD){
        String sql="UPDATE member SET username='"+mD.getUsername()+"',password='"+mD.getPassword()+"',name='"+mD.getName()+"',birth='"+mD.getBirth()+"',cellphone='"+mD.getCellphone()+"',email='"+mD.getEmail()+"',adpExp='"+mD.getAdpExp()+"',meter='"+mD.getMeter()+"',Intro='"+mD.getIntro()+"' WHERE username='"+mD.getUsername()+"';";
        jdbcTemplate.update(sql);
    }

}
