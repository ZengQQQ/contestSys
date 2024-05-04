package com.game.serve;

import com.game.dao.*;
import com.game.domain.Stall;
import com.game.domain.StallProjectMessage;
import com.game.domain.StallTeamMessage;
import com.game.domain.StallMentorMessage;
import com.game.domain.TeamUserMessage;
import com.game.utils.Result;

import com.alibaba.fastjson2.JSON;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationshipServe {
    TeamUserMessageDao teamUserMessageDao = new TeamUserMessageDao();
    StallDao stallDao = new StallDao();
    StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();


    public Result<String> updateTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        List<TeamUserMessage> teamUserMessageList = JSON.parseArray(jsonString,TeamUserMessage.class);
        TeamUserMessage teamUserMessage = teamUserMessageList.get(0);
        TeamUserMessage mapCondition = teamUserMessageList.get(1);
        int result=teamUserMessageDao.update(teamUserMessage,mapCondition);
        if(result == 0){
            return Result.fail("更新关系失败","队伍关系更新失败");
        }
        return Result.success("队伍关系更新成功");
    }


    public Result<String> updateStallProjectRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        List<StallProjectMessage> stallProjectMessageList = JSON.parseArray(jsonString,StallProjectMessage.class);
        StallProjectMessage spm = stallProjectMessageList.get(0);
        StallProjectMessage con = stallProjectMessageList.get(1);
        int result=stallProjectMessageDao.update(spm,con);
        if(result==0||spm.getSt_id()==null){
            return Result.fail("更新房间任务关系失败","房间任务关系更新失败");
        }
        return Result.success("房间任务关系更新成功");
    }



    public Result<String> updateStallTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        List<StallTeamMessage> stallTeamMessageList = JSON.parseArray(jsonString,StallTeamMessage.class);
        StallTeamMessage stm = stallTeamMessageList.get(0);
        StallTeamMessage con = stallTeamMessageList.get(1);
        int result=stallTeamMessageDao.update(stm,con);
        if(result==0||stm.getSt_id()==null){
            return Result.fail("更新房间队伍关系失败","房间队伍关系更新失败");
        }
        return Result.success("房间队伍关系更新成功");
    }


    public Result<String> updateStallMentorRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        List<StallMentorMessage> stallMentorMessageList = JSON.parseArray(jsonString,StallMentorMessage.class);
        StallMentorMessage smm = stallMentorMessageList.get(0);
        StallMentorMessage con = stallMentorMessageList.get(1);
        int result=stallMentorMessageDao.update(smm,con);
        if(result==0||smm.getSt_id()==null){
            return Result.fail("更新房间导师关系失败","房间导师关系更新失败");
        }
        return Result.success("房间导师关系更新成功");
    }

}
