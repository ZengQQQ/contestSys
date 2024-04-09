package com.game.serve;

import com.game.dao.StudentDao;
import com.game.domain.Student;

import java.util.List;

public class StudentServe extends StudentDao {
    public boolean checkPassword(Student student){
        if (student.getS_xuehao()!=null && student.getS_pwd()!=null) {
            List<Student> studentList;
            studentList = this.query(student, -1, -1);
            return !studentList.isEmpty();
        }else {
            return false;
        }
    }
    public List<Student> ByPage(){
        return null;
    }
}
