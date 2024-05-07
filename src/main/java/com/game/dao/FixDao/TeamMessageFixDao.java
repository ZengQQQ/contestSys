package com.game.dao.FixDao;

import com.game.bean.PageBean;

import com.game.dao.TeamDao;
import com.game.dao.TeamUserMessageDao;
import com.game.dao.UserDao;

import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.StallTeamMessageFix;
import com.game.domain.fixDomain.TeamFix;
import com.game.domain.fixDomain.TeamMessageFix;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TeamMessageFixDao {

    TeamDao teamDao = new TeamDao();
    UserDao userDao = new UserDao();
    TeamFixDao teamFIxDao = new TeamFixDao();
    TeamUserMessageDao messageDao = new TeamUserMessageDao();
    private TeamUserMessage model;
    private PageBean<TeamMessageFix> pageBean = new PageBean<>();




    public void initPage(TeamUserMessage object){
        this.model=object;
        List<TeamUserMessage> total =new ArrayList<>();
        total=messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }
    public void initPage(Integer totalsize){
        pageBean.setTotalSize(totalsize);
    }

    public PageBean<TeamMessageFix> queryPage(Integer currentPage, TeamUserMessage object){
        List<TeamUserMessage> temresult = new ArrayList<>();
        List<TeamMessageFix> result = new ArrayList<>();
        temresult=messageDao.query(object,-1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
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
    public PageBean<TeamMessageFix> queryTeamMemPage(Integer currentPage, User object){
        List<TeamUserMessage> temresult = new ArrayList<>();
        List<TeamMessageFix> result = new ArrayList<>();
        Team chain = new Team();
        TeamUserMessage target = new TeamUserMessage();
        Map<String,String> joinCondition = new LinkedHashMap<>();
        joinCondition.put("team","team.t_id=team_user_message.t_id");
        joinCondition.put("user","team.u_acc=user.u_acc");
        temresult=messageDao.leftQuery(TeamUserMessage.class,"team_user_message", BaseDao.formList(object,chain,target),joinCondition, -1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
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
    public PageBean<TeamMessageFix> queryMemTeamPage(Integer currentPage, User u){
        TeamUserMessage object = new TeamUserMessage();
        object.setU_acc(u.getU_acc());
        List<TeamUserMessage> temresult = new ArrayList<>();
        List<TeamMessageFix> result = new ArrayList<>();
        temresult=messageDao.query(object,-1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
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
