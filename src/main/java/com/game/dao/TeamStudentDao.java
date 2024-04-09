package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.TeamStudent;

import java.util.List;
import java.util.Map;

public class TeamStudentDao extends BaseDao<TeamStudent> {
    public TeamStudentDao() {
        super("team_student");
    }

    public boolean insert(TeamStudent TeamStudent) {
        Map<String, Object> map = TeamStudent.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("队伍学生信息插入成功");
        }else {
            System.out.println("队伍学生信息插入失败");
        }
        return value;
    }


    public int delete(TeamStudent TeamStudent) {
        Map<String, Object> map = TeamStudent.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除队伍学生失败");
        }else {
            System.out.println("删除队伍学生成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<TeamStudent> query(TeamStudent TeamStudent, int start, int end) {
        Class<TeamStudent> clazz = TeamStudent.class;
        Map<String, Object> map = TeamStudent.toMap();
        List<TeamStudent>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍学生失败");
        }else {
            System.out.println("查询队伍学生成功");
            for (TeamStudent v : value){
                System.out.println(v);
            }
        }
        return value;
    }


    public int update(TeamStudent TeamStudent, TeamStudent mapCondition) {
        Map<String, Object> map = TeamStudent.toMap();
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新队伍学生失败");
        } else {
            System.out.println("更新队伍学生成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }

    public int statistics(TeamStudent teamStudent) {
        Map<String, Object> map = teamStudent.toMap();
        return super.statistics(map);
    }
}
