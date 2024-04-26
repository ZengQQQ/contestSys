package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.*;
import com.game.dao.base.BaseDao;
import com.game.domain.*;
import com.game.domain.Team;
import com.game.domain.secondary.teamDomain.TeamMentor;
import com.game.domain.secondary.teamDomain.TeamUser;
import com.game.domain.secondary.teamDomain.TeamWork;
import com.game.domain.secondary.teamDomain.WorkApplication;
import com.game.domain.secondary.teamMessageDomain.TeamApplication;
import com.game.domain.secondary.teamMessageDomain.TeamInvite;
import com.game.domain.secondary.userDomain.College;
import com.game.domain.secondary.userDomain.Mentor;
import com.game.domain.secondary.userDomain.Student;
import com.game.domain.secondary.workDomain.Competition;
import com.game.domain.secondary.workDomain.Project;
import com.game.domain.secondary.workDomain.Task;

import java.util.List;


public class AdministratorServe {

    AdministratorDao administratorDao = new AdministratorDao();
    CollegeDao collegeDao = new CollegeDao();
    CompetitionDao competitionDao = new CompetitionDao();
    MentorDao mentorDao = new MentorDao();
    ProjectDao projectDao = new ProjectDao();
    StudentDao studentDao = new StudentDao();
    TaskDao taskDao = new TaskDao();
    TeamApplicationDao teamApplicationDao = new TeamApplicationDao();
    TeamDao teamDao = new TeamDao();
    TeamInviteDao teamInviteDao = new TeamInviteDao();
    TeamMentorDao teamMentorDao = new TeamMentorDao();
    TeamUserDao teamUserDao = new TeamUserDao();
    UserDao userDao = new UserDao();
    WorkDao workDao = new WorkDao();
    WorkApplicationDao workApplicationDao = new WorkApplicationDao();
    TeamWorkDao teamWorkDao = new TeamWorkDao();

    public PageBean<College> query(Integer currentPage, College ele) {
        return collegeDao.queryByPage(currentPage, ele);
    }

    public PageBean<Competition> query(Integer currentPage, Competition ele) {
        return competitionDao.queryByPage(currentPage, ele);
    }

    public PageBean<Mentor> query(Integer currentPage, Mentor ele) {
        return mentorDao.queryByPage(currentPage, ele);
    }

    public PageBean<Project> query(Integer currentPage, Project ele) {
        return projectDao.queryByPage(currentPage, ele);
    }

    public PageBean<Student> query(Integer currentPage, Student ele) {
        return studentDao.queryByPage(currentPage, ele);
    }

    public PageBean<Task> query(Integer currentPage, Task ele) {
        return taskDao.queryByPage(currentPage, ele);
    }

    public PageBean<TeamApplication> query(Integer currentPage, TeamApplication ele) {
        return teamApplicationDao.queryByPage(currentPage, ele);
    }

    public PageBean<Team> query(Integer currentPage, Team ele) {
        return teamDao.queryByPage(currentPage, ele);
    }

    public PageBean<TeamInvite> query(Integer currentPage, TeamInvite ele) {
        return teamInviteDao.queryByPage(currentPage, ele);
    }

    public PageBean<User> query(Integer currentPage, User ele) {
        return userDao.queryByPage(currentPage, ele);
    }

    public PageBean<Work> query(Integer currentPage, Work ele) {
        return workDao.queryByPage(currentPage, ele);
    }
    public PageBean<WorkApplication> query(Integer currentPage, WorkApplication ele) {
        return workApplicationDao.queryByPage(currentPage, ele);
    }
    public PageBean<TeamWork> query(Integer currentPage, TeamWork ele) {
        return teamWorkDao.queryByPage(currentPage, ele);
    }

    public int update(College ele, College con) {
        return collegeDao.update(ele, con);
    }

    public int update(Competition ele, Competition con) {
        return competitionDao.update(ele, con);
    }

    public int update(Mentor ele, Mentor con) {
        return mentorDao.update(ele, con);
    }

    public int update(Project ele, Project con) {
        return projectDao.update(ele, con);
    }

    public int update(Student ele, Student con) {
        return studentDao.update(ele, con);
    }

    public int update(Task ele, Task con) {
        return taskDao.update(ele, con);
    }

    public int update(TeamApplication ele, TeamApplication con) {
        return teamApplicationDao.update(ele, con);
    }

    public int update(Team ele, Team con) {
        return teamDao.update(ele, con);
    }

    public int update(TeamInvite ele, TeamInvite con) {
        return teamInviteDao.update(ele, con);
    }

    public int update(TeamMentor ele, TeamMentor con) {
        return teamMentorDao.update(ele, con);
    }

    public int update(TeamUser ele, TeamUser con) {
        return teamUserDao.update(ele, con);
    }

    public int update(User ele, User con) {
        return userDao.update(ele, con);
    }

    public int update(Work ele, Work con) {
        return workDao.update(ele, con);
    }
    public int update(WorkApplication ele,WorkApplication con){
        if (ele.getW_id()!=null) {
            List<Work> works = workDao.leftQuery(BaseDao.formList(ele, new Work()), -1, -1);
            if(!works.isEmpty()){
                if (ele.getT_id()!=null) {
                    List<Team> teams= teamDao.leftQuery(BaseDao.formList(ele,new Team()),-1,-1);
                    if (!teams.isEmpty()){
                        return workApplicationDao.update(ele,con);
                    }
                }else {
                    return workApplicationDao.update(ele,con);
                }
            }
        } else {
            if (ele.getT_id()!=null) {
                List<Team> teams= teamDao.leftQuery(BaseDao.formList(ele,new Team()),-1,-1);
                if (!teams.isEmpty()){
                    return workApplicationDao.update(ele,con);
                }
            }
        }
        return 0;
    }
    public int update(TeamWork ele,TeamWork con){
        if (ele.getW_id()!=null) {
            List<Work> works = workDao.leftQuery(BaseDao.formList(ele, new Work()), -1, -1);
            if(!works.isEmpty()){
                if (ele.getT_id()!=null) {
                    List<Team> teams= teamDao.leftQuery(BaseDao.formList(ele,new Team()),-1,-1);
                    if (!teams.isEmpty()){
                        return teamWorkDao.update(ele,con);
                    }
                }else {
                    return teamWorkDao.update(ele,con);
                }
            }
        } else {
            if (ele.getT_id()!=null) {
                List<Team> teams= teamDao.leftQuery(BaseDao.formList(ele,new Team()),-1,-1);
                if (!teams.isEmpty()){
                    return teamWorkDao.update(ele,con);
                }
            }
        }
        return 0;
    }

    public boolean insert(College ele) {
        return collegeDao.insert(ele);
    }

    public boolean insert(Competition ele) {
        return competitionDao.insert(ele);
    }

    public boolean insert(Mentor ele) {
        return mentorDao.insert(ele);
    }

    public boolean insert(Project ele) {
        if(ele.getM_id()!=null){
            Mentor mentor = new Mentor();
            mentor.setM_id(ele.getM_id());
            if(!mentorDao.query(mentor,-1,-1).isEmpty()){
                if(ele.getC_id()==null){
                    return projectDao.insert(ele);
                }else {
                    Competition competition =new Competition();
                    competition.setC_id(ele.getC_id());
                    if (!competitionDao.query(competition,-1,-1).isEmpty()){
                        return projectDao.insert(ele);
                    }
                }
            }
        }
        return false;
    }

    public boolean insert(Student ele) {
        return studentDao.insert(ele);
    }

    public boolean insert(Task ele) {
        if (ele.getU_id() != null) {
            User user =new User();
            user.setU_id(ele.getU_id());
            if (!userDao.query(user, -1, -1).isEmpty()) {
                return taskDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(TeamApplication ele) {
        if (ele.getU_id() != null && ele.getT_id() != null) {
            Team team = new Team();
            User user = new User();
            team.setT_id(ele.getT_id());
            user.setU_id(ele.getU_id());
            if (!userDao.query(user, -1, -1).isEmpty() && !teamDao.query(team, -1, -1).isEmpty()) {
                return teamApplicationDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(Team ele) {
        if (ele.getCaptain_id() != null) {
            User user = new User();
            user.setU_id(ele.getCaptain_id());
            if (!userDao.query(user, -1, -1).isEmpty()) {
                return teamDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(TeamInvite ele) {
        if (ele.getU_id() != null && ele.getT_id() != null) {
            Team team = new Team();
            User user = new User();
            team.setT_id(ele.getT_id());
            user.setU_id(ele.getU_id());
            if (!userDao.query(user, -1, -1).isEmpty() && !teamDao.query(team, -1, -1).isEmpty()) {
                return teamInviteDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(TeamMentor ele) {
        if (ele.getT_id() != null && ele.getM_id() != null) {
            Mentor mentor = new Mentor();
            Team team = new Team();
            mentor.setM_id(ele.getM_id());
            team.setT_id(ele.getT_id());
            if (!mentorDao.query(mentor, -1, -1).isEmpty() && !teamDao.query(team, -1, -1).isEmpty()) {
                return teamMentorDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(TeamUser ele) {
        if (ele.getT_id() != null && ele.getU_id() != null) {
            User user = new User();
            Team team = new Team();
            user.setU_id(ele.getU_id());
            team.setT_id(ele.getT_id());
            if (!userDao.query(user, -1, -1).isEmpty() && !teamDao.query(team, -1, -1).isEmpty()) {
                return teamUserDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(User ele) {
        if (ele.getS_id() != null) {
            Student student = new Student();
            student.setS_id(ele.getS_id());
            if (!studentDao.query(student, -1, -1).isEmpty()) {
                return userDao.insert(ele);
            }
        } else if (ele.getM_id() != null) {
            Mentor mentor = new Mentor();
            mentor.setM_id(ele.getM_id());
            if (!mentorDao.query(mentor, -1, -1).isEmpty()) {
                return userDao.insert(ele);
            }
        } else if (ele.getCo_id() != null) {
            College college = new College();
            college.setCo_id(ele.getCo_id());
            if (!collegeDao.query(college, -1, -1).isEmpty()) {
                return userDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(Work ele) {
        if (ele.getC_id() != null) {
            Competition competition = new Competition();
            competition.setC_id(ele.getC_id());
            if (!competitionDao.query(competition, -1, -1).isEmpty()) {
                return workDao.insert(ele);
            }
        } else if (ele.getP_id() != null) {
            Project project = new Project();
            project.setP_id(ele.getP_id());
            if (!projectDao.query(project, -1, -1).isEmpty()) {
                return workDao.insert(ele);
            }
        } else if (ele.getTk_id() != null) {
            Task task = new Task();
            task.setTk_id(ele.getTk_id());
            if (!taskDao.query(task, -1, -1).isEmpty()) {
                return workDao.insert(ele);
            }
        }
        return false;
    }

    public boolean insert(WorkApplication ele){
        if (ele.getW_id() != null && ele.getT_id() != null) {
            Team team = new Team();
            Work work = new Work();
            team.setT_id(ele.getT_id());
            work.setW_id(ele.getW_id());
            if (!workDao.query(work, -1, -1).isEmpty() && !teamDao.query(team, -1, -1).isEmpty()) {
                return workApplicationDao.insert(ele);
            }
        }
        return false;
    }
    public boolean insert(TeamWork ele){
        return teamWorkDao.insert(ele);
    }

    public int delete(College ele) {
        List<User> users = userDao.leftQuery(BaseDao.formList(ele, new User()), -1, -1);
        for (User u : users) {
            userDao.delete(u);
        }
        return collegeDao.delete(ele);
    }

    public int delete(Competition ele) {
        List<Work> works = workDao.leftQuery(BaseDao.formList(ele, new Work()), -1, -1);
        for (Work work : works) {
            workDao.delete(work);
        }
        return competitionDao.delete(ele);
    }

    public int delete(Mentor ele) {
        List<User> users = userDao.leftQuery(BaseDao.formList(ele, new User()), -1, -1);
        for (User u : users) {
            userDao.delete(u);
        }
        return mentorDao.delete(ele);
    }

    public int delete(Project ele) {
        List<Work> works = workDao.leftQuery(BaseDao.formList(ele, new Work()), -1, -1);
        for (Work work : works) {
            workDao.delete(work);
        }
        return projectDao.delete(ele);
    }

    public int delete(Student ele) {
        List<User> users = userDao.leftQuery(BaseDao.formList(ele, new User()), -1, -1);
        for (User u : users) {
            userDao.delete(u);
        }
        return studentDao.delete(ele);
    }

    public int delete(Task ele) {
        List<Work> works = workDao.leftQuery(BaseDao.formList(ele, new Work()), -1, -1);
        for (Work work : works) {
            delete(work);
        }
        return taskDao.delete(ele);
    }

    public int delete(TeamApplication ele) {
        return teamApplicationDao.delete(ele);
    }

    public int delete(Team ele) {
        List<TeamUser> userList = teamUserDao.leftQuery(BaseDao.formList(ele, new TeamUser()), -1, -1);
        List<TeamMentor> mentorList = teamMentorDao.leftQuery(BaseDao.formList(ele, new TeamMentor()), -1, -1);
        List<TeamApplication> teamApplications= teamApplicationDao.leftQuery(BaseDao.formList(ele,new TeamApplication()),-1,-1);
        List<TeamInvite> teamInvites= teamInviteDao.leftQuery(BaseDao.formList(ele,new TeamInvite()),-1,-1);
        List<TeamWork> teamWorks = teamWorkDao.leftQuery(BaseDao.formList(ele,new TeamWork()),-1,-1);
        for (TeamWork t :teamWorks){
            delete(t);
        }
        for (TeamApplication t:teamApplications){
            TeamApplication t1=new TeamApplication(t);
            t1.setTa_status(99);
            update(t1,t);
        }
        for (TeamInvite t:teamInvites){
            TeamInvite t1=new TeamInvite(t);
            t1.setTi_status(99);
            update(t1,t);
        }
        for (TeamUser teamUser : userList) {
            delete(teamUser);
        }
        for (TeamMentor teamMentor : mentorList) {
            delete(teamMentor);
        }
        return teamDao.delete(ele);
    }

    public int delete(TeamInvite ele) {
        return teamInviteDao.delete(ele);
    }

    public int delete(TeamMentor ele) {
        return teamMentorDao.delete(ele);
    }

    public int delete(TeamUser ele) {
        return teamUserDao.delete(ele);
    }

    public int delete(User ele) {
        Task task =new Task();
        TeamUser teamUser = new TeamUser();
        TeamInvite teamInvite = new TeamInvite();
        TeamApplication teamApplication = new TeamApplication();
        Team team = new Team();
        team.setCaptain_id(ele.getU_id());
        List<Task> tasks=taskDao.leftQuery(BaseDao.formList(ele,task),-1,-1);
        List<TeamUser> teamUsers = teamUserDao.leftQuery(BaseDao.formList(ele,teamUser),-1,-1);
        List<TeamInvite> teamInvites = teamInviteDao.leftQuery(BaseDao.formList(ele,teamInvite),-1,-1);
        List<TeamApplication> teamApplications = teamApplicationDao.leftQuery(BaseDao.formList(ele,teamApplication),-1,-1);
        List<Team> teams = teamDao.query(team,-1,-1);
        for (Task t :tasks){
            delete(t);
        }
        for (TeamUser t:teamUsers){
            delete(t);
        }
        for (TeamInvite t:teamInvites){
            TeamInvite t1 = new TeamInvite(t);
            t1.setTi_status(99);
            update(t1,t);
        }
        for (TeamApplication t:teamApplications){
            TeamApplication t1 = new TeamApplication(t);
            t1.setTa_status(99);
            update(t1,t);
        }
        for (Team t : teams){
            delete(t);
        }
        return userDao.delete(ele);
    }

    public int delete(Work ele) {
        WorkApplication workApplication = new WorkApplication();
        TeamWork teamWork = new TeamWork();
        workApplication.setW_id(ele.getW_id());
        teamWork.setW_id(ele.getW_id());
        List<WorkApplication> workApplications = workApplicationDao.query(workApplication,-1,-1);
        List<TeamWork> teamWorks = teamWorkDao.query(teamWork,-1,-1);
        for(WorkApplication w:workApplications){
            WorkApplication w1 = new WorkApplication(w);
            w1.setWa_status(99);
            update(w1,w);
        }
        for (TeamWork t :teamWorks){
            delete(t);
        }
        return workDao.delete(ele);
    }
    public int delete(WorkApplication ele){
        return workApplicationDao.delete(ele);
    }

    public int delete(TeamWork ele){
        return teamWorkDao.delete(ele);
    }


    public int banStudentUser(Student stu) {
        User user = new User();
        int count = 0;
        List<User> userList = userDao.leftQuery(BaseDao.formList(user, stu), -1, -1);
        for (User ele : userList) {
            if (ele.getU_status() == 0) {
                User user1 = new User();
                user1.setU_status(0);
                count++;
            } else {
                System.out.println("该账号处于封禁状态");
                count++;
            }
        }
        return count;
    }

    //解封学生账号
    public int unBanStudentUser(Student stu) {
        User user = new User();
        int count = 0;
        List<User> userList = userDao.leftQuery(BaseDao.formList(user, stu), -1, -1);
        for (User ele : userList) {
            if (ele.getU_status() != 0) {
                User user1 = new User();
                user1.setU_status(1);
                count++;
            } else {
                System.out.println("该账号处于正常状态");
                count++;
            }
        }
        return count;
    }

    //添加比赛信息


    //删除帖子
    public boolean deletePostings() {
        return true;
    }


}
