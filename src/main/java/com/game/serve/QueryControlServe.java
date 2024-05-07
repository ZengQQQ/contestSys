package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.FixDao.*;
import com.game.dao.MentorDao;
import com.game.dao.StudentDao;
import com.game.dao.UserDao;
import com.game.domain.*;
import com.game.domain.fixDomain.*;
import com.game.utils.Result;

public class QueryControlServe {
    StallFixDao stallFixDao  = new StallFixDao();
    StallMentorMessageFixDao stallMentorMessageFixDao = new StallMentorMessageFixDao();
    StallTeamMessageFixDao stallTeamMessageFixDao = new StallTeamMessageFixDao();
    StallProjectMessageFixDao stallProjectMessageFixDao = new StallProjectMessageFixDao();

    StudentDao studentDao = new StudentDao();

    MentorDao mentorDao = new MentorDao();

    ProjectFixDao projectFixDao = new ProjectFixDao();

    TeamFixDao teamFixDao = new TeamFixDao();
    UserDao userDao =new UserDao();
    TeamMessageFixDao teamMessageFixDao = new TeamMessageFixDao();

    public Result<PageBean<ProjectFix>> queryPage(Integer currentPage, Project stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<ProjectFix> data = projectFixDao.queryByPage(currentPage,stall);
        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<User>> queryPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<User> data = userDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }
    public Result<PageBean<TeamMessageFix>> queryPage(Integer currentPage, TeamUserMessage stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<TeamMessageFix> data = teamMessageFixDao.queryPage(currentPage,stall);
        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }


    public Result<PageBean<TeamMessageFix>> queryMemberTeamPage(Integer currentPage, TeamUserMessage stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<TeamMessageFix> data = teamMessageFixDao.queryPage(currentPage,stall);
        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<TeamMessageFix>> queryTeamMemberPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<TeamMessageFix> data = teamMessageFixDao.queryTeamMemPage(currentPage,stall);
        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }
    public Result<PageBean<TeamFix>> queryPage(Integer currentPage, Team stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<TeamFix> data = teamFixDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<TeamFix>> joinedTeamQuery(Integer currentPage, User stall,TeamUserMessage chain,Team taget){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<TeamFix> data = teamFixDao.queryByPage(currentPage,stall,chain,taget);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallFix>> queryPage(Integer currentPage,Stall stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallFix> data = stallFixDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallFix>> joinedStallQuery(Integer currentPage,User stall,TeamUserMessage chain,Team chain1,StallTeamMessage chain2,Stall target){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallFix> data = stallFixDao.queryByPage(currentPage,stall,chain,chain1,chain2,target);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }
    public Result<PageBean<StallMentorMessageFix>> queryPage(Integer currentPage, StallMentorMessage stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallMentorMessageFix> data = stallMentorMessageFixDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }


    public Result<PageBean<StallTeamMessageFix>> queryPage(Integer currentPage, StallTeamMessage stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallTeamMessageFix> data = stallTeamMessageFixDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallMentorMessageFix>> queryMentorStallPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallMentorMessageFix> data = stallMentorMessageFixDao.queryMentorPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }
    public Result<PageBean<StallMentorMessageFix>> queryStallMenPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallMentorMessageFix> data = stallMentorMessageFixDao.queryStallPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallProjectMessageFix>> queryStallProPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallProjectMessageFix> data = stallProjectMessageFixDao.queryStallPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallProjectMessageFix>> queryProStallPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallProjectMessageFix> data = stallProjectMessageFixDao.queryProjectPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallTeamMessageFix>> queryTeamStallPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallTeamMessageFix> data = stallTeamMessageFixDao.queryTeamPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }


    public Result<PageBean<StallTeamMessageFix>> queryStallTeamPage(Integer currentPage, User stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallTeamMessageFix> data = stallTeamMessageFixDao.queryStallPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<StallProjectMessageFix>> queryPage(Integer currentPage, StallProjectMessage stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<StallProjectMessageFix> data = stallProjectMessageFixDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<Student>> queryPage(Integer currentPage,Student stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<Student> data = studentDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }

    public Result<PageBean<Mentor>> queryPage(Integer currentPage,Mentor stall){
        if(currentPage ==null){
            currentPage=1;
        }
        PageBean<Mentor> data = mentorDao.queryByPage(currentPage,stall);

        if(data==null||data.getListPage()==null){
            return Result.fail("查询失败",data);
        }
        return Result.success(data);
    }





}
