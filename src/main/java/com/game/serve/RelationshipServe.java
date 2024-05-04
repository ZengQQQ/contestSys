package com.game.serve;

import com.game.dao.StallDao;
import com.game.domain.Stall;
import com.game.utils.Result;
import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.Map;

public class RelationshipServe {
    TeamUserLinkDao teamUserLinkDao = new TeamUserLinkDao();
    StallDao stallDao = new StallDao();

    public Result<String> buildTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        TeamUserLink teamUserLink = JSON.parseObject(jsonString,TeamUserLink.class);
        boolean result=teamUserLinkDao.insert(teamUserLink);
        if(!result){
            return Result.fail("添加关系失败","队伍关系创建失败");
        }
        return Result.success("队伍关系创建成功");
    }

    public Result<String> deleteTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        TeamUserLink teamUserLink = JSON.parseObject(jsonString,TeamUserLink.class);
        int result=teamUserLinkDao.delete(teamUserLink);
        if(result==0){
            return Result.fail("删除关系失败","队伍关系删除失败");
        }
        return Result.success("队伍关系删除成功");
    }




    public Result<String> buildStallRelation(Object ele){
        Stall con = new Stall();
        String jsonString = JSON.toJSONString(ele);
        Stall stall= JSON.parseObject(jsonString, Stall.class);
        con.setSt_id(stall.getSt_id());
        int result=stallDao.update(stall,con);
        if(result==0||con.getSt_id()==null){
            return Result.fail("添加关系失败","地摊关系创建失败");
        }
        return Result.success("地摊关系创建成功");
    }


    public Result<String> deleteStallMentorRelation(Object tem){
        Stall con = new Stall();
        Stall ele = new Stall();
        String jsonString = JSON.toJSONString(tem);
        Stall stall= JSON.parseObject(jsonString, Stall.class);
        ele.setSt_id(stall.getSt_id());
        con.setSt_id(stall.getSt_id());
        Map<String,Object> eleMap = ele.toMap();
        Map<String,Object> conMap = con.toMap();
        eleMap.put("m_acc",null);
        int result=stallDao.update(eleMap,conMap);
        if(result!=0||con.getSt_id()==null){
            return Result.fail("删除关系失败","删除地摊导师关系失败");
        }
        return Result.success("地摊导师关系删除成功");
    }
    public Result<String> deleteStallTaskRelation(Object tem){
        String jsonString = JSON.toJSONString(tem);
        Stall stall= JSON.parseObject(jsonString, Stall.class);
        Map<String,Object> eleMap = new HashMap<>();
        Map<String,Object> conMap = new HashMap<>();
        conMap.put("st_id",stall.getSt_id());
        eleMap.put("st_id",stall.getSt_id());
        eleMap.put("p_id",null);
        int result=stallDao.update(eleMap,conMap);
        if(result!=0||stall.getSt_id()==null){
            return Result.fail("删除关系失败","地摊项目创建失败");
        }
        return Result.success("地摊关系创建成功");
    }
    public Result<String> deleteStallTeamRelation(Object tem){
        String jsonString = JSON.toJSONString(tem);
        Stall stall= JSON.parseObject(jsonString, Stall.class);
        Map<String,Object> eleMap = new HashMap<>();
        Map<String,Object> conMap = new HashMap<>();
        conMap.put("st_id",stall.getSt_id());
        eleMap.put("st_id",stall.getSt_id());
        eleMap.put("t_id",null);
        int result=stallDao.update(eleMap,conMap);
        if(result!=0||stall.getSt_id()==null){
            return Result.fail("添加关系失败","地摊关系创建失败");
        }
        return Result.success("地摊关系创建成功");
    }
}
