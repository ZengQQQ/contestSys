package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.dao.StallDao;
import com.game.dao.StallProjectMessageDao;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.StallMentorMessageFix;
import com.game.domain.fixDomain.StallProjectMessageFix;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StallProjectMessageFixDao {

    ProjectDao projectDao =new ProjectDao();
    StallDao stallDao = new StallDao();
    StallFixDao stallFixDao = new StallFixDao();
    StallProjectMessageDao messageDao = new StallProjectMessageDao();
    private StallProjectMessage model;
    private PageBean<StallProjectMessageFix> pageBean = new PageBean<>();


    public void initPage( StallProjectMessage object){
        this.model= object;
        List<StallProjectMessage> total =new ArrayList<>();
        total=messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallProjectMessageFix> queryByPage(Integer currentPage, StallProjectMessage object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<StallProjectMessage> temresult = new ArrayList<>();
        List<StallProjectMessageFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=messageDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(StallProjectMessage s :temresult){
            Stall tem =  new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            Project p =new Project();
            p.setP_id(s.getP_id());
            Project project= projectDao.querySingle(p);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallProjectMessageFix em = new StallProjectMessageFix(s.getSpm_id(),stallFix,project,s.getSpm_info(),s.getSpm_pass(),s.getSpm_status(),s.getSpm_dct(),s.getSpm_time(),s.getStall_view(),s.getProject_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
    public void initPage( Integer size){
        pageBean.setTotalSize(size);
    }
    public PageBean<StallProjectMessageFix> queryProjectPage(Integer currentPage, User object){
        List<StallProjectMessage> temresult = new ArrayList<>();
        List<StallProjectMessageFix> result = new ArrayList<>();
        Stall chain = new Stall();
        StallTeamMessage target = new StallTeamMessage();
        Map<String,String> joinCondition = new LinkedHashMap<>();
        joinCondition.put("project","project.p_id=stall_project_message.p_id");
        joinCondition.put("user","project.u_acc=user.u_acc");
        temresult=messageDao.leftQuery(StallProjectMessage.class,"stall_project_message", BaseDao.formList(object,chain,target),joinCondition, -1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
        for(StallProjectMessage s :temresult){
            Stall tem =  new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            Project p =new Project();
            p.setP_id(s.getP_id());
            Project project= projectDao.querySingle(p);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallProjectMessageFix em = new StallProjectMessageFix(s.getSpm_id(),stallFix,project,s.getSpm_info(),s.getSpm_pass(),s.getSpm_status(),s.getSpm_dct(),s.getSpm_time(),s.getStall_view(),s.getProject_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
    public PageBean<StallProjectMessageFix> queryStallPage(Integer currentPage, User object){
        List<StallProjectMessage> temresult = new ArrayList<>();
        List<StallProjectMessageFix> result = new ArrayList<>();
        Stall chain = new Stall();
        StallTeamMessage target = new StallTeamMessage();
        Map<String,String> joinCondition = new LinkedHashMap<>();
        joinCondition.put("stall","stall.st_id=stall_project_message.st_id");
        joinCondition.put("user","stall.u_acc=user.u_acc");
        temresult=messageDao.leftQuery(StallProjectMessage.class,"stall_project_message", BaseDao.formList(object,chain,target),joinCondition, -1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
        for(StallProjectMessage s :temresult){
            Stall tem =  new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            Project p =new Project();
            p.setP_id(s.getP_id());
            Project project= projectDao.querySingle(p);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallProjectMessageFix em = new StallProjectMessageFix(s.getSpm_id(),stallFix,project,s.getSpm_info(),s.getSpm_pass(),s.getSpm_status(),s.getSpm_dct(),s.getSpm_time(),s.getStall_view(),s.getProject_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }

}
