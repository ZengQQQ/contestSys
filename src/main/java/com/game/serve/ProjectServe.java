package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.domain.secondary.workDomain.Project;

import java.util.List;

public class ProjectServe extends ProjectDao {

    public boolean addProjectInfo(List<Project> Projects) {
        boolean success = true; // 初始化一个标志以跟踪整体成功情况
        for (Project Project : Projects) {
            try {
                // 尝试添加竞赛信息
                insert(Project);
            } catch (Exception e) {
                // 记录错误并继续处理其他竞赛
                // 你可能想要记录具体失败的竞赛
                System.err.println("添加竞赛信息时出错：" + e.getMessage());
                success = false; // 如果发生任何错误，则将成功标志设置为false
            }
        }
        return success; // 返回整体成功标志
    }
}
