package com.game.serve;

import com.game.dao.ProjectDao;
import com.game.domain.Project;
import com.game.utils.Result;

import java.util.List;

public class ProjectService {

    private static final ProjectDao projectDao = new ProjectDao();

    public Result<String> insert(Project project) {
        List<Project> exited = projectDao.query(project,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("更新失败","项目已存在");
        }
        boolean inserted = projectDao.insert(project);
        if (inserted){
            return Result.success("更新成功");
        }
        return Result.fail("更新失败","更新失败");
    }

    public Result<String> update(Project project) {
        Project tar = new Project();
        tar.setP_id(project.getP_id());
        List<Project> exited = projectDao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该项目");
        }
        int updated = projectDao.update(project,tar);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }

    public Result<String> delete(Project project) {
        List<Project> exited = projectDao.query(project,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("删除失败，没有该项目",null);
        }
        int deleted = projectDao.delete(project);
        if (deleted == 0){
            return Result.fail("删除失败",null);
        }
        return Result.success("删除成功");
    }

}
