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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StallFixDao {

    StallDao stallDao =new StallDao();
    UserDao userDao = new UserDao();
    TeamDao teamDao = new TeamDao();
    ProjectDao projectDao = new ProjectDao();
    TeamFixDao teamFIxDao =new TeamFixDao();
    private Stall model;

    private PageBean<StallFix> pageBean = new PageBean<>();


    public void initPage(Stall object){
        this.model=object;
        List<Stall> total =new ArrayList<>();
        total=stallDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public void initPage(Integer totalsize){
        pageBean.setTotalSize(totalsize);
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
        if(!object.equals(this.model)){
            initPage(object);
        }
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

    /**
     *
     * @param currentPage
     * @param user
     * @param chain
     * @param chain1
     * @param chain2
     * @param target
     * @return
     */
    public PageBean<StallFix> queryByPage(Integer currentPage, User user,TeamUserMessage chain,Team chain1,StallTeamMessage chain2,Stall target){
        Map<String,String> joinCondition = new HashMap<>();
        joinCondition.put("stall_team_message","stall.st_id=stall_team_message.st_id");
        joinCondition.put("team","stall_team_message.t_id=team.t_id");
        joinCondition.put("team_user_message","team.t_id=team_user_message.t_id");
        joinCondition.put("user","team_user_message.u_acc=user.u_acc");
        List<Stall> temresult = stallDao.leftQuery(Stall.class,"stall",BaseDao.formList(target,chain,chain1,chain2,user),joinCondition,-1,-1);
        List<StallFix> result = new ArrayList<>();
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult=temresult.subList(pageBean.getBegin()-1,pageBean.getEnd());
        for(Stall ele :temresult){
            StallFix fix = singToFix(ele);
            result.add(fix);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
