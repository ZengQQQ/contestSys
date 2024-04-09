package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Team;

import java.util.List;
import java.util.Map;

public class TeamDao extends BaseDao<Team> {
    public TeamDao() {
        super("team");
    }

    public boolean insert(Team Team) {
        Map<String, Object> map = Team.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("队伍信息插入成功");
        }else {
            System.out.println("队伍信息插入失败");
        }
        return value;
    }


    public int delete(Team Team) {
        Map<String, Object> map = Team.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除队伍失败");
        }else {
            System.out.println("删除队伍成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<Team> query(Team Team, int start, int end) {
        Class<Team> clazz = Team.class;
        Map<String, Object> map = Team.toMap();
        List<Team>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍失败");
        }else {
            System.out.println("查询队伍成功");
            for (Team v : value){
                System.out.println(v);
            }
        }
        return value;
    }


    public int update(Team Team, Team mapCondition) {
        Map<String, Object> map = Team.toMap();
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新队伍失败");
        } else {
            System.out.println("更新队伍成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }

}
