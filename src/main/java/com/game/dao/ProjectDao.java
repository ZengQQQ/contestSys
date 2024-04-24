package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.secondary.workDomain.Project;

import java.util.List;

public class ProjectDao extends BaseDao<Project> {
    public ProjectDao() {
        super("project");
    }

    @Override
    public boolean insert(Project object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("项目添加成功");
        }else {
            System.out.println("项目添加失败");
        }
        return value;
    }

    @Override
    public int delete(Project object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("项目删除失败");
        }else {
            System.out.println("项目删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<Project> query(Project object, int start, int end) {
        List<Project> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询项目失败");
        }else {
            System.out.println("查询项目成功");
            for (Project v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(Project object1, Project object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("项目更新失败");
        }else {
            System.out.println("项目更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }
}