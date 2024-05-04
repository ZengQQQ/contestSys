package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.dao.StallDao;
import com.game.dao.TeamDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.TeamFix;

import java.util.ArrayList;
import java.util.List;

public class StallFixDao {

    StallDao stallDao =new StallDao();
    UserDao userDao = new UserDao();
    TeamDao teamDao = new TeamDao();
    ProjectDao projectDao = new ProjectDao();
    TeamFIxDao teamFIxDao =new TeamFIxDao();

    private PageBean<StallFix> pageBean = new PageBean<>();


    public void initPage(Integer currentPage, Stall object){
        List<Stall> total =stallDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public StallFix singToFix(Stall ele) {
        User u = new User();
        u.setU_acc(ele.getU_acc());
        User host = userDao.querySingle(u);
        User u1 = new User();
        StallMentorMessage chain =new StallMentorMessage();
        User mentor = userDao.leftQuery(User.class,"stall", BaseDao.formList(ele,u1,chain),-1,-1).get(0);
        Team t1 = new Team();
        StallTeamMessage chaint =new StallTeamMessage();
        Team team = teamDao.leftQuery(Team.class,"stall", BaseDao.formList(ele,t1,chaint),-1,-1).get(0);
        TeamFix teamFix = teamFIxDao.singToFix(team);
        Project p1 = new Project();
        StallProjectMessage chainp =new StallProjectMessage();
        Project project = projectDao.leftQuery(Project.class,"stall", BaseDao.formList(ele,p1,chainp),-1,-1).get(0);
        host.setU_pwd(null);
        mentor.setU_pwd(null);
        return new StallFix(ele.getSt_id(),mentor,teamFix,project,host,ele.getSt_name(),ele.getSt_info(),ele.getSt_status());
    }

    public PageBean<StallFix> queryByPage(Integer currentPage, Stall object){
        List<Stall> temresult = new ArrayList<>();
        List<StallFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=stallDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(Stall ele :temresult){
            StallFix stallFix = singToFix(ele);
            result.add(stallFix);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
