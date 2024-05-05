package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.domain.Project;
import com.game.utils.Result;

import java.util.List;

public class ProjectService {

    private static final ProjectDao projectDao = new ProjectDao();


    public Result<String> insert(Project project) {

        // 参数检查

        // 查询重复
        List<Project> exited = projectDao.query(project,-1,-1);
        if (!exited.isEmpty()){

            Result<String> fail = Result.fail("已存在","");
            return fail;
        }
        // 添加
        boolean inserted = projectDao.insert(project);
        if (inserted){
            Result<String> success = Result.success("");
            return success;
        }

        // 其它问题
        return Result.fail("添加失败，稍后重试","");

    }




}
