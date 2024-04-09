package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.StudentDao;
import com.game.domain.Student;
import lombok.Getter;

import java.util.List;

@Getter
public class StudentServe extends StudentDao {
    private final PageBean<Student> pageBean = new PageBean<Student>(super.statistics());
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
        result=query(student,pageBean.getStart(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

}
