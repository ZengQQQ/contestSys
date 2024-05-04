package com.game.serve;

import com.game.dao.*;
import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.domain.Mentor;
import com.game.domain.Student;
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
        if (user.getU_acc()!=null && user.getU_pwd()!=null &&user.getU_identity()!=null) {
            User user2 = (new UserDao()).querySingle(user);
            if(user2!=null){
                if(user2.getU_status()==0){
                    return Result.success("用户登录成功");
                }else if(user2.getU_status()==1){
                    return Result.fail("用户登录失败","用户已注销");
                }else if(user2.getU_status()==2) {
                    return Result.fail("用户登录失败", "用户违规");
                }else {
                    return Result.fail("用户登录失败", "用户封禁");
                }
            }else{
                return Result.fail("用户登录失败", "账号或密码错误");
            }
        }else {
            return Result.fail("用户登录失败", "输入内容异常");
        }
    }

    public Result<String> administratorLogin(Administrator administrator){
        if(administrator.getA_acc()!=null&&administrator.getA_pwd()!=null){
            List<Administrator> administratorList =(new AdministratorDao()).query(administrator,-1,-1);
            if(!administratorList.isEmpty()){
                return Result.success("管理员登录成功");
            }else {
                return Result.fail("管理员登录失败", "账号或密码错误");
            }
        }else {
            return Result.fail("管理员登录失败", "输入内容异常");
        }
    }

}
