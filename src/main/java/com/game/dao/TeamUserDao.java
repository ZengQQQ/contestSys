package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.secondary.teamDomain.TeamUser;

import java.util.List;

public class TeamUserDao extends BaseDao<TeamUser> {
    public TeamUserDao() {
        super("team_user");
    }

    public boolean insert(TeamUser object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("队伍用户关系添加成功");
        }else {
            System.out.println("队伍用户关系添加失败");
        }
        return value;
    }

    @Override
    public int delete(TeamUser object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("队伍用户关系删除失败");
        }else {
            System.out.println("队伍用户关系删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<TeamUser> query(TeamUser object, int start, int end) {
        List<TeamUser> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍用户关系失败");
        }else {
            System.out.println("查询队伍用户关系成功");
            for (TeamUser v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(TeamUser object1, TeamUser object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("队伍用户关系更新失败");
        }else {
            System.out.println("队伍用户关系更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }
    private final PageBean<TeamUser> pageBean = new PageBean<TeamUser>();

    public PageBean<TeamUser> queryByPage(Integer currentPage,TeamUser object) {
        List<TeamUser> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
