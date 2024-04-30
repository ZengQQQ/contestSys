package com.game.serve;

import com.game.dao.*;
import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.domain.secondary.userDomain.College;
import com.game.domain.secondary.userDomain.Mentor;
import com.game.domain.secondary.userDomain.Student;

import java.util.ArrayList;
import java.util.List;

public class LoginControlServe {

    public boolean studentLogin(String s_xuehao,String u_pwd){
        if (s_xuehao!=null && u_pwd!=null) {
            Student student =new Student();
            User user = new User();
            List<Object> list = new ArrayList<>();
            student.setS_xuehao(s_xuehao);
            user.setU_pwd(u_pwd);
            user.setU_status(1);
            list.add(student);
            list.add(user);
            List<Student> studentList = (new StudentDao()).leftQuery(list, -1, -1);
            return !studentList.isEmpty();
        }else {
            return false;
        }
    }

    public boolean administratorLogin(String a_acc,String a_pwd){
        if(a_acc!=null&&a_pwd!=null){
            Administrator administrator = new Administrator();
            administrator.setA_acc(a_acc);
            administrator.setA_pwd(a_pwd);
            List<Administrator> administratorList =(new AdministratorDao()).query(administrator,-1,-1);
            return !administratorList.isEmpty();
        }else {
            return false;
        }
    }

    public boolean mentorLogin(String m_acc,String u_pwd){
        if (m_acc!=null && u_pwd!=null) {
            Mentor mentor =new Mentor();
            User user = new User();
            List<Object> list = new ArrayList<>();
            mentor.setM_acc(m_acc);
            user.setU_pwd(u_pwd);
            user.setU_status(1);
            list.add(mentor);
            list.add(user);
            List<Mentor> mentors = (new MentorDao()).leftQuery(list, -1, -1);
            return !mentors.isEmpty();
        }else {
            return false;
        }
    }

    public boolean collegeLogin(String co_acc,String u_pwd){
        if (co_acc!=null && u_pwd!=null) {
            College college =new College();
            User user = new User();
            List<Object> list = new ArrayList<>();
            college.setCo_acc(co_acc);
            user.setU_pwd(u_pwd);
            user.setU_status(1);
            list.add(college);
            list.add(user);
            List<College> colleges = (new CollegeDao()).leftQuery(list, -1, -1);
            return !colleges.isEmpty();
        }else {
            return false;
        }
    }

}
