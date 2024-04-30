package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.secondary.teamMessageDomain.TeamInvite;

import java.util.List;

public class TeamInviteDao extends BaseDao<TeamInvite> {
    public TeamInviteDao() {
        super("team_invite");
    }

    @Override
    public boolean insert(TeamInvite object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("组队邀请添加成功");
        }else {
            System.out.println("组队邀请添加失败");
        }
        return value;
    }

    @Override
    public int delete(TeamInvite object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("组队邀请删除失败");
        }else {
            System.out.println("组队邀请删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<TeamInvite> query(TeamInvite object, int start, int end) {
        List<TeamInvite> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询组队邀请失败");
        }else {
            System.out.println("查询组队邀请成功");
            for (TeamInvite v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(TeamInvite object1, TeamInvite object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("组队邀请更新失败");
        }else {
            System.out.println("组队邀请更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }

    private final PageBean<TeamInvite> pageBean = new PageBean<TeamInvite>();

    public PageBean<TeamInvite> queryByPage(Integer currentPage,TeamInvite object) {
        List<TeamInvite> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}