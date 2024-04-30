package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.secondary.workDomain.Task;

import java.util.List;

public class TaskDao extends BaseDao<Task> {
    public TaskDao() {
        super("task");
    }

    @Override
    public boolean insert(Task object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("任务添加成功");
        }else {
            System.out.println("任务添加失败");
        }
        return value;
    }

    @Override
    public int delete(Task object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("任务删除失败");
        }else {
            System.out.println("任务删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<Task> query(Task object, int start, int end) {
        List<Task> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询任务失败");
        }else {
            System.out.println("查询任务成功");
            for (Task v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(Task object1, Task object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("任务更新失败");
        }else {
            System.out.println("任务更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }

    private final PageBean<Task> pageBean = new PageBean<Task>();

    public PageBean<Task> queryByPage(Integer currentPage,Task object) {
        List<Task> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
