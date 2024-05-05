package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamDao extends BaseDao<Team> {

    public TeamDao(){
     super("team");
    }

    private Team model;

    //查询队伍信息
    public List<Team> findAllTeam(){
        return super.query(Team.class,new HashMap<>(),0,0);
    }

    //插入队伍信息
    public boolean insertByAllinfos(Integer captain_id,String t_desc,Integer c_id,Integer t_num,String t_status){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("t_id",null);
        hashMap.put("captain_id",captain_id);
        hashMap.put("t_desc",t_desc);
        hashMap.put("t_num",t_num);
        hashMap.put("t_status",t_status);

        return super.insert(hashMap);
    }

    //修改队伍信息，根据队伍id
    public int updateTeamInfoByID(Integer t_id,Integer new_captain_id, String new_t_desc,
                                  Integer new_c_id,Integer new_t_num,String new_t_status){

        HashMap updateMap = new HashMap();
        if(new_t_desc!=null){
            updateMap.put("t_desc",new_t_desc);
        }

        updateMap.put("c_id",new_c_id);
        updateMap.put("t_num",new_t_num);
        updateMap.put("t_status",new_t_status);

        Map<String,Object> condition = new HashMap<>();
        condition.put("t_id",t_id);
        return super.update(updateMap,condition);
    }


    public boolean insert(Team team){
        Map<String,Object> map = team.toMap();
        return super.insert(map);
    }
    public int delete(Team team){
        return super.delete(team.toMap());
    }
    public List<Team> query(Team team,int start ,int end){
        return super.query(Team.class,team.toMap(),start,end);
    }

    public int update(Team team,Team teamCondition){
        Map<String,Object> map = team.toMap();
        map.remove("m_id");
        return super.update(map,teamCondition.toMap());
    }


    private PageBean<Team> pageBean = new PageBean<Team>();


    public void initPage( Team object){
        this.model=object;
        List<Team> total =new ArrayList<>();
        total=query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<Team> queryByPage(Integer currentPage, Team object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<Team> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }


    public static void main(String[] args) {
        TeamDao teamdao = new TeamDao();
        teamdao.insertByAllinfos(1,"为了荣耀",1,1,"组队中");
        List<Team> teams = teamdao.findAllTeam();
        for(Team team:teams){
            System.out.println(team);
        }
    }


}
