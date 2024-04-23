package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.*;
import com.game.dao.base.BaseDao;
import com.game.domain.Mentor;
import com.game.domain.Student;
import com.game.domain.TeamGather;
import com.game.domain.secondaryDao.Team;
import com.game.domain.secondaryDao.TeamMentor;
import com.game.domain.secondaryDao.TeamStudent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamGatherServe extends BaseDao<TeamGather> {
    private final PageBean<TeamGather> pageBean = new PageBean<TeamGather>();
    public List<TeamGather> query(Team team, Student student, Mentor mentor){
        List<TeamGather> teamRelationsList=new ArrayList<>();
        TeamDao teamDao = new TeamDao();
        StudentDao studentDao = new StudentDao();
        MentorDao mentorDao = new MentorDao();
        TeamMentor teamMentor = new TeamMentor();
        TeamStudent teamStudent = new TeamStudent();
        List<Object> objectList = new ArrayList<>();
        objectList.add(student);
        objectList.add(teamStudent);
        objectList.add(teamMentor);
        objectList.add(mentor);
        objectList.add(team);
        List<Team> teamList=teamDao.leftQuery(objectList,-1,-1);
        removeDuplicates(teamList);
        for (Team team1:teamList){
            try {
                objectList.remove(objectList.size());
                objectList.add(team1);
                List<Student> studentList = studentDao.leftQuery(objectList,-1,-1);
                List<Mentor> mentorList = mentorDao.leftQuery(objectList,-1,-1);
                removeDuplicates(studentList);
                removeDuplicates(mentorList);
                TeamGather teamRelation = new TeamGather(team1,mentorList,studentList);
                teamRelationsList.add(teamRelation);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return teamRelationsList;
    }
    public PageBean<TeamGather> queryByPage(Integer currentPage, Team team, Student student, Mentor mentor){
        List<TeamGather> result = null;
        pageBean.setCurrentPage(currentPage);
        List<TeamGather> teamGatherList =query(team, student, mentor);
        pageBean.setTotalSize(teamGatherList.size());
        result=query(team, student, mentor);
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

}
