package com.game.serve;

import com.game.dao.*;
import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.domain.Mentor;
import com.game.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginControlServe {

    /**
     * 学生登录
     * @param user 用户：账号 密码
     * @return 业务消息：code message
        code 0:失败 1:成功
        message 提示消息
     */
    public Map<String,String> studentLogin(User user){
        Map<String,String> map = new HashMap<>();
        if (user.getU_acc()!=null && user.getU_pwd()!=null) {
            User user1 = new User();
            user1.setU_acc(user.getU_acc());
            user1.setU_pwd(user.getU_pwd());
            User user2 = (new UserDao()).querySingle(user1);
            if(user2!=null){
                if(user2.getU_status()!=0){
                    map.put("code","1");
                    map.put("message","用户成功登录");
                }else {
                    map.put("code","0");
                    map.put("message","用户封禁中");
                }
            }else{
                map.put("code","0");
                map.put("message","账号或密码错误");
            }
        }else {
            map.put("code","0");
            map.put("message","输入内容错误");
        }
        return map;
    }

    public Map<String,String> administratorLogin(Administrator administrator){
        Map<String,String> map =new HashMap<>();
        if(administrator.getA_acc()!=null&&administrator.getA_pwd()!=null){
            // todo 为什么新建一个对象
            administrator.setA_acc(administrator.getA_acc());
            administrator.setA_pwd(administrator.getA_pwd());
            List<Administrator> administratorList =(new AdministratorDao()).query(administrator,-1,-1);
            if(!administratorList.isEmpty()){
                map.put("code","1");
                map.put("message","管理员登录成功");
            }else {
                map.put("code","0");
                map.put("message","账号或密码错误");
            }
        }else {
            map.put("code","0");
            map.put("message","输入内容错误");
        }
        return map;
    }

    public Map<String, String> mentorLogin(User user){
        Map<String,String> map = new HashMap<>();
        if (user.getU_acc()!=null && user.getU_pwd()!=null) {
            User user1 = new User();
            user1.setU_acc(user.getU_acc());
            user1.setU_pwd(user.getU_pwd());
            User user2 = (new UserDao()).querySingle(user1);
            if(user2!=null){
                if(user2.getU_status()!=0){
                    map.put("code","1");
                    map.put("message","教师成功登录");
                }else {
                    map.put("code","0");
                    map.put("message","用户封禁中");
                }
            }else{
                map.put("code","0");
                map.put("message","账号或密码错误");
            }
        }else {
            map.put("code","0");
            map.put("message","输入错误内容");
        }
        return map;
    }


}
