package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.Project;

import java.util.ArrayList;
import java.util.List;


/**
 * ProjectDao
 * 项目数据访问类
 */
public class ProjectDao extends BaseDao<Project> {

    public ProjectDao() {
        super("project");
    }
    private Project model;


    /**
     * 添加项目
     */

    public boolean insert(Project project) {
        boolean inserted = super.insert(project);
        if (!inserted) {
            System.out.println("添加项目失败");
            return false;
        } else {
            System.out.println("添加项目成功");
            return true;
        }
    }

    /**
     * 删除项目
     */
    public int delete(Project project) {

        int deleted = super.delete(project);
        if (deleted == 0) {
            System.out.println("删除项目失败");
            return deleted;
        } else {
            System.out.println("删除项目成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新项目
     *
     * @param competition          更新内容
     * @param conditionCompetition 更新条件
     * @return 更新的条数
     */
    public int update(Project competition, Project conditionCompetition) {
        Project competition1 =new Project(competition);
        competition1.setP_id(null);
        // 更新条件

        int updated = super.update(competition, conditionCompetition);
        if (updated == 0) {
            System.out.println("更新项目失败");
            return 0;
        } else {
            System.out.println("更新项目成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询项目
     */
    public List<Project> query(Project competition, int current, int size) {
        // 查询结果
        List<Project> competitionList = null;
        competitionList = super.query(competition, current, size);
        if (competitionList.isEmpty()) {
            System.out.println("查询项目失败");
            return competitionList;
        } else {
            System.out.println("查询项目成功！");
            for (Project c : competitionList) {
                System.out.println(c);
            }
            return competitionList;
        }
    }



    private PageBean<Project> pageBean = new PageBean<Project>();


    public void initPage( Project object){
        this.model=object;
        List<Project> total =new ArrayList<>();
        total=query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<Project> queryByPage(Integer currentPage, Project object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<Project> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }

    public static void main(String[] args) {
        ProjectDao competitionDao = new ProjectDao();
        Project competition = new Project();


        // 添加项目
//        boolean instert = competitionDao.instert(competition);
//        System.out.println(instert);

//         查询项目
//        List<Project> competitionList = competitionDao.select(competition, 0, 10);
//        for (Project c : competitionList) {
//            System.out.println(c.toString());
//        }
//
//        // 更新项目
//        competition.setC_name("项目2");
//        Project conditionCompetition = new Project();
//        conditionCompetition.setC_id(1);
//        int updated = competitionDao.update(competition, conditionCompetition);
//        System.out.println(updated);
//
//        // 删除项目
        int delete = competitionDao.delete(competition);
        System.out.println(delete);
    }


}
