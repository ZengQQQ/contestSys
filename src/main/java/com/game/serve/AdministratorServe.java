package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.AdministratorDao;
import com.game.dao.CompetitionDao;
import com.game.dao.StudentDao;
import com.game.domain.Administrator;
import com.game.domain.Competition;
import com.game.domain.Student;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class AdministratorServe extends AdministratorDao {
    private final PageBean<Administrator> pageBean = new PageBean<Administrator>();

    public boolean checkPassword(Administrator ad){
        if(ad.getA_acc()!=null && ad.getA_pwd()!=null){
            List<Administrator> adList = this.query(ad,-1,-1);
            return !adList.isEmpty();
        }
        else{
            return false;
        }
    }

    //封禁学生账号


  //添加比赛信息

    public PageBean<Administrator> queryByPage(Integer currentPage,Administrator object) {
        List<Administrator> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

    //删除帖子
    public boolean deletePostings(){
        return true;
    }



}
