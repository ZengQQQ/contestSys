package com.game.dao.FixDao;

import com.game.bean.PageBean;

import com.game.dao.TeamDao;
import com.game.dao.TeamUserMessageDao;
import com.game.dao.UserDao;

import com.game.domain.Team;
import com.game.domain.TeamUserMessage;
import com.game.domain.User;
import com.game.domain.fixDomain.TeamFix;
import com.game.domain.fixDomain.TeamMessageFix;

import java.util.ArrayList;
import java.util.List;

public class TeamMessageFixDao {

    TeamDao teamDao = new TeamDao();
    UserDao userDao = new UserDao();
    TeamFixDao teamFIxDao = new TeamFixDao();
    TeamUserMessageDao messageDao = new TeamUserMessageDao();
    private TeamUserMessage model;
    private PageBean<TeamMessageFix> pageBean = new PageBean<>();


    public void initPage(TeamUserMessage object){
        this.model=object;
        List<TeamUserMessage> total =messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<TeamMessageFix> queryByPage(Integer currentPage, TeamUserMessage object){
        if(!object.equals(model)){
            initPage(object);
        }
        List<TeamUserMessage> temresult = new ArrayList<>();
        List<TeamMessageFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=messageDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(TeamUserMessage s :temresult){
            User tem = new User();
            tem.setU_acc(s.getU_acc());
            User user = userDao.querySingle(tem);
            Team temt = new Team();
            temt.setT_id(s.getT_id());
            Team team= teamDao.querySingle(temt);
            TeamFix teamFix = teamFIxDao.singToFix(team);
            TeamMessageFix em = new TeamMessageFix(s.getTsm_id(),teamFix,user,s.getTsm_info(),s.getTsm_status(),s.getTsm_pass(),s.getTsm_dct(),s.getTsm_time());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
