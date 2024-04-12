package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.*;
import com.game.dao.base.BaseDao;
import com.game.domain.Mentor;
import com.game.domain.Student;
import com.game.domain.TeamRelation;
import com.game.domain.secondary.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamRelationServe extends BaseDao<TeamRelation> {
    private final PageBean<TeamRelation> pageBean = new PageBean<TeamRelation>();
    public List<TeamRelation> query(Team team, Student student, Mentor mentor){
        List<TeamRelation> teamRelationsList=new ArrayList<>();
        TeamDao teamDao = new TeamDao();
        StudentDao studentDao = new StudentDao();
        MentorDao mentorDao = new MentorDao();
        Map<String,Map<String,Object>> map = new HashMap<String,Map<String,Object>>();
        Map<String,String> joinCondition = new HashMap<String,String>();
        String mainTable = "team";
        map.put("team",team.toMap());
        map.put("student",student.toMap());
        map.put("mentor",mentor.toMap());
        joinCondition.put("team_mentor","team_mentor.t_id = team.t_id");
        joinCondition.put("team_student","team_student.t_id = team.t_id");
        joinCondition.put("student","team_student.s_id = student.s_id");
        joinCondition.put("mentor","team_mentor.m_id = mentor.m_id");
        List<Team> teamList=teamDao.leftQuery(Team.class,mainTable,map,joinCondition,-1,-1);
        for (Team team1:teamList){
            try {
                Map<String,Map<String,Object>> mapTem = new HashMap<String,Map<String,Object>>();
                Map<String,String> joinConditionTem = new HashMap<String,String>();

                mapTem.put("team",team1.toMap());
                mapTem.put("student",student.toMap());
                mapTem.put("mentor",mentor.toMap());
                joinConditionTem.put("team_mentor","team_mentor.t_id = team.t_id");
                joinConditionTem.put("team_student","team_student.t_id = team.t_id");
                joinConditionTem.put("student","team_student.s_id = student.s_id");
                joinConditionTem.put("mentor","team_mentor.m_id = mentor.m_id");
                List<Student> studentList = studentDao.leftQuery(Student.class,mainTable,mapTem,joinConditionTem,-1,-1);
                List<Mentor> mentorList = mentorDao.leftQuery(Mentor.class,mainTable,mapTem,joinConditionTem,-1,-1);
                TeamRelation teamRelation = new TeamRelation(team1,mentorList,studentList);
                teamRelationsList.add(teamRelation);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return teamRelationsList;
    }
    public PageBean<TeamRelation> queryByPage(Integer currentPage, Team team, Student student, Mentor mentor){
        List<TeamRelation> result = null;
        pageBean.setCurrentPage(currentPage);
        List<TeamRelation> teamRelationList =query(team, student, mentor);
        pageBean.setTotalSize(teamRelationList.size());
        result=query(team, student, mentor);
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

}
