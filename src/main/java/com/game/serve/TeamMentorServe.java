package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.TeamMentorDao;
import com.game.domain.secondary.teamDomain.TeamMentor;

import java.util.List;

public class TeamMentorServe extends TeamMentorDao {
    private final PageBean<TeamMentor> pageBean = new PageBean<TeamMentor>();

    public PageBean<TeamMentor> queryByPage(Integer currentPage, TeamMentor object){
        List<TeamMentor> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }



}
