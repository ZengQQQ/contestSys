package com.game.serve;

import com.game.dao.*;
import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.domain.Mentor;
import com.game.domain.Student;
import com.game.utils.JWTUtils;
import com.game.utils.Result;

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
    public Result<String> login(User user){
        System.out.println(user);
        if (user.getU_acc()!=null && user.getU_pwd()!=null ) {
            User user2 = (new UserDao()).querySingle(user);
            if(user2!=null){
                Integer status;
                if(user2.getU_identity()==0){
                    Student student = new Student();
                    student.setS_acc(user2.getU_acc());
                    student = new StudentDao().querySingle(student);
                    status = student.getS_status();
                }else {
                    Mentor mentor = new Mentor();
                    mentor.setM_acc(user.getU_acc());
                    mentor=new MentorDao().querySingle(mentor);
                    status = mentor.getM_status();
                }
                if(user2.getU_status()==0&& status==0){
                    user2.setU_pwd(null);
                    String token= JWTUtils.encodeJwt(user2);;
                    return Result.success(token);
                }else if(user2.getU_status()==1){
                    return Result.fail("用户登录失败,用户已注销",null);
                }else if(user2.getU_status()==2) {
                    return Result.fail("用户登录失败,用户违规", null);
                }else {
                    return Result.fail("用户登录失败,用户注册表封禁", null);
                }
            }else{
                return Result.fail("用户登录失败,账号或密码错误", null);
            }
        }else {
            return Result.fail("用户登录失败,输入内容异常", null);
        }
    }

    public Result<String> administratorLogin(Administrator administrator){
        if(administrator.getA_acc()!=null&&administrator.getA_pwd()!=null){
            List<Administrator> administratorList =(new AdministratorDao()).query(administrator,-1,-1);
            if(!administratorList.isEmpty()){
                User user =  new User();
                user.setU_acc("1111");
                user.setU_name("管理员");
                String token=JWTUtils.encodeJwt(user);
                return Result.success(token);
            }else {
                return Result.fail("管理员登录失败,账号或密码错误", null);
            }
        }else {
            return Result.fail("管理员登录失败,输入内容异常", null);
        }
    }

}
