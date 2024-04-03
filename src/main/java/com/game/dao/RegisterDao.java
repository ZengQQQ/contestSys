package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Register;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterDao extends BaseDao<Register> {

    public RegisterDao(){
        super("register");
    }


    public boolean insert(String r_xuehao, String r_name, String r_major) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("r_id",null);
        hashMap.put("s_xuehao",r_xuehao);
        hashMap.put("r_name",r_name);
        hashMap.put("r_major",r_major);

        return super.insert(hashMap);
    }


    public List<Register> query(String s_xuehao, int start, int end) {
        Map<String,Object> hashMap = new HashMap<>();
        return super.query(Register.class, hashMap, start, end);
    }



    public static void main(String[] args) {
       RegisterDao tesdao = new RegisterDao();
       List<Register> lists;
       lists = tesdao.query("21110223",0,10);
       for(Register jj:lists){
           System.out.println(jj);
       }
    }

}
