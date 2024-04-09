package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.StudentDao;
import com.game.dao.TeamApplicationDao;
import com.game.domain.Student;
import com.game.domain.Team;
import com.game.domain.TeamApplication;
import lombok.Getter;

import java.util.List;

@Getter
public class StudentServe extends StudentDao {
    private final PageBean<Student> pageBean = new PageBean<Student>(super.statistics());
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
        result=query(student,pageBean.getStart(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
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
