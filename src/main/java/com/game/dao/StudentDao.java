package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.Student;
import com.game.domain.User;

import java.util.List;
import java.util.Map;

public class StudentDao extends BaseDao<Student> {
    public StudentDao() {
        super("student");
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
        map.remove("s_id");
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
    private final PageBean<Student> pageBean = new PageBean<Student>();
    public PageBean<Student> queryByPage(Integer currentPage,Student student){
        List<Student> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(student));
        result=query(student,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }


    public int statistics(Student student) {
        return super.statistics(student);
    }

    public static void main(String[] args) {
        StudentDao dao =new StudentDao();
        List<Student> students=dao.leftQuery(Student.class,BaseDao.formList(new Student(),new User()),-1,-1);
        for (Student s:students){
            System.out.println(s);
        }
    }
}
