package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.StudentDao;
import com.game.dao.TeamApplicationDao;
import com.game.domain.Student;
import com.game.domain.TeamApplication;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class StudentServe extends StudentDao {
    private final PageBean<Student> pageBean = new PageBean<Student>();
    public boolean checkPassword(Student student){
        if (student.getS_xuehao()!=null && student.getS_pwd()!=null) {
            List<Student> studentList;
            studentList = this.query(student, -1, -1);
            return !studentList.isEmpty();
        }else {
            return false;
        }
    }
    public PageBean<Student> queryByPage(Integer currentPage,Student student){
        List<Student> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(student));
        result=query(student,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
    public boolean banStudentAccount(Student stu){
        if(stu.getS_status()!=1){
            HashMap<String,Object> newStatus = new HashMap<>();
            newStatus.put("s_status", 0);
            StudentDao studentDao = new StudentDao();
            return studentDao.update(newStatus, stu.toMap()) != 0;
        }else{
            System.out.println("该账号目前已经被封禁");
            return false;
        }
    }
    //解封学生账号
    public boolean unBanStudentAccount(Student stu){
        if(stu.getS_status()!=0){
            HashMap<String,Object> newStatus = new HashMap<>();
            newStatus.put("s_status", 1);
            StudentDao studentDao = new StudentDao();
            return studentDao.update(newStatus, stu.toMap()) != 0;
        }else{
            System.out.println("该账号处于正常状态");
            return false;
        }
    }

    //申请入队
    public boolean applyJoinTeam(TeamApplication teamApplication){
        if(teamApplication.getS_id()!=null &&teamApplication.getT_id()!=null){
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
