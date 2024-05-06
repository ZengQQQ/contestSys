package com.game.serve;


import com.game.dao.StudentDao;
import com.game.domain.Student;
import com.game.utils.Result;

import java.util.List;

public class StudentService {

    private static final StudentDao studentDao = new StudentDao();

    public Result<String> insert(Student Student) {
        Student old = new Student();
        old.setS_acc(Student.getS_acc());
        List<Student> exited = studentDao.query(old,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("添加失败","已存在该学生");
        }
        boolean inserted = studentDao.insert(Student);
        if (inserted){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败","添加失败");
    }

    public Result<String> update(Student student) {
        Student old = new Student();
        old.setS_id(student.getS_id());
        List<Student> exited = studentDao.query(old,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败，没有该学生","");
        }
        int updated = studentDao.update(student,old);
        if (updated == 0){
            return Result.fail("更新失败","");
        }
        return Result.success("更新成功");
    }

    public Result<String> delete(Student student) {
        List<Student> exited = studentDao.query(student,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("删除失败，没有该学生",null);
        }
        int deleted = studentDao.delete(student);
        if (deleted == 0){
            return Result.fail("删除失败",null);
        }
        return Result.success("删除成功");
    }
}
