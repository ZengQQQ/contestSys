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

    public PageBean<Administrator> query(Integer currentPage, Administrator ele) {
        return administratorDao.queryByPage(currentPage, ele);
    }
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
        ele.setCo_id(null);
        if(ele.getCo_status()==0&&con.getCo_status()!=0){
            List<User> users = userDao.leftQuery(BaseDao.formList(con,new User()),-1,-1);
            for(User user:users){
                User user1 = new User(user);
                user1.setU_status(0);
                update(user1,user);
            }
        }
        return collegeDao.update(ele, con);
    }

    public int update(Competition ele, Competition con) {
        ele.setC_id(null);
        return competitionDao.update(ele, con);
    }

    public int update(Mentor ele, Mentor con) {
        ele.setM_id(null);
        if(ele.getM_status()==0&&con.getM_status()!=0){
            List<User> users = userDao.leftQuery(BaseDao.formList(con,new User()),-1,-1);
            for(User user:users){
                User user1 = new User(user);
                user1.setU_status(0);
                update(user1,user);
            }
        }
        return mentorDao.update(ele, con);
    }

    public int update(Project ele, Project con) {
        ele.setP_id(null);
        boolean value =false;
        if (ele.getM_id()!=null) {
            Mentor mentor = new Mentor();
            mentor.setM_id(ele.getM_id());
            List<Mentor> mentors = mentorDao.query(mentor,-1,-1);
            if(!mentors.isEmpty()){
                value=true;
            }
        }else {
            value =true;
        }
        if(value){
            if(ele.getP_status()==0&&con.getP_status()!=0){
                List<Work> works = workDao.leftQuery(BaseDao.formList(con,new Work()),-1,-1);
                for(Work u:works){
                    Work u1 = new Work(u);
                    u1.setW_status(0);
                    update(u1,u);
                }
            }
            return projectDao.update(ele, con);
        }else {
            return 0;
        }
    }

    public int update(Student ele, Student con) {
        ele.setS_id(null);
        if(ele.getS_status()==0&&con.getS_status()!=0){
            List<User> users = userDao.leftQuery(BaseDao.formList(con,new User()),-1,-1);
            for(User user:users){
                User user1 = new User(user);
                user1.setU_status(0);
                update(user1,user);
            }
        }
        return studentDao.update(ele, con);
    }

    public int update(Task ele, Task con) {
        ele.setTk_id(null);
        boolean value =false;
        if (ele.getU_id()!=null) {
            User user = new User();
            user.setU_id(ele.getU_id());
            List<User> users = userDao.query(user,-1,-1);
            if(!users.isEmpty()){
                value = true;
            }
        }else {
            value=true;
        }
        if(value){
            if(ele.getTk_status()==0&&con.getTk_status()!=0){
                List<Work> works = workDao.leftQuery(BaseDao.formList(con,new Work()),-1,-1);
                for(Work u:works){
                    Work u1 = new Work(u);
                    u1.setW_status(0);
                    update(u1,u);
                }
            }
            return taskDao.update(ele, con);
        }else {
            return 0;
        }
    }

    public int update(TeamApplication ele, TeamApplication con) {
        boolean value = false;
        boolean value1 = false;
        if (ele.getT_id()!=null) {
            Team team = new Team();
            team.setT_id(ele.getT_id());
            List<Team> teams= teamDao.query(team,-1,-1);
            if (!teams.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getU_id()!=null) {
            User user = new User();
            user.setU_id(ele.getU_id());
            List<User> users = userDao.query(user,-1,-1);
            if(!users.isEmpty()){
                value1=true;
            }
        }else {
            value1=true;
        }
        if(value1&&value&&(ele.getT_id()!=null||ele.getU_id()!=null)){
            return teamApplicationDao.update(ele, con);
        }else {
            return 0;
        }

    }

    public int update(Team ele, Team con) {
        ele.setT_id(null);
        boolean value = false;
        if (ele.getCaptain_id()!=null) {
            User user = new User();
            user.setU_id(ele.getCaptain_id());
            List<User> users = userDao.query(user,-1,-1);
            if(!users.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }
        if (value){
            if(ele.getT_status()==0&&con.getT_status()!=0){
                List<TeamApplication> teamApplications = teamApplicationDao.leftQuery(BaseDao.formList(con,new TeamApplication()),-1,-1);
                List<TeamInvite> teamInvites = teamInviteDao.leftQuery(BaseDao.formList(con,new TeamInvite()),-1,-1);
                List<WorkApplication> workApplications =workApplicationDao.leftQuery(BaseDao.formList(con,new WorkApplication()),-1,-1);
                for(TeamApplication u:teamApplications){
                    TeamApplication u1 = new TeamApplication(u);
                    u1.setTa_status(99);
                    update(u1,u);
                }
                for(TeamInvite u:teamInvites){
                    TeamInvite u1 = new TeamInvite(u);
                    u1.setTi_status(99);
                    update(u1,u);
                }
                for(WorkApplication u:workApplications){
                    WorkApplication u1 = new WorkApplication(u);
                    u1.setWa_status(99);
                    update(u1,u);
                }
            }
            return teamDao.update(ele, con);
        }
        return 0;
    }

    public int update(TeamInvite ele, TeamInvite con) {
        boolean value = false;
        boolean value1 = false;

        if (ele.getT_id()!=null) {
            Team team = new Team();
            team.setT_id(ele.getT_id());
            List<Team> teams= teamDao.query(team,-1,-1);
            if (!teams.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getU_id()!=null) {
            User user = new User();
            user.setU_id(ele.getU_id());
            List<User> users = userDao.query(user,-1,-1);
            if(!users.isEmpty()){
                value1=true;
            }
        }else {
            value1=true;
        }
        if(value1&&value&&(ele.getT_id()!=null||ele.getU_id()!=null)){
            return teamInviteDao.update(ele, con);
        }else {
            return 0;
        }

    }

    public int update(TeamMentor ele, TeamMentor con) {

        boolean value = false;
        boolean value1 = false;

        if (ele.getT_id()!=null) {
            Team team = new Team();
            team.setT_id(ele.getT_id());
            List<Team> teams= teamDao.query(team,-1,-1);
            if (!teams.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getM_id()!=null) {
            Mentor mentor =new Mentor();
            mentor.setM_id(ele.getM_id());
            List<Mentor> mentors = mentorDao.query(mentor,-1,-1);
            if(!mentors.isEmpty()){
                value1=true;
            }
        }else {
            value1=true;
        }
        if(value1&&value&&(ele.getT_id()!=null||ele.getM_id()!=null)){
            return teamMentorDao.update(ele, con);
        }else {
            return 0;
        }
    }

    public int update(TeamUser ele, TeamUser con) {
        boolean value = false;
        boolean value1 = false;

        if (ele.getT_id()!=null) {
            Team team = new Team();
            team.setT_id(ele.getT_id());
            List<Team> teams= teamDao.query(team,-1,-1);
            if (!teams.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getU_id()!=null) {
            User user = new User();
            user.setU_id(ele.getU_id());
            List<User> users = userDao.query(user,-1,-1);
            if(!users.isEmpty()){
                value1=true;
            }
        }else {
            value1=true;
        }
        if(value1&&value&&(ele.getT_id()!=null||ele.getU_id()!=null)){
            return teamUserDao.update(ele, con);
        }else {
            return 0;
        }

    }

    public int update(User ele, User con) {
        ele.setS_id(null);
        boolean value = false;
        boolean value1 = false;
        boolean value2 =false;

        if (ele.getS_id()!=null) {
            Student student = new Student();
            student.setS_id(ele.getS_id());
            List<Student> students = studentDao.query(student,-1,-1);
            if (!students.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getM_id()!=null) {
            Mentor mentor = new Mentor();
            mentor.setM_id(ele.getM_id());
            List<Mentor> mentors = mentorDao.query(mentor,-1,-1);
            if(!mentors.isEmpty()&&ele.getS_id()==null){
                value1=true;
            }
        }else {
            value1=true;
        }
        if (ele.getCo_id()!=null) {
            College college = new College();
            college.setCo_id(ele.getCo_id());
            List<College> colleges = collegeDao.query(college,-1,-1);
            if(!colleges.isEmpty()&&ele.getS_id()==null&&ele.getM_id()==null){
                value2=true;
            }
        }else {
            value2=true;
        }
        if(value1&&value&&value2&&(ele.getS_id()!=null||ele.getM_id()!=null||ele.getCo_id()!=null)){
            if(ele.getU_status()==0&&con.getU_status()!=0){
                List<TeamApplication> teamApplications = teamApplicationDao.leftQuery(BaseDao.formList(con,new TeamApplication()),-1,-1);
                List<TeamInvite> teamInvites = teamInviteDao.leftQuery(BaseDao.formList(con,new TeamInvite()),-1,-1);
                for(TeamApplication u:teamApplications){
                    TeamApplication u1 = new TeamApplication(u);
                    u1.setTa_status(99);
                    update(u1,u);
                }
                for(TeamInvite u:teamInvites){
                    TeamInvite u1 = new TeamInvite(u);
                    u1.setTi_status(99);
                    update(u1,u);
                }
            }
            return userDao.update(ele,con);
        }else {
            return 0;
        }
    }

    public int update(Work ele, Work con) {
        boolean value = false;
        boolean value1 = false;
        boolean value2 =false;

        if (ele.getC_id()!=null) {
            Competition competition = new Competition();
            competition.setC_id(ele.getC_id());
            List<Competition> competitions = competitionDao.query(competition,-1,-1);
            if (!competitions.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getP_id()!=null) {
            Project project = new Project();
            project.setP_id(ele.getP_id());
            List<Project> projects = projectDao.query(project,-1,-1);
            if(!projects.isEmpty()&&ele.getC_id()==null){
                value1=true;
            }
        }else {
            value1=true;
        }
        if (ele.getTk_id()!=null) {
            Task task = new Task();
            task.setTk_id(ele.getTk_id());
            List<Task> tasks = taskDao.query(task,-1,-1);
            if(!tasks.isEmpty()&&ele.getP_id()==null&&ele.getC_id()==null){
                value2=true;
            }
        }else {
            value2=true;
        }
        if(value1&&value&&value2&&(ele.getP_id()!=null||ele.getW_id()!=null||ele.getTk_id()!=null)){
            if(ele.getW_status()==0&&con.getW_status()!=0){
                List<WorkApplication> workApplications =workApplicationDao.leftQuery(BaseDao.formList(con,new WorkApplication()),-1,-1);
                for(WorkApplication u:workApplications){
                    WorkApplication u1 = new WorkApplication(u);
                    u1.setWa_status(99);
                    update(u1,u);
                }
            }
            return workDao.update(ele,con);
        }else {
            return 0;
        }
    }
    public int update(WorkApplication ele,WorkApplication con){
        boolean value = false;
        boolean value1 = false;

        if (ele.getT_id()!=null) {
            Team team = new Team();
            team.setT_id(ele.getT_id());
            List<Team> teams= teamDao.query(team,-1,-1);
            if (!teams.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getW_id()!=null) {
            Work work = new Work();
            work.setW_id(ele.getW_id());
            List<Work> works = workDao.query(work,-1,-1);
            if(!works.isEmpty()){
                value1=true;
            }
        }else {
            value1=true;
        }
        if(value1&&value&&(ele.getT_id()!=null||ele.getW_id()!=null)){
            return workApplicationDao.update(ele,con);
        }else {
            return 0;
        }
    }
    public int update(TeamWork ele,TeamWork con){
        boolean value = false;
        boolean value1 = false;

        if (ele.getT_id()!=null) {
            Team team = new Team();
            team.setT_id(ele.getT_id());
            List<Team> teams= teamDao.query(team,-1,-1);
            if (!teams.isEmpty()){
                value=true;
            }
        }else {
            value = true;
        }

        if (ele.getW_id()!=null) {
            Work work = new Work();
            work.setW_id(ele.getW_id());
            List<Work> works = workDao.query(work,-1,-1);
            if(!works.isEmpty()){
                value1=true;
            }
        }else {
            value1=true;
        }
        if(value1&&value&&(ele.getT_id()!=null||ele.getW_id()!=null)){
            return teamWorkDao.update(ele,con);
        }else {
            return 0;
        }
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
            delete(u);
        }
        return collegeDao.delete(ele);
    }

    public int delete(Competition ele) {
        List<Work> works = workDao.leftQuery(BaseDao.formList(ele, new Work()), -1, -1);
        for (Work work : works) {
            delete(work);
        }
        return competitionDao.delete(ele);
    }

    public int delete(Mentor ele) {
        List<User> users = userDao.leftQuery(BaseDao.formList(ele, new User()), -1, -1);
        for (User u : users) {
            delete(u);
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
            delete(u);
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


}
