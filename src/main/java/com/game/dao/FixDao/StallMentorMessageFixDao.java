package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.StallDao;
import com.game.dao.StallMentorMessageDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.StallMentorMessageFix;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StallMentorMessageFixDao {

    UserDao userDao = new UserDao();
    StallDao stallDao = new StallDao();
    StallFixDao stallFixDao = new StallFixDao();
    private StallMentorMessage model;
    StallMentorMessageDao messageDao = new StallMentorMessageDao();
    private PageBean<StallMentorMessageFix> pageBean = new PageBean<>();


    public void initPage( StallMentorMessage object){
        this.model=object;
        List<StallMentorMessage> total =new ArrayList<>();
        total=messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public void initPage( Integer size){
        pageBean.setTotalSize(size);
    }

    public PageBean<StallMentorMessageFix> queryByPage(Integer currentPage, StallMentorMessage object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<StallMentorMessage> temresult = new ArrayList<>();
        List<StallMentorMessageFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=messageDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(StallMentorMessage s :temresult){
            Stall tem =  new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            User ele = new User();
            ele.setU_acc(s.getU_acc());
            User mentor = userDao.querySingle(ele);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallMentorMessageFix em = new StallMentorMessageFix(s.getSmm_id(),stallFix,mentor,s.getSmm_info(),s.getSmm_pass(),s.getSmm_status(),s.getSmm_dct(),s.getSmm_time(),s.getStall_view(),s.getMentor_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }


    public PageBean<StallMentorMessageFix> queryMentorPage(Integer currentPage, User object){
        List<StallMentorMessage> temresult = new ArrayList<>();
        List<StallMentorMessageFix> result = new ArrayList<>();
        StallTeamMessage target = new StallTeamMessage();
        Map<String,String> joinCondition = new LinkedHashMap<>();
        joinCondition.put("user","stall_mentor_message.u_acc=user.u_acc");
        joinCondition.put("mentor","mentor.m_acc=user.u_acc");
        temresult=messageDao.leftQuery(StallMentorMessage.class,"stall_mentor_message", BaseDao.formList(object,target),joinCondition, -1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
        for(StallMentorMessage s :temresult){
            Stall tem =  new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            User ele = new User();
            ele.setU_acc(s.getU_acc());
            User mentor = userDao.querySingle(ele);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallMentorMessageFix em = new StallMentorMessageFix(s.getSmm_id(),stallFix,mentor,s.getSmm_info(),s.getSmm_pass(),s.getSmm_status(),s.getSmm_dct(),s.getSmm_time(),s.getStall_view(),s.getMentor_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
    public PageBean<StallMentorMessageFix> queryStallPage(Integer currentPage, User object){
        List<StallMentorMessage> temresult = new ArrayList<>();
        List<StallMentorMessageFix> result = new ArrayList<>();
        Stall chain = new Stall();
        StallTeamMessage target = new StallTeamMessage();
        Map<String,String> joinCondition = new LinkedHashMap<>();
        joinCondition.put("stall","stall.st_id=stall_mentor_message.st_id");
        joinCondition.put("user","stall.u_acc=user.u_acc");
        temresult=messageDao.leftQuery(StallMentorMessage.class,"stall_mentor_message", BaseDao.formList(object,chain,target),joinCondition, -1,-1);
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult = temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
        for(StallMentorMessage s :temresult){
            Stall tem =  new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            User ele = new User();
            ele.setU_acc(s.getU_acc());
            User mentor = userDao.querySingle(ele);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallMentorMessageFix em = new StallMentorMessageFix(s.getSmm_id(),stallFix,mentor,s.getSmm_info(),s.getSmm_pass(),s.getSmm_status(),s.getSmm_dct(),s.getSmm_time(),s.getStall_view(),s.getMentor_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
