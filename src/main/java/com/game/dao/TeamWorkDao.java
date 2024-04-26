package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.secondary.teamDomain.TeamWork;

import java.util.List;
import java.util.Map;

public class TeamWorkDao extends BaseDao<TeamWork> {
    public TeamWorkDao() {
        super("team_work");
    }
    public boolean insert(TeamWork teamWork) {
        Map<String, Object> map = teamWork.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("队伍工作关系信息插入成功");
        }else {
            System.out.println("队伍工作关系信息插入失败");
        }
        return value;
    }


    public int delete(TeamWork teamWork) {
        Map<String, Object> map = teamWork.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除队伍工作关系失败");
        }else {
            System.out.println("删除队伍工作关系成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<TeamWork> query(TeamWork teamWork, int start, int end) {
        Class<TeamWork> clazz = TeamWork.class;
        Map<String, Object> map = teamWork.toMap();
        List<TeamWork>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍工作关系失败");
        }else {
            System.out.println("查询队伍工作关系成功");
            for (TeamWork m : value){
                System.out.println(m);
            }
        }
        return value;
    }


    public int update(TeamWork teamWork, TeamWork mapCondition) {
        Map<String, Object> map = teamWork.toMap();
        map.remove("m_id");
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新队伍工作关系失败");
        } else {
            System.out.println("更新队伍工作关系成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }


    public int statistics(TeamWork teamWork) {
        return super.statistics(teamWork);
    }

    private final PageBean<TeamWork> pageBean = new PageBean<TeamWork>();
    public PageBean<TeamWork> queryByPage(Integer currentPage,TeamWork object){
        List<TeamWork> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
