package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.Administrator;
import com.game.domain.Stall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员表的增删改查
 */
public class AdministratorDao extends BaseDao<Administrator> {

    public AdministratorDao() {
        super("administrator");
    }
    private Administrator model;



    public boolean insert(Administrator administrator) {
        Map<String, Object> map=administrator.toMap();
        return super.insert(map);
    }


    public int delete(Administrator administrator) {
        Map<String, Object> map=administrator.toMap();
        return super.delete(map);
    }


    public List<Administrator> query( Administrator administrator, int start, int end) {
        Map<String, Object> map=administrator.toMap();
        return super.query(Administrator.class, map, start, end);
    }

    public int update(Administrator administrator, Administrator condition) {
        Map<String, Object> map = administrator.toMap();
        map.remove("a_id");
        Map<String, Object> con = condition.toMap();
        return super.update(map, con);
    }



    public int statistics(Administrator administrator) {
        Map<String, Object> map=administrator.toMap();
        return super.statistics(Administrator.class,map);
    }

    private PageBean<Administrator> pageBean = new PageBean<Administrator>();


    public void initPage( Administrator object){
        this.model=object;
        List<Administrator> total =new ArrayList<>();
        total=query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<Administrator> queryByPage(Integer currentPage, Administrator object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<Administrator> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }

    public static void main(String[] args) {
        AdministratorDao administratorDao = new AdministratorDao();
        Administrator administrator = new Administrator();
        administrator.setA_acc("11111");
        administrator.setA_pwd("1234");
        administratorDao.insert(administrator);
        System.out.println(administratorDao.query(administrator,-1,-1));
    }

}
