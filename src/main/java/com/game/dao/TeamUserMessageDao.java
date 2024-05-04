package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.TeamUserMessage;
import com.game.domain.User;

import java.util.List;
import java.util.Map;

public class TeamUserMessageDao extends BaseDao<TeamUserMessage> {

    public TeamUserMessageDao() {
        super("team_user_message");
    }


    public boolean insert(TeamUserMessage TeamUserMessage) {
        Map<String, Object> map = TeamUserMessage.toMap();
        boolean value = super.insert(map);
        if (value){
            System.out.println("TeamUserMessage信息插入成功");
        }else {
            System.out.println("TeamUserMessage信息插入失败");
        }
        return value;
    }


    public int delete(TeamUserMessage TeamUserMessage) {
        Map<String, Object> map = TeamUserMessage.toMap();
        int value = super.delete(map);
        if (value==0){
            System.out.println("删除TeamUserMessage失败");
        }else {
            System.out.println("删除TeamUserMessage成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    public List<TeamUserMessage> query(TeamUserMessage TeamUserMessage, int start, int end) {
        Class<TeamUserMessage> clazz = TeamUserMessage.class;
        Map<String, Object> map = TeamUserMessage.toMap();
        List<TeamUserMessage>value = super.query(clazz, map, start, end);
        if (value.isEmpty()){
            System.out.println("查询TeamUserMessage失败");
        }else {
            System.out.println("查询TeamUserMessage成功");
            for (TeamUserMessage v : value){
                System.out.println(v);
            }
        }
        return value;
    }


    public int update(TeamUserMessage TeamUserMessage, TeamUserMessage mapCondition) {
        Map<String, Object> map = TeamUserMessage.toMap();
        map.remove("s_id");
        Map<String, Object> condition = mapCondition.toMap();
        int value = super.update(map, condition);
        if (value == 0) {
            System.out.println("更新TeamUserMessage失败");
        } else {
            System.out.println("更新TeamUserMessage成功！");
            System.out.println("更新了" + value + "条数据");

        }
        return value;
    }
    private PageBean<TeamUserMessage> pageBean = new PageBean<TeamUserMessage>();


    public void initPage(Integer currentPage, TeamUserMessage object){
        List<TeamUserMessage> total =query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<TeamUserMessage> queryByPage(Integer currentPage, TeamUserMessage object){
        List<TeamUserMessage> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }
    public static void main(String[] args) {
        TeamUserMessageDao dao =new TeamUserMessageDao();
        List<TeamUserMessage> TeamUserMessages=dao.leftQuery(TeamUserMessage.class,"user",BaseDao.formList(new TeamUserMessage(),new User()),-1,-1);
        for (TeamUserMessage s:TeamUserMessages){
            System.out.println(s);
        }
    }
}
