package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.alibaba.fastjson2.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SquareDao  extends BaseDao<Square>{
    public SquareDao() {
        super("projectview");
    }
    private final PageBean<Square> pageBean = new PageBean<Square>();

    public PageBean<Square> queryByPage(Integer currentPage, Square object){
        List<Square> result = null;
        result=query(object,-1,-1);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(result.size());
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
    public PageBean<Square> query(Integer currentPage,Competition competition,User captain, User mentor, MentorTeamCompLink mentorTeamCompLink,Team team){
        Captain captain1 = new Captain(captain);
        MentorUser mentorUser = new MentorUser(mentor);
        Map<String,Object> map =new HashMap<>();
        map.putAll(captain1.toMap());
        map.putAll(mentorUser.toMap());
        map.putAll(mentorTeamCompLink.toMap());
        map.putAll(team.toMap());
        Square square=JSON.toJavaObject((JSON) JSON.toJSON(map), Square.class);

    }
}
