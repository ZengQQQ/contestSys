package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.MentorDao;
import com.game.domain.Mentor;


import java.util.HashMap;
import java.util.List;

public class MentorServe extends MentorDao {
    private final PageBean<Mentor> pageBean = new PageBean<Mentor>();
    public boolean checkPassword(Mentor mentor){
        if (mentor.getM_acc()!=null && mentor.getM_pwd()!=null){
            List<Mentor> mentorList;
            mentorList = this.query(mentor,-1,-1);
            return !mentorList.isEmpty();
        }else {
            return false;
        }
    }
    public PageBean<Mentor> queryByPage(Integer currentPage,Mentor object){
        List<Mentor> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
