package com.game.serve;

import com.game.dao.TeamDao;
import com.game.domain.Team;
import com.game.utils.Result;

import java.util.List;

public class TeamServe{

    private static final TeamDao teamDao = new TeamDao();

    public Result<String> insert(Team team) {
        List<Team> exited = teamDao.query(team,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("添加失败","团队已存在");
        }
        boolean inserted = teamDao.insert(team);
        if (inserted){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败","添加失败");
    }

    public Result<String> update(Team team) {
        Team tar = new Team();
        tar.setT_id(team.getT_id());
        List<Team> exited = teamDao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该团队");
        }
        int updated = teamDao.update(team,tar);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }
}
