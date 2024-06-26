package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.TeamDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.fixDomain.TeamFix;

import java.util.*;

public class TeamFixDao {

    UserDao userDao = new UserDao();
    TeamDao teamDao = new TeamDao();

    private Team model;

    private PageBean<TeamFix> pageBean = new PageBean<>();


    public void initPage(Team object){
        this.model=object;
        List<Team> total =new ArrayList<>();
        total=teamDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public TeamFix singToFix(Team ele) {
        User tem = new User();
        tem.setU_acc(ele.getU_acc());
        User captain = userDao.querySingle(tem);
        User taget = new User();
        TeamUserMessage chain = new TeamUserMessage();
        chain.setJoin_status(1);
        Map<String,String> joincondition = new LinkedHashMap<>();
        joincondition.put("team_user_message","team_user_message.t_id=team.t_id");
        joincondition.put("user","team_user_message.u_acc=user.u_acc");
        List<User> member = userDao.leftQuery(User.class,"team",BaseDao.formList(ele,taget,chain),joincondition,-1,-1);
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

    public void initPage(Integer totalsize){
        pageBean.setTotalSize(totalsize);
    }

    /**
     *
     * @param currentPage
     * @param user
     * @param chain
     * @return
     */
    public PageBean<TeamFix> queryByPage(Integer currentPage, User user,TeamUserMessage chain,Team target){
        Map<String,String> joinCondition = new LinkedHashMap<>();
        joinCondition.put("team_user_message","team.t_id=team_user_message.t_id");
        joinCondition.put("user","team_user_message.u_acc=user.u_acc");
        List<Team> temresult = teamDao.leftQuery(Team.class,"team",BaseDao.formList(target,chain,user),joinCondition,-1,-1);
        List<TeamFix> result = new ArrayList<>();
        initPage(temresult.size());
        pageBean.setCurrentPage(currentPage);
        temresult=temresult.subList(pageBean.getBegin(),pageBean.getEnd()>temresult.size() ? temresult.size() : pageBean.getEnd());
        for(Team ele :temresult){
            TeamFix teamFix = singToFix(ele);
            result.add(teamFix);
        }
        pageBean.setListPage(result);
        return pageBean;
    }

}
