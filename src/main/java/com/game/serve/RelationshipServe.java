package com.game.serve;

import com.alibaba.fastjson2.JSONObject;
import com.game.dao.*;
import com.game.domain.Stall;
import com.game.domain.*;
import com.game.utils.Result;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RelationshipServe {
    TeamUserMessageDao teamUserMessageDao = new TeamUserMessageDao();
    StallDao stallDao = new StallDao();
    ProjectDao projectDao = new ProjectDao();
    UserDao userDao = new UserDao();
    private static final TeamDao teamDao = new TeamDao();
    private static final StudentDao studentDao = new StudentDao();
    TeamServe teamServe = new TeamServe();
    StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();


    public Result<String> insertTeamRelation(TeamUserMessage teamUserMessage) {
        if (teamUserMessage.getT_id() == null || teamUserMessage.getU_acc() == null || teamUserMessage.getTsm_dct() == null) {
            return Result.fail("添加关系失败,缺少参数", "");
        } else if (teamUserMessage.getTsm_dct() == 0 || teamUserMessage.getTsm_dct() == 1) {
            User user = new User();
            user.setU_acc(teamUserMessage.getU_acc());
            List<User> userList = userDao.query(user, -1, -1);
            if (userList.isEmpty()) {
                return Result.fail("添加关系失败,没有该用户", "");
            }
            Student student = new Student();
            student.setS_acc(teamUserMessage.getU_acc());
            List<Student> students = studentDao.query(student, -1, -1);
            if (students.isEmpty()) {
                return Result.fail("添加关系失败,仅可学生加入队伍", "");
            }
            for (User u : userList) {
                if (u.getU_status() != 0) {
                    return Result.fail("添加关系失败,用户状态异常", "");
                }
            }
            Team team = new Team();
            team.setT_id(teamUserMessage.getT_id());
            List<Team> teamList = teamDao.query(team, -1, -1);
            if (teamList.isEmpty()) {
                return Result.fail("添加关系失败,没有该队伍", "");
            }
            for (Team t : teamList) {
                if (t.getT_status() != 0) {
                    return Result.fail("添加关系失败,队伍状态异常", "");
                }
            }
            team = teamList.get(0);
            if (Objects.equals(team.getU_acc(), user.getU_acc())){
                return Result.fail("添加关系失败,队长无法加入自己的队伍", "");
            }
            boolean result = teamUserMessageDao.insert(teamUserMessage);
            if (!result) {
                return Result.fail("添加关系失败,队伍关系添加失败", "");
            }
            return Result.success("队伍关系添加成功");
        }
        return Result.fail("添加关系失败,无法识别的关系方向", "");
    }

    /**
     * 管理员封禁team user message
     *
     * @param teamUserMessage 修改后的team user message，包含t_id，tsm_status
     * @return Result<String> 修改结果
     */

    public Result<String> BanTeamUserMessage(TeamUserMessage teamUserMessage) {
        TeamUserMessage tar = new TeamUserMessage();
        tar.setT_id(teamUserMessage.getT_id());
        List<TeamUserMessage> resList = teamUserMessageDao.query(tar, -1, -1);
        if (resList.isEmpty()) {
            return Result.fail("没有该消息", null);
        }
        int result = teamUserMessageDao.update(teamUserMessage, tar);
        if (result == 0) {
            return Result.fail("封禁消息失败", null);
        }
        return Result.success("封禁成功");
    }

    public Result<String> updateTeamRelation(TeamUserMessage teamUserMessage) {
        TeamUserMessage tar = new TeamUserMessage();
        tar.setT_id(teamUserMessage.getT_id());
        tar.setU_acc(teamUserMessage.getU_acc());
        List<TeamUserMessage> resList = teamUserMessageDao.query(tar, -1, -1);
        if (resList.isEmpty()) {
            return Result.fail("更新学生队伍关系失败,没有该学生队伍关系", "");
        }
        User user = new User();
        user.setU_acc(teamUserMessage.getU_acc());
        Team team = new Team();
        Team tar1 = new Team();
        team.setT_id(teamUserMessage.getT_id());
        List<User> userList = userDao.query(user, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (userList.isEmpty() || teamList.isEmpty()) {
            return Result.fail("更新关系失败,没有该用户或队伍", "");
        }
        for (User u : userList) {
            if (u.getU_status() != 0) {
                return Result.fail("更新关系失败,用户状态异常", "");
            }
        }
        for (Team t : teamList) {
            if (t.getT_status() != 0) {
                return Result.fail("更新关系失败,队伍状态异常", "");
            }
        }
        team = teamList.get(0);
        if (teamUserMessage.getTsm_pass() == 1) {
            TeamUserMessage tar2 = new TeamUserMessage();
            tar2.setT_id(teamUserMessage.getT_id());
            tar2.setJoin_status(1);
            List<TeamUserMessage> resList2 = teamUserMessageDao.query(tar2, -1, -1);
            if (resList2.size() >= team.getT_maxnum()) {
                return Result.fail("更新关系失败,队伍人数已满", "");
            }
            teamUserMessage.setJoin_status(1);
            tar1.setT_id(teamUserMessage.getT_id());
            team.setT_curnum(team.getT_curnum() + 1);
        } else if (teamUserMessage.getTsm_pass() == 2) {
            teamUserMessage.setJoin_status(0);
        } else if (teamUserMessage.getJoin_status() == 2) {
            tar1.setT_id(teamUserMessage.getT_id());
            team.setT_curnum(team.getT_curnum() - 1);
        }
        int result1 = teamUserMessageDao.update(teamUserMessage, tar);
        int result2 = teamDao.update(team, tar1);
        if (result1 == 0 || result2 == 0) {
            return Result.fail("更新关系失败", "队伍关系更新失败");
        }
        return Result.fail("添加关系失败,无法识别的关系类型", "");
    }


    public Result<String> insertStallProjectRelation(StallProjectMessage stallProjectMessage) {
        Project project = new Project();
        Stall stall = new Stall();
        project.setP_id(stallProjectMessage.getP_id());
        stall.setSt_id(stallProjectMessage.getSt_id());
        List<Project> projectList = projectDao.query(project, -1, -1);
        List<Stall> stallList = stallDao.query(stall, -1, -1);
        if (projectList.isEmpty() || stallList.isEmpty()) {
            return Result.fail("添加房间任务关系失败", "没有该项目或房间");
        }
        for (Project p : projectList) {
            if (p.getP_status() != 0) {
                return Result.fail("添加房间任务关系失败", "项目状态异常");
            }
        }
        for (Stall s : stallList) {
            if (s.getSt_status() != 0) {
                return Result.fail("添加房间任务关系失败", "房间状态异常");
            }
        }
        boolean result = stallProjectMessageDao.insert(stallProjectMessage);
        if (!result || stallProjectMessage.getSt_id() == null || stallProjectMessage.getSpm_dct() == null) {
            return Result.fail("添加房间任务关系失败", "房间任务关系添加失败");
        }
        return Result.success("房间任务关系添加成功");
    }


    public Result<String> updateStallProjectRelation(StallProjectMessage stallProjectMessage) {
        StallProjectMessage tar = new StallProjectMessage();
        tar.setSt_id(stallProjectMessage.getSt_id());
        tar.setP_id(stallProjectMessage.getP_id());
        List<StallProjectMessage> resList = stallProjectMessageDao.query(tar, -1, -1);
        if (resList.isEmpty()) {
            return Result.fail("更新房间任务关系失败", "没有该房间任务关系");
        }
        Stall stall = new Stall();
        Project project = new Project();
        project.setP_id(stallProjectMessage.getP_id());
        stall.setSt_id(stallProjectMessage.getSt_id());
        List<Project> projectList = projectDao.query(project, -1, -1);
        List<Stall> stallList = stallDao.query(stall, -1, -1);
        if (projectList.isEmpty() || stallList.isEmpty()) {
            return Result.fail("更新房间任务关系失败", "没有该项目或房间");
        }
        for (Project p : projectList) {
            if (p.getP_status() != 0) {
                return Result.fail("更新房间任务关系失败", "项目状态异常");
            }
        }
        for (Stall s : stallList) {
            if (s.getSt_status() != 0) {
                return Result.fail("更新房间任务关系失败", "房间状态异常");
            }
        }
        tar.setSpm_id(stallProjectMessage.getSpm_id());
        int result = stallProjectMessageDao.update(stallProjectMessage, tar);
        if (result == 0) {
            return Result.fail("更新房间任务关系失败", "房间任务关系更新失败");
        }
        return Result.success("房间任务关系更新成功");
    }


    public Result<String> insertStallTeamRelation(StallTeamMessage stallTeamMessage) {
        Team team = new Team();
        Stall stall = new Stall();
        team.setT_id(stallTeamMessage.getT_id());
        stall.setSt_id(stallTeamMessage.getSt_id());
        List<Team> teamList = teamDao.query(team, -1, -1);
        List<Stall> stallList = stallDao.query(stall, -1, -1);
        if (teamList.isEmpty() || stallList.isEmpty()) {
            return Result.fail("添加房间队伍关系失败", "没有该队伍或房间");
        }
        for (Team t : teamList) {
            if (t.getT_status() != 0) {
                return Result.fail("添加房间队伍关系失败", "队伍状态异常");
            }
        }
        for (Stall s : stallList) {
            if (s.getSt_status() != 0) {
                return Result.fail("添加房间队伍关系失败", "房间状态异常");
            }
        }
        boolean result = stallTeamMessageDao.insert(stallTeamMessage);
        if (!result) {
            return Result.fail("添加房间队伍关系失败", "房间队伍关系添加失败");
        }
        return Result.success("房间队伍关系添加成功");
    }


    public Result<String> updateStallTeamRelation(StallTeamMessage stallTeamMessage) {
        StallTeamMessage tar = new StallTeamMessage();
        tar.setSt_id(stallTeamMessage.getSt_id());
        tar.setT_id(stallTeamMessage.getT_id());
        List<StallTeamMessage> resList = stallTeamMessageDao.query(tar, -1, -1);
        if (resList.isEmpty()) {
            return Result.fail("更新房间队伍关系失败", "没有该房间队伍关系");
        }
        Team team = new Team();
        Stall stall = new Stall();
        team.setT_id(stallTeamMessage.getT_id());
        stall.setSt_id(stallTeamMessage.getSt_id());
        List<Team> teamList = teamDao.query(team, -1, -1);
        List<Stall> stallList = stallDao.query(stall, -1, -1);
        if (teamList.isEmpty() || stallList.isEmpty()) {
            return Result.fail("更新房间队伍关系失败", "没有该队伍或房间");
        }
        for (Team t : teamList) {
            if (t.getT_status() != 0) {
                return Result.fail("更新房间队伍关系失败", "队伍状态异常");
            }
        }
        for (Stall s : stallList) {
            if (s.getSt_status() != 0) {
                return Result.fail("更新房间队伍关系失败", "房间状态异常");
            }
        }
        int result = stallTeamMessageDao.update(stallTeamMessage, tar);
        if (result == 0) {
            return Result.fail("更新房间队伍关系失败", "房间队伍关系更新失败");
        }
        return Result.success("房间队伍关系更新成功");
    }


    public Result<String> insertStallMentorRelation(StallMentorMessage stallMentorMessage) {
        User user = new User();
        Stall stall = new Stall();
        user.setU_acc(stallMentorMessage.getU_acc());
        stall.setSt_id(stallMentorMessage.getSt_id());
        List<User> mentorList = userDao.query(user, -1, -1);
        List<Stall> stallList = stallDao.query(stall, -1, -1);
        if (mentorList.isEmpty() || stallList.isEmpty()) {
            return Result.fail("添加房间导师关系失败", "没有该导师或房间");
        }
        for (User u : mentorList) {
            if (u.getU_status() != 0) {
                return Result.fail("添加房间导师关系失败", "导师状态异常");
            }
        }
        for (Stall s : stallList) {
            if (s.getSt_status() != 0) {
                return Result.fail("添加房间导师关系失败", "房间状态异常");
            }
        }
        boolean result = stallMentorMessageDao.insert(stallMentorMessage);
        if (!result) {
            return Result.fail("添加房间导师关系失败", "房间导师关系添加失败");
        }
        return Result.success("房间导师关系添加成功");
    }


    public Result<String> updateStallMentorRelation(StallMentorMessage stallMentorMessage) {
        StallMentorMessage tar = new StallMentorMessage();
        tar.setSt_id(stallMentorMessage.getSt_id());
        tar.setU_acc(stallMentorMessage.getU_acc());
        List<StallMentorMessage> resList = stallMentorMessageDao.query(tar, -1, -1);
        if (resList.isEmpty()) {
            return Result.fail("更新房间导师关系失败", "没有该房间导师关系");
        }
        User user = new User();
        Stall stall = new Stall();
        user.setU_acc(stallMentorMessage.getU_acc());
        stall.setSt_id(stallMentorMessage.getSt_id());
        List<User> mentorList = userDao.query(user, -1, -1);
        List<Stall> stallList = stallDao.query(stall, -1, -1);
        if (mentorList.isEmpty() || stallList.isEmpty()) {
            return Result.fail("更新房间导师关系失败", "没有该导师或房间");
        }
        for (User u : mentorList) {
            if (u.getU_status() != 0) {
                return Result.fail("更新房间导师关系失败", "导师状态异常");
            }
        }
        for (Stall s : stallList) {
            if (s.getSt_status() != 0) {
                return Result.fail("更新房间导师关系失败", "房间状态异常");
            }
        }
        int result = stallMentorMessageDao.update(stallMentorMessage, tar);
        if (result == 0) {
            return Result.fail("更新房间导师关系失败", "房间导师关系更新失败");
        }
        return Result.success("房间导师关系更新成功");
    }

}
