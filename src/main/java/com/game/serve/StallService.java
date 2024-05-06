package com.game.serve;

import com.game.dao.*;
import com.game.domain.*;
import com.game.utils.Result;

import java.util.List;

public class StallService {
    private static final StallDao stalldao = new StallDao();
    private static final ProjectDao projectDao = new ProjectDao();
    private static final TeamDao teamDao = new TeamDao();
    private static final StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    private static final StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();

    public Result<String> insert(Team team, Project project) {
        List<Project> projectList = projectDao.query(project, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (projectList.isEmpty() || teamList.isEmpty()) {
            return Result.fail("添加失败", "无目标项目与队伍");
        }
        Stall stall = new Stall();
        stall.setU_acc(team.getU_acc());
        List<Stall> stallList = stalldao.query(stall, -1, -1);
        if (!stallList.isEmpty()) {
            for (Stall s : stallList) {
                StallProjectMessage spm = new StallProjectMessage();
                spm.setP_id(project.getP_id());
                spm.setSt_id(s.getSt_id());
                List<StallProjectMessage> spmList = stallProjectMessageDao.query(spm, -1, -1);
                if (!spmList.isEmpty()) {
                    return Result.fail("添加失败", "已存在该团队项目对应关系");
                }
            }
        }
        boolean inserted = stalldao.insert(stall);
        if (inserted) {
            StallTeamMessage stm = new StallTeamMessage();
            stm.setSt_id(stall.getSt_id());
            stm.setT_id(team.getT_id());
            StallProjectMessage spm = new StallProjectMessage();
            spm.setP_id(project.getP_id());
            spm.setSt_id(stall.getSt_id());
            boolean inserted1 = stallTeamMessageDao.insert(stm);
            boolean inserted2 = stallProjectMessageDao.insert(spm);
            if (inserted2&&inserted1) {
                return Result.success("添加成功");
            }
        }
        return Result.fail("添加失败", "添加失败");
    }

    public Result<String> update(Stall stall) {
        Stall tar = new Stall();
        tar.setSt_id(stall.getSt_id());
        List<Stall> exited = stalldao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该团队");
        }
        int updated = stalldao.update(stall,tar);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }
}
