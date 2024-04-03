package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Student;

import java.util.List;
import java.util.Map;

public class StudentDao extends BaseDao<Student> {
    public StudentDao() {
        super("Student");
    }

    public boolean insert(Student Student) {
        Map<String, Object> map = Student.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("学生信息插入成功");
        }else {
            System.out.println("学生信息插入失败");
        }
        return value;
    }


    public int delete(Student Student) {
        Map<String, Object> map = Student.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除学生失败");
        }else {
            System.out.println("删除学生成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<Student> query(Student Student, int start, int end) {
        Class<Student> clazz = Student.class;
        Map<String, Object> map = Student.toMap();
        List<Student>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询学生失败");
        }else {
            System.out.println("查询学生成功");
            for (Student v : value){
                System.out.println(v);
            }
        }
        return value;
    }


    public int update(Student Student, Student mapCondition) {
        Map<String, Object> map = Student.toMap();
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新学生失败");
        } else {
            System.out.println("更新学生成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }
}
