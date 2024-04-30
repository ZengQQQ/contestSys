package com.game.serve;

import com.game.dao.MentorDao;
import com.game.dao.StudentDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.User;
import com.game.domain.Mentor;
import com.game.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpControlServe {
//    reged signed
    public boolean studentCheckRegistered(User user){
        user.setU_id(null);
        if(user.getU_acc()!=null){
            Student student = new Student();
            student.setS_xuehao(user.getU_acc());
            List<Student> students =(new StudentDao()).query(student,-1,-1);
            return !students.isEmpty();
        }else {
            return false;
        }
    }
    public boolean studentCheckUnsigned(User user){
        user.setU_id(null);
        if(user.getU_acc()!=null) {
            User user1 = new User();
            user1.setU_acc(user.getU_acc());
            List<User> users = (new UserDao()).query(user1, -1, -1);
            return users.isEmpty();
        }else {
            return false;
        }
    }
    public Map<String,String> studentSignUp(User user){
        Map<String,String>map=new HashMap<>();
        if(studentCheckUnsigned(user)) {
            if (studentCheckRegistered(user)) {
                if (user.getU_acc() != null && user.getU_pwd() != null) {
                    Student student = new Student();
                    student.setS_xuehao(user.getU_acc());
                    Student student1 = (new StudentDao()).querySingle(student);
                    if (student1.getS_status() != 0) {
                        map.put("code", "1");
                        map.put("message", "用户成功注册");
                        (new UserDao()).insert(user);
                    } else {
                        map.put("code", "0");
                        map.put("message", "用户封禁中");
                    }
                } else {
                    map.put("code", "0");
                    map.put("message", "输入值异常");
                }
            } else {
                map.put("code", "0");
                map.put("message", "注册表不存在");
            }
        }else {
            map.put("code", "0");
            map.put("message", "用户已注册");
        }

        return map;
    }
    public boolean mentorCheckRegistered(User user){
        if(user.getU_acc()!=null){
            Mentor mentor = new Mentor();
            mentor.setM_acc(user.getU_acc());
            List<Mentor> mentors =(new MentorDao()).query(mentor,-1,-1);
            return !mentors.isEmpty();
        }else {
            return false;
        }
    }
    public boolean mentorCheckUnsigned(User user){
        if(user.getU_acc()!=null){
            User user1 = new User();
            user1.setU_acc(user.getU_acc());
            List<User> users =(new UserDao()).query(user1,-1,-1);
            return users.isEmpty();
        }else {
            return false;
        }
    }
    public Map<String,String> mentorSignUp(User user){
        Map<String,String>map=new HashMap<>();
        if(mentorCheckUnsigned(user)) {
            if (mentorCheckRegistered(user)) {
                if (user.getU_acc() != null && user.getU_pwd() != null) {
                    Mentor mentor = new Mentor();
                    mentor.setM_acc(user.getU_acc());
                    Mentor mentor1 = (new MentorDao()).querySingle(mentor);
                    if (mentor1.getM_status() != 0) {
                        map.put("code", "1");
                        map.put("message", "用户成功注册");
                        (new UserDao()).insert(user);
                    } else {
                        map.put("code", "0");
                        map.put("message", "用户封禁中");
                    }
                } else {
                    map.put("code", "0");
                    map.put("message", "输入值异常");
                }
            } else {
                map.put("code", "0");
                map.put("message", "注册表不存在");
            }
        }else {
            map.put("code", "0");
            map.put("message", "用户已注册");
        }
        return map;
    }

}
