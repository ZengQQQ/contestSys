package com.game.serve;

import com.game.dao.MentorDao;
import com.game.domain.Mentor;
import java.util.List;

public class MentorServe extends MentorDao {
    public boolean checkPassword(Mentor mentor){
        if (mentor.getM_acc()!=null && mentor.getM_pwd()!=null){
            List<Mentor> mentorList;
            mentorList = this.query(mentor,-1,-1);
            return !mentorList.isEmpty();
        }else {
            return false;
        }
    }
}
