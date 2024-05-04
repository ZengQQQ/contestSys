package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.*;
import com.game.dao.FixDao.StallMentorMessageFixDao;
import com.game.dao.FixDao.StallProjectMessageFixDao;
import com.game.dao.FixDao.StallTeamMessageFixDao;
import com.game.dao.FixDao.TeamMessageFixDao;
import com.game.domain.*;
import com.game.domain.fixDomain.StallMentorMessageFix;
import com.game.domain.fixDomain.StallProjectMessageFix;
import com.game.domain.fixDomain.StallTeamMessageFix;
import com.game.domain.fixDomain.TeamMessageFix;
import com.game.utils.Result;


public class MessageServe {

    StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();
    StallProjectMessageDao stallTaskMessageDao = new StallProjectMessageDao();
    StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    StallMentorMessageFixDao stallMentorMessageFixDao = new StallMentorMessageFixDao();
    StallProjectMessageFixDao stallTaskMessageFixDao = new StallProjectMessageFixDao();
    StallTeamMessageFixDao stallTeamMessageFixDao = new StallTeamMessageFixDao();
    TeamUserMessageDao teamUserMessageDao = new TeamUserMessageDao();
    TeamMessageFixDao teamMessageFixDao = new TeamMessageFixDao();

    /*---------------------------------------------------------------------------------------------*/
    /*---------------------------------------队伍信息------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------*/
    /*-----------------------------query会将team队长信息，成员信息都返回，发出或收到邀请的人信息也返回---------*/
    /*-----------------------------quickQuery会将team_id\caption_id\发出或收到邀请的id-----------------*/
    /*---------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------*/

    public Result<PageBean<TeamMessageFix>> query(Integer currentPage, TeamUserMessage ele) {
        teamMessageFixDao.initPage(currentPage, ele);
        PageBean<TeamMessageFix> pageBean=teamMessageFixDao.queryByPage(currentPage, ele);
        if (pageBean.getListPage().isEmpty()){
            return Result.fail("队伍信息查询失败",pageBean);
        }
        return Result.success(pageBean);
    }

    //需要增加入队操作
    public Result<String>update (TeamUserMessage ele,TeamUserMessage con){
        int num = teamUserMessageDao.update(ele,con);
        if(num ==0){
            return Result.fail("更新失败","队伍更新数量"+num);
        }
        return Result.success("队伍更新数量"+num);
    }

    public Result<String>insert(TeamUserMessage ele){
        boolean result=teamUserMessageDao.insert(ele);
        if(!result){
            return Result.fail("添加失败","队伍消息创建失败");
        }
        return Result.success("队伍消息创建成功");
    }



    /*---------------------------------------------------------------------------------------------*/
    /*---------------------------------------地摊信息------------------------------------------------*/
    /*---------------------------------------------------------------------------------------------*/
    public Result<PageBean<StallProjectMessageFix>> query(Integer currentPage, StallProjectMessage ele) {
        stallTaskMessageFixDao.initPage(currentPage, ele);
        PageBean<StallProjectMessageFix> pageBean =stallTaskMessageFixDao.queryByPage(currentPage, ele);
        if (pageBean.getListPage().isEmpty()){
            return Result.fail("队伍信息查询失败",pageBean);
        }
        return Result.success(pageBean);
    }

    public Result<PageBean<StallTeamMessageFix>> query(Integer currentPage, StallTeamMessage ele) {
        stallTeamMessageFixDao.initPage(currentPage, ele);
        PageBean<StallTeamMessageFix> pageBean =stallTeamMessageFixDao.queryByPage(currentPage, ele);
        if (pageBean.getListPage().isEmpty()){
            return Result.fail("队伍信息查询失败",pageBean);
        }
        return Result.success(pageBean);
    }

    public Result<PageBean<StallMentorMessageFix>>query(Integer currentPage, StallMentorMessage ele) {
        stallMentorMessageFixDao.initPage(currentPage, ele);
        PageBean<StallMentorMessageFix> pageBean =stallMentorMessageFixDao.queryByPage(currentPage, ele);
        if (pageBean.getListPage().isEmpty()){
            return Result.fail("队伍信息查询失败",pageBean);
        }
        return Result.success(pageBean);
    }

    public Result<String>update (StallProjectMessage ele, StallProjectMessage con){
        int num = stallTaskMessageDao.update(ele,con);
        if(num ==0){
            return Result.fail("更新失败","更新数量"+num);
        }
        return Result.success("更新数量"+num);
    }
    public Result<String>update (StallTeamMessage ele,StallTeamMessage con){
        int num = stallTeamMessageDao.update(ele,con);
        if(num ==0){
            return Result.fail("更新失败","更新数量"+num);
        }
        return Result.success("更新数量"+num);
    }
    public Result<String>update (StallMentorMessage ele,StallMentorMessage con){
        int num = stallMentorMessageDao.update(ele,con);
        if(num ==0){
            return Result.fail("更新失败","更新数量"+num);
        }
        return Result.success("更新数量"+num);
    }

    public Result<String>insert(StallProjectMessage ele){
        boolean result=stallTaskMessageDao.insert(ele);
        if(!result){
            return Result.fail("添加失败","队伍消息创建失败");
        }
        return Result.success("队伍消息创建成功");
    }
    public Result<String>insert(StallTeamMessage ele){
        boolean result=stallTeamMessageDao.insert(ele);
        if(!result){
            return Result.fail("添加失败","队伍消息创建失败");
        }
        return Result.success("队伍消息创建成功");
    }
    public Result<String>insert(StallMentorMessage ele){
        boolean result=stallMentorMessageDao.insert(ele);
        if(!result){
            return Result.fail("添加失败","队伍消息创建失败");
        }
        return Result.success("队伍消息创建成功");
    }


}
