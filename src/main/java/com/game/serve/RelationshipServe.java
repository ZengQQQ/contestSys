package com.game.serve;

import com.alibaba.fastjson2.JSONObject;
import com.game.dao.*;
import com.game.domain.Stall;
import com.game.domain.*;
import com.game.utils.Result;

import com.alibaba.fastjson2.JSON;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationshipServe {
    TeamUserMessageDao teamUserMessageDao = new TeamUserMessageDao();
    StallDao stallDao = new StallDao();
    ProjectDao projectDao = new ProjectDao();
    MentorDao mentorDao = new MentorDao();
    UserDao userDao = new UserDao();
    TeamDao teamDao = new TeamDao();
    StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();


    public Result<String> insertTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        TeamUserMessage teamUserMessage = JSON.parseObject(jsonString,TeamUserMessage.class);
        if (teamUserMessage.getT_id()==null||teamUserMessage.getU_acc()==null||teamUserMessage.getTsm_dct()==null){
            return Result.fail("添加关系失败","缺少参数");
        } else if (teamUserMessage.getTsm_dct() == 0){
            User user = new User();
            user.setU_acc(teamUserMessage.getU_acc());
            List<User> userList = userDao.query(user,-1,-1);
            if (userList.isEmpty()){
                return Result.fail("添加关系失败","没有该用户");
            }
            for (User u : userList){
                if (u.getU_status() != 0){
                    return Result.fail("添加关系失败","用户状态异常");
                }
            }
            boolean result = teamUserMessageDao.insert(teamUserMessage);
            if (!result){
                return Result.fail("添加关系失败","队伍关系添加失败");
            }
            return Result.success("队伍关系添加成功");
        } else if (teamUserMessage.getTsm_dct() == 1) {
            Team team = new Team();
            team.setT_id(teamUserMessage.getT_id());
            List<Team> teamList = teamDao.query(team,-1,-1);
            if (teamList.isEmpty()){
                return Result.fail("添加关系失败","没有该队伍");
            }
            for (Team t : teamList){
                if (t.getT_status() != 0){
                    return Result.fail("添加关系失败","队伍状态异常");
                }
            }
            boolean result = teamUserMessageDao.insert(teamUserMessage);
            if (!result){
                return Result.fail("添加关系失败","队伍关系添加失败");
            }
            return Result.success("队伍关系添加成功");
        }
        return Result.fail("添加关系失败","无法识别的关系类型");
    }


    public Result<String> updateTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        TeamUserMessage teamUserMessage = JSON.parseObject(jsonString,TeamUserMessage.class);
        User user = new User();
        user.setU_acc(teamUserMessage.getU_acc());
        Team team = new Team();
        team.setT_id(teamUserMessage.getT_id());
        List<User> userList = userDao.query(user,-1,-1);
        List<Team> teamList = teamDao.query(team,-1,-1);
        if (userList.isEmpty()||teamList.isEmpty()){
            return Result.fail("更新关系失败","没有该用户或队伍");
        }
        for (User u : userList){
            if (u.getU_status() != 0){
                return Result.fail("更新关系失败","用户状态异常");
            }
        }
        for (Team t : teamList) {
            if (t.getT_status() != 0) {
                return Result.fail("更新关系失败", "队伍状态异常");
            }
        }
        TeamUserMessage tar = new TeamUserMessage();
        tar.setT_id(teamUserMessage.getT_id());
        int result=teamUserMessageDao.update(teamUserMessage,tar);
        if(result == 0){
            return Result.fail("更新关系失败","队伍关系更新失败");
        }
        return Result.success("队伍关系更新成功");
    }


    public Result<String> insertStallProjectRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        StallProjectMessage stallProjectMessage = JSON.parseObject(jsonString,StallProjectMessage.class);
        Project project = new Project();
        Stall stall = new Stall();
        project.setP_id(stallProjectMessage.getP_id());
        stall.setSt_id(stallProjectMessage.getSt_id());
        List<Project> projectList = projectDao.query(project,-1,-1);
        List<Stall> stallList = stallDao.query(stall,-1,-1);
        if (projectList.isEmpty()||stallList.isEmpty()){
            return Result.fail("添加房间任务关系失败","没有该项目或房间");
        }
        for (Project p : projectList){
            if (p.getP_status() != 0){
                return Result.fail("添加房间任务关系失败","项目状态异常");
            }
        }
        for (Stall s : stallList){
            if (s.getSt_status() != 0){
                return Result.fail("添加房间任务关系失败","房间状态异常");
            }
        }
        boolean result = stallProjectMessageDao.insert(stallProjectMessage);
        if(!result||stallProjectMessage.getSt_id()==null||stallProjectMessage.getSpm_dct()==null){
            return Result.fail("添加房间任务关系失败","房间任务关系添加失败");
        }
        return Result.success("房间任务关系添加成功");
    }


    public Result<String> updateStallProjectRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        StallProjectMessage stallProjectMessage = JSON.parseObject(jsonString,StallProjectMessage.class);
        List<StallProjectMessage> resList = stallProjectMessageDao.query(stallProjectMessage,-1,-1);
        for (StallProjectMessage res : resList){
            if (res.getSt_id() == null){
                return Result.fail("更新房间任务关系失败","没有该房间任务关系");
            }
        }
        StallProjectMessage tar = new StallProjectMessage();
        Stall stall = new Stall();
        Project project = new Project();
        project.setP_id(stallProjectMessage.getP_id());
        stall.setSt_id(stallProjectMessage.getSt_id());
        List<Project> projectList = projectDao.query(project,-1,-1);
        List<Stall> stallList = stallDao.query(stall,-1,-1);
        if (projectList.isEmpty()||stallList.isEmpty()){
            return Result.fail("更新房间任务关系失败","没有该项目或房间");
        }
        for (Project p : projectList){
            if (p.getP_status() != 0){
                return Result.fail("更新房间任务关系失败","项目状态异常");
            }
        }
        for (Stall s : stallList){
            if (s.getSt_status() != 0){
                return Result.fail("更新房间任务关系失败","房间状态异常");
            }
        }
        tar.setSpm_id(stallProjectMessage.getSpm_id());
        int result=stallProjectMessageDao.update(stallProjectMessage,tar);
        if(result==0){
            return Result.fail("更新房间任务关系失败","房间任务关系更新失败");
        }
        return Result.success("房间任务关系更新成功");
    }


    public Result<String> insertStallTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        StallTeamMessage stallTeamMessage = JSON.parseObject(jsonString,StallTeamMessage.class);
        Team team = new Team();
        Stall stall = new Stall();
        team.setT_id(stallTeamMessage.getT_id());
        stall.setSt_id(stallTeamMessage.getSt_id());
        List<Team> teamList = teamDao.query(team,-1,-1);
        List<Stall> stallList = stallDao.query(stall,-1,-1);
        if (teamList.isEmpty()||stallList.isEmpty()){
            return Result.fail("添加房间队伍关系失败","没有该队伍或房间");
        }
        for (Team t : teamList){
            if (t.getT_status() != 0){
                return Result.fail("添加房间队伍关系失败","队伍状态异常");
            }
        }
        for (Stall s : stallList){
            if (s.getSt_status() != 0){
                return Result.fail("添加房间队伍关系失败","房间状态异常");
            }
        }
        boolean result = stallTeamMessageDao.insert(stallTeamMessage);
        if(!result){
            return Result.fail("添加房间队伍关系失败","房间队伍关系添加失败");
        }
        return Result.success("房间队伍关系添加成功");
    }


    public Result<String> updateStallTeamRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        StallTeamMessage stallTeamMessage = JSON.parseObject(jsonString,StallTeamMessage.class);
        List<StallTeamMessage> resList = stallTeamMessageDao.query(stallTeamMessage,-1,-1);
        for (StallTeamMessage res : resList){
            if (res.getSt_id() == null){
                return Result.fail("更新房间队伍关系失败","没有该房间队伍关系");
            }
        }
        StallTeamMessage tar = new StallTeamMessage();
        tar.setStm_id(stallTeamMessage.getStm_id());
        Team team = new Team();
        Stall stall = new Stall();
        team.setT_id(stallTeamMessage.getT_id());
        stall.setSt_id(stallTeamMessage.getSt_id());
        List<Team> teamList = teamDao.query(team,-1,-1);
        List<Stall> stallList = stallDao.query(stall,-1,-1);
        if (teamList.isEmpty()||stallList.isEmpty()){
            return Result.fail("更新房间队伍关系失败","没有该队伍或房间");
        }
        for (Team t : teamList){
            if (t.getT_status() != 0){
                return Result.fail("更新房间队伍关系失败","队伍状态异常");
            }
        }
        for (Stall s : stallList){
            if (s.getSt_status() != 0){
                return Result.fail("更新房间队伍关系失败","房间状态异常");
            }
        }
        int result=stallTeamMessageDao.update(stallTeamMessage,tar);
        if(result==0){
            return Result.fail("更新房间队伍关系失败","房间队伍关系更新失败");
        }
        return Result.success("房间队伍关系更新成功");
    }


    public Result<String> insertStallMentorRelation(Object ele){
        String jsonString = JSON.toJSONString(ele);
        StallMentorMessage stallMentorMessage = JSON.parseObject(jsonString,StallMentorMessage.class);
        User user = new User();
        Stall stall = new Stall();
        user.setU_acc(stallMentorMessage.getU_acc());
        stall.setSt_id(stallMentorMessage.getSt_id());
        List<User> mentorList = userDao.query(user,-1,-1);
        List<Stall> stallList = stallDao.query(stall,-1,-1);
        if (mentorList.isEmpty()||stallList.isEmpty()){
            return Result.fail("添加房间导师关系失败","没有该导师或房间");
        }
        for (User u : mentorList){
            if (u.getU_status() != 0){
                return Result.fail("添加房间导师关系失败","导师状态异常");
            }
        }
        for (Stall s : stallList){
            if (s.getSt_status() != 0){
                return Result.fail("添加房间导师关系失败","房间状态异常");
            }
        }
        boolean result = stallMentorMessageDao.insert(stallMentorMessage);
        if(!result){
            return Result.fail("添加房间导师关系失败","房间导师关系添加失败");
        }
        return Result.success("房间导师关系添加成功");
    }


    public Result<String> updateStallMentorRelation(StallMentorMessage stallMentorMessage){
//        String jsonString = JSON.toJSONString(ele);
//        StallMentorMessage stallMentorMessage = JSON.parseObject(jsonString,StallMentorMessage.class);
        StallMentorMessage tar = new StallMentorMessage();
        tar.setSmm_id(stallMentorMessage.getSmm_id());
        List<StallMentorMessage> resList = stallMentorMessageDao.query(tar,-1,-1);
        for (StallMentorMessage res : resList){
            if (res.getSt_id() == null){
                return Result.fail("更新房间导师关系失败","没有该房间导师关系");
            }
        }
        User user = new User();
        Stall stall = new Stall();
        user.setU_acc(stallMentorMessage.getU_acc());
        stall.setSt_id(stallMentorMessage.getSt_id());
        List<User> mentorList = userDao.query(user,-1,-1);
        List<Stall> stallList = stallDao.query(stall,-1,-1);
        if (mentorList.isEmpty()||stallList.isEmpty()){
            return Result.fail("更新房间导师关系失败","没有该导师或房间");
        }
        for (User u : mentorList){
            if (u.getU_status() != 0){
                return Result.fail("更新房间导师关系失败","导师状态异常");
            }
        }
        for (Stall s : stallList){
            if (s.getSt_status() != 0){
                return Result.fail("更新房间导师关系失败","房间状态异常");
            }
        }
        int result=stallMentorMessageDao.update(tar,stallMentorMessage);
        if(result==0){
            return Result.fail("更新房间导师关系失败","房间导师关系更新失败");
        }
        return Result.success("房间导师关系更新成功");
    }

}
