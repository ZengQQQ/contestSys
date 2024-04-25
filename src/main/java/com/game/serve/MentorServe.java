package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.MentorDao;
import com.game.domain.User;
import com.game.domain.secondary.userDomain.Mentor;


import java.util.ArrayList;
import java.util.List;

public class MentorServe extends MentorDao {

    public boolean checkPassword(String u_pwd, String m_acc){
        if (m_acc!=null && u_pwd!=null){
            Mentor mentor = new Mentor();
            User user = new User();
            mentor.setM_acc(m_acc);
            user.setU_pwd(u_pwd);
            List<Mentor> mentorList;
            List<Object> list =new ArrayList<>();
            list.add(user);
            list.add(mentor);
            mentorList = this.leftQuery(list,-1,-1);
            return !mentorList.isEmpty();
        }else {
            return false;
        }
    }

}
