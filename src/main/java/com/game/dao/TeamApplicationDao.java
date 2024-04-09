package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.TeamApplication;

import java.util.List;
import java.util.Map;

public class TeamApplicationDao extends BaseDao<TeamApplication> {
    public TeamApplicationDao() {
        super("team_application");
    }

    public boolean insert(TeamApplication TeamApplication) {
        Map<String, Object> map = TeamApplication.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("队伍申请信息插入成功");
        }else {
            System.out.println("队伍申请信息插入失败");
        }
        return value;
    }


    public int delete(TeamApplication TeamApplication) {
        Map<String, Object> map = TeamApplication.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除队伍申请失败");
        }else {
            System.out.println("删除队伍申请成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<TeamApplication> query(TeamApplication TeamApplication, int start, int end) {
        Class<TeamApplication> clazz = TeamApplication.class;
        Map<String, Object> map = TeamApplication.toMap();
        List<TeamApplication>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍申请失败");
        }else {
            System.out.println("查询队伍申请成功");
            for (TeamApplication v : value){
                System.out.println(v);
            }
        }
        return value;
    }


    public int update(TeamApplication TeamApplication, TeamApplication mapCondition) {
        Map<String, Object> map = TeamApplication.toMap();
        map.remove("ta_id");
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新队伍申请失败");
        } else {
            System.out.println("更新队伍申请成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }


    public int statistics(TeamApplication teamApplication) {
        Map<String, Object> map=teamApplication.toMap();
        return super.statistics(map);
    }
}
