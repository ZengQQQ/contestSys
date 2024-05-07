package com.game.serve;

import com.game.dao.MentorDao;
import com.game.dao.StudentDao;
import com.game.dao.UserDao;
import com.game.dao.base.BaseDao;
import com.game.domain.User;
import com.game.domain.Mentor;
import com.game.domain.Student;
import com.game.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpControlServe {
//    reged signed
    public boolean studentCheckRegistered(User user){
        user.setU_id(null);
        if(user.getU_acc()!=null){
            Student student = new Student();
            student.setS_acc(user.getU_acc());
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
    public Result<String>SignUp(User user){
        if(user.getU_acc() != null && user.getU_pwd() != null&&user.getU_identity()!=null) {
            if(user.getU_identity()==1){
                if(mentorCheckRegistered(user)) {
                    if (mentorCheckUnsigned(user)) {
                        Mentor mentor = new Mentor();
                        mentor.setM_acc(user.getU_acc());
                        Mentor mentor1 = (new MentorDao()).querySingle(mentor);
                        if (mentor1.getM_status() == 0) {
                            user.setU_identity(1);
                            (new UserDao()).insert(user);
                            return Result.success("用户成功注册");
                        } else {
                            return Result.fail("用户注册失败，该导师已被封禁","该导师已被封禁");
                        }

                    } else {
                        return Result.fail("用户注册失败，该账号已经注册","该账号已经注册");
                    }
                }else {
                    return Result.fail("用户注册失败，该账号注册表中不存在","该账号注册表中不存在");
                }
            }else {
                if (studentCheckRegistered(user)) {
                    if (studentCheckUnsigned(user)) {
                        Student student = new Student();
                        student.setS_acc(user.getU_acc());
                        Student student1 = (new StudentDao()).querySingle(student);
                        if (student1.getS_status() == 0) {
                            user.setU_identity(0);
                            (new UserDao()).insert(user);
                            return Result.success("用户成功注册");
                        } else {
                            return Result.fail("用户注册失败，该学生已被封禁","该学生已被封禁");
                        }
                    } else {
                        return Result.fail("用户注册失败，该账号已经注册","该账号已经注册");
                    }
                } else {

                    return Result.fail("用户注册失败，该账号注册表中不存在","该账号注册表中不存在");
                }
            }
        }else {
            return Result.fail("用户注册失败，输入值异常","输入值异常");
        }

    }
    public boolean mentorCheckRegistered(User user){
        if(user.getU_acc()!=null){
            Mentor mentor = new Mentor();
            mentor.setM_acc(user.getU_acc());
            System.out.println(mentor);
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

}
