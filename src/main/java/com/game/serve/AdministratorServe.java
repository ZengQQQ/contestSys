package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.*;
import com.game.dao.base.BaseDao;
import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.domain.Work;
import com.game.domain.secondary.teamDomain.Team;
import com.game.domain.secondary.teamMessageDomain.TeamApplication;
import com.game.domain.secondary.teamMessageDomain.TeamInvite;
import com.game.domain.secondary.userDomain.College;
import com.game.domain.secondary.userDomain.Mentor;
import com.game.domain.secondary.userDomain.Student;
import com.game.domain.secondary.workDomain.Competition;
import com.game.domain.secondary.workDomain.Project;
import com.game.domain.secondary.workDomain.Task;

import java.util.List;


public class AdministratorServe {

    AdministratorDao administratorDao= new AdministratorDao();
    CollegeDao collegeDao = new CollegeDao();
    CompetitionDao competitionDao = new CompetitionDao();
    MentorDao mentorDao = new MentorDao();
    ProjectDao projectDao = new ProjectDao();
    StudentDao studentDao = new StudentDao();
    TaskDao taskDao =new TaskDao();
    TeamApplicationDao teamApplicationDao = new TeamApplicationDao();
    TeamDao teamDao =new TeamDao();
    TeamInviteDao teamInviteDao = new TeamInviteDao();
    TeamMentorDao teamMentorDao = new TeamMentorDao();
    TeamUserDao teamUserDao =new TeamUserDao();
    UserDao userDao = new UserDao();
    WorkDao workDao = new WorkDao();

    public PageBean<College>  collegeQuery(Integer currentPage,College ele){
        return collegeDao.queryByPage(currentPage,ele);
    }
    public PageBean<Competition>  competitionQuery(Integer currentPage, Competition ele){
        return competitionDao.queryByPage(currentPage,ele);
    }
    public PageBean<Mentor>  mentorQuery(Integer currentPage,Mentor ele){
        return mentorDao.queryByPage(currentPage,ele);
    }
    public PageBean<Project>  projectQuery(Integer currentPage, Project ele){
        return projectDao.queryByPage(currentPage,ele);
    }
    public PageBean<Student>  studentQuery(Integer currentPage, Student ele){
        return studentDao.queryByPage(currentPage,ele);
    }
    public PageBean<Task>  taskQuery(Integer currentPage, Task ele){
        return taskDao.queryByPage(currentPage,ele);
    }
    public PageBean<TeamApplication>  teamApplicationQuery(Integer currentPage, TeamApplication ele){
        return teamApplicationDao.queryByPage(currentPage,ele);
    }
    public PageBean<Team>  teamQuery(Integer currentPage, Team ele){
        return teamDao.queryByPage(currentPage,ele);
    }
    public PageBean<TeamInvite>  teamInviteQuery(Integer currentPage, TeamInvite ele){
        return teamInviteDao.queryByPage(currentPage,ele);
    }
    public PageBean<User>  userQuery(Integer currentPage, User ele){
        return userDao.queryByPage(currentPage,ele);
    }
    public PageBean<Work>  workQuery(Integer currentPage, Work ele){
        return workDao.queryByPage(currentPage,ele);
    }


    public int banStudentUser(Student stu){
        User user = new User();
        int count=0;
        List<User> userList = userDao.leftQuery(BaseDao.formList(user,stu),-1,-1);
        for(User ele:userList ) {
            if (ele.getU_status() == 0) {
                User user1 = new User();
                user1.setU_status(0);
                count++;
            } else {
                System.out.println("该账号处于封禁状态");
                count++;
            }
        }
        return count;
    }
    //解封学生账号
    public int unBanStudentUser(Student stu){
        User user = new User();
        int count=0;
        List<User> userList = userDao.leftQuery(BaseDao.formList(user,stu),-1,-1);
        for(User ele:userList ) {
            if (ele.getU_status() != 0) {
                User user1 = new User();
                user1.setU_status(1);
                count++;
            } else {
                System.out.println("该账号处于正常状态");
                count++;
            }
        }
        return count;
    }

  //添加比赛信息


    //删除帖子
    public boolean deletePostings(){
        return true;
    }



}
