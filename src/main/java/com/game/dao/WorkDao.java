package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.Work;


import java.util.List;

public class WorkDao extends BaseDao<Work> {
    public WorkDao() {
        super("work");
    }

    @Override
    public boolean insert(Work object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("工作添加成功");
        }else {
            System.out.println("工作添加失败");
        }
        return value;
    }

    @Override
    public int delete(Work object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("工作删除失败");
        }else {
            System.out.println("工作删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<Work> query(Work object, int start, int end) {
        List<Work> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询工作失败");
        }else {
            System.out.println("查询工作成功");
            for (Work v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(Work object1, Work object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("工作更新失败");
        }else {
            System.out.println("工作更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }

    private final PageBean<Work> pageBean = new PageBean<Work>();

    public PageBean<Work> queryByPage(Integer currentPage,Work object) {
        List<Work> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
