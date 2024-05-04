package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.StallDao;
import com.game.dao.StallMentorMessageDao;
import com.game.dao.UserDao;
import com.game.domain.Stall;
import com.game.domain.StallMentorMessage;
import com.game.domain.User;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.StallMentorMessageFix;

import java.util.ArrayList;
import java.util.List;

public class StallMentorMessageFixDao {

    UserDao userDao = new UserDao();
    StallDao stallDao = new StallDao();
    StallFixDao stallFixDao = new StallFixDao();
    StallMentorMessageDao messageDao = new StallMentorMessageDao();
    private PageBean<StallMentorMessageFix> pageBean = new PageBean<>();


    public void initPage(Integer currentPage, StallMentorMessage object){
        List<StallMentorMessage> total =messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallMentorMessageFix> queryByPage(Integer currentPage, StallMentorMessage object){
        List<StallMentorMessage> temresult = new ArrayList<>();
        List<StallMentorMessageFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=messageDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(StallMentorMessage s :temresult){
            Stall stall = stallDao.querySingle(new Stall(null,null,null,s.getSt_id(),null,null));
            User mentor = userDao.querySingle(new User(null,s.getM_acc(),null,null,null,null,null,null,null,null,null));
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallMentorMessageFix em = new StallMentorMessageFix(s.getSmm_id(),stallFix,mentor,s.getSmm_info(),s.getSmm_pass(),s.getSmm_status(),s.getSmm_dct(),s.getSmm_time());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
