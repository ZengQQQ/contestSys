package com.game.serve;

import com.game.dao.CollegeDao;
import com.game.dao.MentorDao;
import com.game.dao.StudentDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.User;
import com.game.domain.secondary.userDomain.College;
import com.game.domain.secondary.userDomain.Mentor;
import com.game.domain.secondary.userDomain.Student;

import java.util.ArrayList;
import java.util.List;

public class SignUpControlServe {
//    reged signed
    public boolean studentCheckRegistered(String s_xuehao,String s_name,String s_major){
        if(s_xuehao!=null){
            Student student = new Student();
            student.setS_xuehao(s_xuehao);
            student.setS_name(s_name);
            student.setS_major(s_major);
            List<Student> students =(new StudentDao()).query(student,-1,-1);
            return !students.isEmpty();
        }else {
            return false;
        }
    }
    public boolean studentCheckSigned(String s_xuehao,String s_name,String s_major){
        if(s_xuehao!=null){
            Student student = new Student();
            User user =new User();
            student.setS_xuehao(s_xuehao);
            student.setS_name(s_name);
            student.setS_major(s_major);
            List<User> users =(new UserDao()).leftQuery(BaseDao.formList(user,student),-1,-1);
            return !users.isEmpty();
        }else {
            return false;
        }
    }
    public boolean studentSignUp(String u_pwd,String s_xuehao,String s_name,String s_major){
        if(s_xuehao!=null&&u_pwd!=null) {
            Student student = new Student();
            User user = new User();
            student.setS_xuehao(s_xuehao);
            student.setS_name(s_name);
            student.setS_major(s_major);
            Student student1 = (new StudentDao()).querySingle(student);
            if (student1.getS_status()!=0) {
                user.setU_pwd(u_pwd);
                user.setU_name(student1.getS_name());
                user.setS_id(student1.getS_id());
                return (new UserDao()).insert(user);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public boolean mentorCheckRegistered(String m_acc,String m_name){
        if(m_acc!=null){
            Mentor mentor = new Mentor();
            mentor.setM_acc(m_acc);
            mentor.setM_name(m_name);
            List<Mentor> mentors =(new MentorDao()).query(mentor,-1,-1);
            return !mentors.isEmpty();
        }else {
            return false;
        }
    }
    public boolean mentorCheckSigned(String m_acc,String m_name){
        if(m_acc!=null){
            Mentor mentor = new Mentor();
            User user = new User();
            mentor.setM_acc(m_acc);
            mentor.setM_name(m_name);
            List<User> users =(new UserDao()).leftQuery(BaseDao.formList(mentor,user),-1,-1);
            return !users.isEmpty();
        }else {
            return false;
        }
    }
    public boolean mentorSignUp(String u_pwd,String m_acc,String m_name){
        if(m_acc!=null&&u_pwd!=null){
            Mentor mentor = new Mentor();
            User user = new User();
            mentor.setM_acc(m_acc);
            mentor.setM_name(m_name);
            Mentor mentor1 = (new MentorDao()).querySingle(mentor);
            if(mentor1!=null) {
                user.setU_pwd(u_pwd);
                user.setM_id(mentor1.getM_id());
                user.setU_name(mentor1.getM_name());
                return (new UserDao()).insert(user);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean collegeCheckRegistered(String co_acc,String co_name){
        if(co_acc!=null){
            College college = new College();
            college.setCo_acc(co_acc);
            college.setCo_name(co_name);
            List<College>colleges  =(new CollegeDao()).query(college,-1,-1);
            return !colleges.isEmpty();
        }else {
            return false;
        }
    }
    public boolean collegeCheckSigned(String co_acc,String co_name){
        if(co_acc!=null){
            College college = new College();
            college.setCo_acc(co_acc);
            college.setCo_name(co_name);
            User user = new User();
            List<User> users =(new UserDao()).leftQuery(BaseDao.formList(college,user),-1,-1);
            return !users.isEmpty();
        }else {
            return false;
        }
    }
    public boolean collegeSignUp(String u_pwd,String co_acc,String co_name){
        if(co_acc!=null&&u_pwd!=null){
            College college = new College();
            college.setCo_acc(co_acc);
            college.setCo_name(co_name);
            User user = new User();
            College college1 = (new CollegeDao()).querySingle(college);
            if(college1!=null) {
                user.setU_pwd(u_pwd);
                user.setM_id(college1.getCo_id());
                user.setU_name(college1.getCo_name());
                return (new UserDao()).insert(user);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}
