package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.TeamApplicationDao;
import com.game.domain.secondary.teamMessageDomain.TeamApplication;
import com.game.utils.TeamStatus;

import java.util.List;

public class TeamApplicationServe extends TeamApplicationDao {
    private final PageBean<TeamApplication> pageBean = new PageBean<TeamApplication>();

    public PageBean<TeamApplication> queryByPage(Integer currentPage,TeamApplication object){
        List<TeamApplication> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

    public boolean approve(TeamApplication teamApplication){
        try {
            TeamApplication teamApplication1=new TeamApplication(teamApplication);
            teamApplication1.setTa_status(TeamStatus.approve);
            update(teamApplication,teamApplication);
            return true;
        }catch (Exception e){
            System.err.println("同意组队要求时出错："+e.getMessage());
            return false;
        }
    }
    public boolean refuse(TeamApplication teamApplication){
        try {
            TeamApplication teamApplication1=new TeamApplication(teamApplication);
            teamApplication1.setTa_status(TeamStatus.refuse);
            update(teamApplication,teamApplication);
            return true;
        }catch (Exception e){
            System.err.println("拒绝组队要求时出错："+e.getMessage());
            return false;
        }
    }
    public boolean unread(TeamApplication teamApplication){
        try {
            TeamApplication teamApplication1=new TeamApplication(teamApplication);
            teamApplication1.setTa_status(TeamStatus.unread);
            update(teamApplication,teamApplication);
            return true;
        }catch (Exception e){
            System.err.println("未读组队要求时出错："+e.getMessage());
            return false;
        }
    }
}
