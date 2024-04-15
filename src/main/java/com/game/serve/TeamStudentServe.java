package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.TeamStudentDao;
import com.game.domain.secondary.TeamStudent;

import java.util.HashMap;
import java.util.List;

public class TeamStudentServe extends TeamStudentDao {
    private final PageBean<TeamStudent> pageBean = new PageBean<TeamStudent>();

    public PageBean<TeamStudent> queryByPage(Integer currentPage, TeamStudent object){
        List<TeamStudent> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }



}
