package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.dao.StallDao;
import com.game.dao.StallProjectMessageDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.StallProjectMessageFix;

import java.util.ArrayList;
import java.util.List;

public class StallProjectMessageFixDao {

    ProjectDao projectDao =new ProjectDao();
    StallDao stallDao = new StallDao();
    StallFixDao stallFixDao = new StallFixDao();
    StallProjectMessageDao messageDao = new StallProjectMessageDao();
    private PageBean<StallProjectMessageFix> pageBean = new PageBean<>();


    public void initPage(Integer currentPage, StallProjectMessage object){
        List<StallProjectMessage> total =messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallProjectMessageFix> queryByPage(Integer currentPage, StallProjectMessage object){
        List<StallProjectMessage> temresult = new ArrayList<>();
        List<StallProjectMessageFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=messageDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(StallProjectMessage s :temresult){
            Stall stall = stallDao.querySingle(new Stall(null,null,null,s.getSt_id(),null,null));
            Project p =new Project();
            p.setP_id(s.getP_id());
            Project project= projectDao.querySingle(p);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallProjectMessageFix em = new StallProjectMessageFix(s.getSmm_id(),stallFix,project,s.getSmm_info(),s.getSmm_pass(),s.getSmm_status(),s.getSmm_dct(),s);
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
