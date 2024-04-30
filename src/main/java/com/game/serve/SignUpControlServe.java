package com.game.serve;

import com.game.dao.MentorDao;
import com.game.dao.StudentDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.User;
import com.game.domain.Mentor;
import com.game.domain.Student;

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
    public boolean studentSignUp(User user){
        if(user.getU_acc()!=null&&user.getU_pwd()!=null) {
            Student student = new Student();
            student.setS_xuehao(user.getU_acc());
            Student student1 = (new StudentDao()).querySingle(student);
            if (student1.getS_status()!=0) {
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
    public boolean mentorSignUp(User user){
        if(user.getU_acc()!=null&&user.getU_pwd()!=null){
            Mentor mentor = new Mentor();
            mentor.setM_acc(user.getU_acc());
            Mentor mentor1 = (new MentorDao()).querySingle(mentor);
            if(mentor1!=null) {
                return (new UserDao()).insert(user);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

}
