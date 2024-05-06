package com.game.dao.FixDao;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.dao.UserDao;
import com.game.domain.Project;
import com.game.domain.Stall;
import com.game.domain.User;
import com.game.domain.fixDomain.ProjectFix;
import com.game.domain.fixDomain.StallFix;

import java.util.ArrayList;
import java.util.List;

public class ProjectFixDao {
    ProjectDao projectDao = new ProjectDao();
    UserDao userDao =new UserDao();
    private Project model;
    private PageBean<ProjectFix> pageBean = new PageBean<>();

    public void initPage(Project object){
        this.model=object;
        List<Project> total =new ArrayList<>();
        total =projectDao.query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public ProjectFix singToFix(Project ele) {
        User u = new User();
        u.setU_acc(ele.getU_acc());
        User editor = userDao.querySingle(u);
        return new ProjectFix(ele.getP_id(),ele.getP_name(),ele.getP_info(),ele.getP_level(),ele.getP_st(),ele.getP_ddl(),ele.getP_url(),ele.getP_img(),ele.getP_cc(),ele.getP_maxtime(),ele.getP_resagree(),editor,ele.getP_status());
    }
    public PageBean<ProjectFix> queryByPage(Integer currentPage, Project object){
        List<Project> projects = projectDao.query(object,-1,-1);
        pageBean.setTotalSize(projects.size());
        List<Project> temresult = new ArrayList<>();
        List<ProjectFix> result = new ArrayList<>();
        pageBean.setCurrentPage(currentPage);
        temresult=projectDao.query(object,pageBean.getBegin(),pageBean.getEnd());
        for(Project ele :temresult){
            ProjectFix stallFix = singToFix(ele);
            result.add(stallFix);
        }
        pageBean.setListPage(result);
        return pageBean;
    }
}
