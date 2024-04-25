package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.StudentDao;
import com.game.dao.TeamApplicationDao;
import com.game.dao.UserDao;
import com.game.domain.User;
import com.game.domain.secondary.userDomain.Student;
import com.game.domain.secondary.teamMessageDomain.TeamApplication;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
public class StudentServe extends StudentDao {
    public boolean checkPassword(String s_xuehao,String u_pwd){
        if (s_xuehao!=null && u_pwd!=null) {
            Student student =new Student();
            User user = new User();
            List<Object> list = new ArrayList<>();
            student.setS_xuehao(s_xuehao);
            user.setU_pwd(u_pwd);
            list.add(student);
            list.add(user);
            List<Student> studentList;
            studentList = this.leftQuery(list, -1, -1);
            return !studentList.isEmpty();
        }else {
            return false;
        }
    }



    //申请入队
    public boolean applyJoinTeam(TeamApplication teamApplication){
        if(teamApplication.getU_id()!=null &&teamApplication.getT_id()!=null){
            TeamApplicationDao teamApplicationDao = new TeamApplicationDao();
            return teamApplicationDao.insert(teamApplication);
        }else{
            System.out.println("申请入队失败");
            return false;
        }
    }

    //邀请入队，队长才能做的，邀请某一个人,该人收到入队的请求，用状态设置为邀请中,与上个函数一样即可，具体数据传入为页面控制
    public boolean inviteSomeoneToTeam(TeamApplication teamApplication){
        return false;
    }

}
