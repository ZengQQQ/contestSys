package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.TeamDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.fixDomain.TeamFix;

import java.util.ArrayList;
import java.util.List;

public class TeamFixDao {

    UserDao userDao = new UserDao();
    TeamDao teamDao = new TeamDao();

    private Team model;

    private PageBean<TeamFix> pageBean = new PageBean<>();


    public void initPage(Team object){
        this.model=object;
        List<Team> total =teamDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public TeamFix singToFix(Team ele) {
        User tem = new User();
        tem.setU_acc(ele.getU_acc());
        User captain = userDao.querySingle(tem);
        User taget = new User();
        TeamUserMessage chain = new TeamUserMessage();
        chain.setJoin_status(1);
        List<User> member = userDao.leftQuery(User.class,"team",BaseDao.formList(ele,taget,chain),-1,-1);
        for(User e:member){
            e.setU_pwd(null);
        }
        captain.setU_pwd(null);
        return new TeamFix(ele.getT_id(),captain,member,ele.getT_name(),ele.getT_info(),ele.getT_curnum(),ele.getT_maxnum(),ele.getT_status());
    }

    public PageBean<TeamFix> queryByPage(Integer currentPage, Team object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<Team> temresult = new ArrayList<>();
        List<TeamFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=teamDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(Team ele :temresult){
            TeamFix teamFix = singToFix(ele);
            result.add(teamFix);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
