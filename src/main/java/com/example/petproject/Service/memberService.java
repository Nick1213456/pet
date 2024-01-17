package com.example.petproject.Service;

import com.example.petproject.DAO.memberRepository;
import com.example.petproject.Mapper.userTypeRowMapper;
import com.example.petproject.Model.RegistModel;
import com.example.petproject.Model.UserType;
import com.example.petproject.Model.memberData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
public class memberService {

    @Autowired
    memberRepository mR;
    @Autowired
    userTypeRowMapper rowmapper;


    @Autowired
    JdbcTemplate jdbcTemplate;



    //密碼登入
    public boolean passcheck(String username,String password){
        String sql = "SELECT count(*) FROM member WHERE username='"+username+"' AND password='"+password+"';";
        if(jdbcTemplate.queryForObject(sql,Long.class) == 1){
            return true;
        }
        else{
            return false;
        }
    }

    //判斷會員權限
    public String memberPer(String username){
        String sql = "SELECT username,Employee FROM member WHERE username='"+username+"';";
        List<UserType> list = jdbcTemplate.query(sql,rowmapper);
        if(list.isEmpty()){
            return "0";
        }
        else{
            return list.get(0).getEmployee();
        }
    }

    //註冊方法
    public int memberRegist(RegistModel registModel){
        int result; //0:新增成功 1:輸入密碼驗證錯誤 2:帳號已經存在 3:帳號帶有禁止關鍵字 4:
        String password = registModel.getPassword();
        String passwordcheck = registModel.getPasswordCheck();
        String username = registModel.getRegistUsername();
        if(!password.equals(passwordcheck)){
            result = 1;
        }
        else if(usernameCheck(username)){
            result = 2;
        }
        else if(username.equalsIgnoreCase("delete") || username.equalsIgnoreCase("drop") || username.equalsIgnoreCase("select") || username.equalsIgnoreCase("update")){
            result = 3;
        }
        else {
            mR.InsertDB(registModel);
            result = 0;
        }
        return result;
    }

    //得到帳號和電子信箱 回傳密碼
    public String returnPassword(String username,String email){
        String password;
        if(usernameCheck(username) && emailCheck(email)){
            password = mR.getPassword(username,email);
        }
        else{
            password = "帳號或電子信箱輸入錯誤，如有問題請洽詢客服人員";
        }
        return password;
    }

    //信箱是否存在檢查
    public boolean emailCheck(String email){
        boolean countResult;
        long result = mR.emailCount(email);
        if(result != 0){
            countResult = true;
        }
        else{
            countResult = false;
        }
        return countResult;
    }


    //帳號是否存在檢查
    public boolean usernameCheck(String username){
        boolean countResult;
        long result = mR.usernameCount(username);
        if(result != 0){
            countResult = true;
        }
        else{
            countResult = false;
        }
        return countResult;
    }

    //會員頁面Session檢查
    public boolean sessionCheck(HttpSession session){
        Object obj = session.getAttribute("username");
        if(obj != null){
            return true;
        }
        else{
            return false;
        }
    }

    //登出時清除session
    public void signOut(HttpSession session){
        session.invalidate();
    }

    //利用帳號取出會員資料
    public List<memberData> memberData(String username){
        return mR.memberData(username);
    }

    //寫入會員資料更改
    public String dataChange(memberData mD,String oldusername){
        return mR.memberDataChange(mD,oldusername);
    }


    public String outputPhoto(String membername){
        String memberSrc = "";
        try {
            Path imagePath = Paths.get("C:/temp/memberimg/"+membername+"/img_1.jpg");
            byte[] bytepath = Files.readAllBytes(imagePath);
            // 讀取圖片檔案
            memberSrc = ( "data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(bytepath));
        } catch (Exception e) {
//            e.printStackTrace();
            memberSrc = ("/images/close_icon.png");
        }
        return  memberSrc;
    }
}

