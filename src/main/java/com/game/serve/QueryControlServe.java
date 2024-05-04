package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.FixDao.*;
import com.game.dao.StallMentorMessageDao;
import com.game.dao.TeamDao;
import com.game.dao.UserDao;
import com.game.domain.Stall;
import com.game.domain.fixDomain.StallFix;
import com.game.utils.Result;

public class QueryControlServe {
    StallFixDao stallFixDao  = new StallFixDao();
    StallMentorMessageFixDao stallMentorMessageFixDao = new StallMentorMessageFixDao();
    StallTeamMessageFixDao stallTeamMessageFixDao = new StallTeamMessageFixDao();
    StallProjectMessageFixDao stallProjectMessageFixDao = new StallProjectMessageFixDao();

    TeamFIxDao teamFIxDao = new TeamFIxDao();
    UserDao userDao =new UserDao();
    TeamMessageFixDao teamMessageFixDao = new TeamMessageFixDao();

    public Result<PageBean<StallFix>> queryPage(Stall stall){

    }





}
