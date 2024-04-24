package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.secondary.userDomain.College;
import java.util.List;

public class CollegeDao extends BaseDao<College> {
    public CollegeDao() {
        super("college");
    }

    @Override
    public boolean insert(College object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println(object.getCo_id()+"学院添加成功");
        }else {
            System.out.println(object.getCo_id()+"学院添加失败");
        }
        return value;
    }

    @Override
    public int delete(College object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("学院删除失败");
        }else {
            System.out.println("学院删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<College> query(College object, int start, int end) {
        List<College> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询学院失败");
        }else {
            System.out.println("查询学院成功");
            for (College v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(College object1, College object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("学院更新失败");
        }else {
            System.out.println("学院更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }
}
