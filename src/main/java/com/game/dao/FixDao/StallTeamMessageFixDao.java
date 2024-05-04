package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.StallDao;
import com.game.dao.StallTeamMessageDao;
import com.game.dao.TeamDao;
import com.game.domain.Stall;
import com.game.domain.StallTeamMessage;
import com.game.domain.Team;
import com.game.domain.fixDomain.StallFix;
import com.game.domain.fixDomain.StallTeamMessageFix;

import java.util.ArrayList;
import java.util.List;

public class StallTeamMessageFixDao {

    TeamDao teamDao = new TeamDao();
    StallDao stallDao = new StallDao();
    StallFixDao stallFixDao = new StallFixDao();
    StallTeamMessageDao messageDao = new StallTeamMessageDao();
    private PageBean<StallTeamMessageFix> pageBean = new PageBean<>();


    public void initPage(Integer currentPage, StallTeamMessage object){
        List<StallTeamMessage> total =messageDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallTeamMessageFix> queryByPage(Integer currentPage, StallTeamMessage object){
        List<StallTeamMessage> temresult = new ArrayList<>();
        List<StallTeamMessageFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=messageDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(StallTeamMessage s :temresult){
            Stall tem = new Stall();
            tem.setSt_id(s.getSt_id());
            Stall stall = stallDao.querySingle(tem);
            Team ele = new Team();
            ele.setT_id(s.getT_id());
            Team team= teamDao.querySingle(ele);
            StallFix stallFix = stallFixDao.singToFix(stall);
            StallTeamMessageFix em = new StallTeamMessageFix(s.getSmm_id(),stallFix,team,s.getSmm_info(),s.getSmm_pass(),s.getSmm_status(),s.getSmm_dct(),s.getSmm_time(),s.getStall_view(),s.getTeam_view(),s.getJoin_status());
            result.add(em);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
