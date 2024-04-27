package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.TeamApplicationDao;
import com.game.domain.secondary.teamMessageDomain.TeamApplication;
import com.game.utils.TeamStatus;

import java.util.List;

public class TeamApplicationServe extends TeamApplicationDao {


    public boolean approve(TeamApplication teamApplication){
        try {
            TeamApplication teamApplication1=new TeamApplication(teamApplication);
            teamApplication1.setTa_status(1);
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
            teamApplication1.setTa_status(2);
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
            teamApplication1.setTa_status(0);
            update(teamApplication,teamApplication);
            return true;
        }catch (Exception e){
            System.err.println("未读组队要求时出错："+e.getMessage());
            return false;
        }
    }
}
