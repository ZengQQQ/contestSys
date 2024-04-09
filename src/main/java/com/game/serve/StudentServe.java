package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.StudentDao;
import com.game.domain.Student;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
public class StudentServe extends StudentDao {
    private final PageBean<Student> pageBean = new PageBean<Student>(statistics(new HashMap<>()));
    public boolean checkPassword(Student student){
        if (student.getS_xuehao()!=null && student.getS_pwd()!=null) {
            List<Student> studentList;
            studentList = this.query(student, -1, -1);
            return !studentList.isEmpty();
        }else {
            return false;
        }
    }
    public PageBean<Student> queryByPage(Integer currentPage,Student student){
        List<Student> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(student));
        result=query(student,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
    public boolean banStudentAccount(Student stu){
        if(stu.getS_status()!=1){
            HashMap<String,Object> newStatus = new HashMap<>();
            newStatus.put("s_status", 0);
            StudentDao studentDao = new StudentDao();
            return studentDao.update(newStatus, stu.toMap()) != 0;
        }else{
            System.out.println("该账号目前已经被封禁");
            return false;
        }
    }
    //解封学生账号
    public boolean unBanStudentAccount(Student stu){
        if(stu.getS_status()!=0){
            HashMap<String,Object> newStatus = new HashMap<>();
            newStatus.put("s_status", 1);
            StudentDao studentDao = new StudentDao();
            return studentDao.update(newStatus, stu.toMap()) != 0;
        }else{
            System.out.println("该账号处于正常状态");
            return false;
        }
    }

}
