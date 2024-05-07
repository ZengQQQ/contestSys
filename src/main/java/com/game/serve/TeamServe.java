package com.game.serve;

import com.game.dao.TeamDao;
import com.game.dao.*;
import com.game.domain.*;
import com.game.utils.Result;

import java.util.List;

public class TeamServe {

    private static final TeamDao teamDao = new TeamDao();
    private static final TeamUserMessageDao teamUserMessageDao = new TeamUserMessageDao();
    private static final StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    private static final StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    private static final StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();


    public Result<String> insert(Team team) {
        List<Team> exited = teamDao.query(team, -1, -1);
        if (!exited.isEmpty()) {
            return Result.fail("添加失败,团队已存在", "");
        }
        team.setT_curnum(1);
        boolean inserted = teamDao.insert(team);
        if (inserted) {
            return Result.success("添加成功");
        }
        return Result.fail("添加失败", "");
    }

    public Result<String> update(Team team) {
        Team tar = new Team();
        tar.setT_id(team.getT_id());
        List<Team> exited = teamDao.query(tar, -1, -1);
        if (exited.isEmpty()) {
            return Result.fail("更新失败,没有该团队", "");
        }
        if (team.getT_status() == 2) {
            TeamUserMessage tsm = new TeamUserMessage();
            tsm.setT_id(team.getT_id());
            tsm.setJoin_status(1);
            List<TeamUserMessage> teamUserMessages = teamUserMessageDao.query(tsm, -1, -1);
            if (teamUserMessages.isEmpty()) {
                int result1 = teamDao.update(team, tar);
                if (result1 == 0) {
                    return Result.fail("更新失败", "");
                }
                return Result.success("解散成功");
            }
            for (TeamUserMessage teamUserMessage : teamUserMessages) {
                TeamUserMessage teamUserMessage1 = new TeamUserMessage();
                teamUserMessage1 = teamUserMessage;
                teamUserMessage.setJoin_status(2);
                int result1 = teamUserMessageDao.update(teamUserMessage, teamUserMessage1);
                if (result1 == 0) {
                    return Result.fail("更新失败", "");
                }
            }
            StallTeamMessage stm = new StallTeamMessage();
            StallTeamMessage stallTeamMessage1 = new StallTeamMessage();
            StallProjectMessage stallProjectMessage1 = new StallProjectMessage();
            StallMentorMessage stallMentorMessage1 = new StallMentorMessage();
            Stall stall1 = new Stall();
            stm.setT_id(team.getT_id());
            stm.setJoin_status(1);
            stm.setStm_status(0);
            List<StallTeamMessage> stallTeamMessages = stallTeamMessageDao.query(stm, -1, -1);
            if (!stallTeamMessages.isEmpty()) {
                for (StallTeamMessage stallTeamMessage : stallTeamMessages) {
                    stallTeamMessage1 = stallTeamMessage;
                    stallTeamMessage.setJoin_status(0);
                    int result1 = stallTeamMessageDao.update(stallTeamMessage1, stallTeamMessage);
                    if (result1 == 0) {
                        return Result.fail("更新失败", "");
                    }
                }
            }
            StallProjectMessage spm = new StallProjectMessage();
            spm.setSt_id(team.getT_id());
            spm.setJoin_status(1);
            spm.setSpm_status(0);
            List<StallProjectMessage> stallProjectMessages = stallProjectMessageDao.query(spm, -1, -1);
            if (!stallProjectMessages.isEmpty()) {
                for (StallProjectMessage stallProjectMessage : stallProjectMessages) {
                    stallProjectMessage1 = stallProjectMessage;
                    stallProjectMessage.setJoin_status(0);
                    int result1 = stallProjectMessageDao.update(stallProjectMessage1, stallProjectMessage);
                    if (result1 == 0) {
                        return Result.fail("更新失败", "");
                    }
                }
            }
            StallMentorMessage smm = new StallMentorMessage();
            smm.setSt_id(team.getT_id());
            smm.setSmm_status(0);
            smm.setJoin_status(1);
            List<StallMentorMessage> stallMentorMessages = stallMentorMessageDao.query(smm, -1, -1);
            if (!stallMentorMessages.isEmpty()) {
                for (StallMentorMessage stallMentorMessage : stallMentorMessages) {
                    stallMentorMessage1 = stallMentorMessage;
                    stallMentorMessage.setJoin_status(0);
                    int result1 = stallMentorMessageDao.update(stallMentorMessage1, stallMentorMessage);
                    if (result1 == 0) {
                        return Result.fail("更新失败", "");
                    }
                }
            }
            Stall stall = new Stall();
            stall.setSt_id(team.getT_id());
            stall.setSt_status(0);
            List<Stall> stalls = new StallDao().query(stall, -1, -1);
            if (!stalls.isEmpty()) {
                for (Stall stall2 : stalls) {
                    stall1 = stall2;
                    stall2.setSt_status(1);
                    new StallDao().update(stall2, stall1);
                }
            }
            int result1 = teamDao.update(team, tar);
            if (result1 == 0) {
                return Result.fail("更新失败", "");
            }
            return Result.success("解散成功");
        }
        int result1 = teamDao.update(team, tar);
        if (result1 == 0) {
            return Result.fail("更新失败", "");
        }
        return Result.success("更新成功");
    }
}
