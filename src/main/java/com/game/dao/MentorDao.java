package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.secondary.userDomain.Mentor;

import java.util.List;
import java.util.Map;

public class MentorDao extends BaseDao<Mentor> {
    public MentorDao() {
        super("mentor");
    }


    public boolean insert(Mentor mentor) {
        Map<String, Object> map = mentor.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("教师信息插入成功");
        }else {
            System.out.println("教师信息插入失败");
        }
        return value;
    }


    public int delete(Mentor mentor) {
        Map<String, Object> map = mentor.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除教师失败");
        }else {
            System.out.println("删除教师成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<Mentor> query(Mentor mentor, int start, int end) {
        Class<Mentor> clazz = Mentor.class;
        Map<String, Object> map = mentor.toMap();
        List<Mentor>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询教师失败");
        }else {
            System.out.println("查询教师成功");
            for (Mentor m : value){
                System.out.println(m);
            }
        }
        return value;
    }


    public int update(Mentor mentor, Mentor mapCondition) {
        Map<String, Object> map = mentor.toMap();
        map.remove("m_id");
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新教师失败");
        } else {
            System.out.println("更新教师成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }


    public int statistics(Mentor mentor) {
        return super.statistics(mentor);
    }
}
