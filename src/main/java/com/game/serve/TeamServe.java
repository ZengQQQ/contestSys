package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.TeamDao;
import com.game.domain.secondary.teamDomain.Team;

import java.util.List;

public class TeamServe extends TeamDao {
    private final PageBean<Team> pageBean = new PageBean<Team>();

    public PageBean<Team> queryByPage(Integer currentPage, Team object){
        List<Team> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }



}
