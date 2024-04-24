package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.secondary.teamDomain.TeamMentor;

import java.util.List;
import java.util.Map;

public class TeamMentorDao extends BaseDao<TeamMentor> {
    public TeamMentorDao() {
        super("team_mentor");
    }

    public boolean insert(TeamMentor TeamMentor) {
        Map<String, Object> map = TeamMentor.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("队伍指导教师信息插入成功");
        }else {
            System.out.println("队伍指导教师信息插入失败");
        }
        return value;
    }


    public int delete(TeamMentor TeamMentor) {
        Map<String, Object> map = TeamMentor.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除队伍指导教师失败");
        }else {
            System.out.println("删除队伍指导教师成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<TeamMentor> query(TeamMentor TeamMentor, int start, int end) {
        Class<TeamMentor> clazz = TeamMentor.class;
        Map<String, Object> map = TeamMentor.toMap();
        List<TeamMentor>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍指导教师失败");
        }else {
            System.out.println("查询队伍指导教师成功");
            for (TeamMentor v : value){
                System.out.println(v);
            }
        }
        return value;
    }


    public int update(TeamMentor TeamMentor, TeamMentor mapCondition) {
        Map<String, Object> map = TeamMentor.toMap();
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新队伍指导教师失败");
        } else {
            System.out.println("更新队伍指导教师成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }


    public int statistics(TeamMentor teamMentor) {
        return super.statistics(teamMentor);
    }
}
