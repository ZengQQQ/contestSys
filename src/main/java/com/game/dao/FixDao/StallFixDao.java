package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.dao.StallDao;
import com.game.dao.TeamDao;
import com.game.dao.UserDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;

import java.util.ArrayList;
import java.util.List;

public class StallFixDao {

    StallDao stallDao =new StallDao();
    UserDao userDao = new UserDao();
    TeamDao teamDao = new TeamDao();
    ProjectDao projectDao = new ProjectDao();

    private PageBean<StallFix> pageBean = new PageBean<>();


    public void initPage(Integer currentPage, Stall object){
        List<Stall> total =stallDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public StallFix singToFix(Stall ele) {
        User host = userDao.querySingle(new User(null,ele.getU_acc(),null,null,null,null,null,null,null,null,null));
        User mentor = userDao.querySingle(new User(null,ele.getM_acc(),null,null,null,null,null,null,null,null,null));
        Team team = teamDao.querySingle(new Team(ele.getT_id(),null,null,null,null,null,null));
        Project project = projectDao.querySingle(new Project(ele.getP_id(),null,null,null,null,null,null,null,null));
        host.setU_pwd(null);
        mentor.setU_pwd(null);
        return new StallFix(ele.getSt_id(),mentor,team,project,host,ele.getSt_status());
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
