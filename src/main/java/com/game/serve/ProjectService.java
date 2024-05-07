package com.game.serve;

import com.game.dao.*;
import com.game.domain.*;
import com.game.domain.fixDomain.ProjectFix;
import com.game.utils.Result;

import java.util.List;

public class ProjectService {

    private static final ProjectDao projectDao = new ProjectDao();
    private static final StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();
    private static final StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    private static final StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    private static final StallDao stallDao = new StallDao();

    public Result<String> insert(ProjectFix projectFix) {
        // 将ProjectFix 转换为 Project
        Project project = new Project();
        project.setP_id(projectFix.getP_id());
        project.setP_name(projectFix.getP_name());
        project.setP_info(projectFix.getP_info());
        project.setP_level(projectFix.getP_level());
        project.setP_st(projectFix.getP_st());
        project.setP_ddl(projectFix.getP_ddl());
        project.setP_url(projectFix.getP_url());
        project.setP_img(projectFix.getP_img());
        project.setP_cc(projectFix.getP_cc());
        project.setP_maxtime(projectFix.getP_maxtime());
        project.setP_resagree(projectFix.getP_resagree());
        project.setU_acc(projectFix.getEditor().getU_acc());
        project.setP_status(projectFix.getP_status());


        List<Project> exited = projectDao.query(project,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("添加失败","项目已存在");
        }
        boolean inserted = projectDao.insert(project);
        if (inserted){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败","更新失败");
    }



    public Result<String> insert(Project project) {
        List<Project> exited = projectDao.query(project,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("添加失败","项目已存在");
        }
        boolean inserted = projectDao.insert(project);
        if (inserted){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败","更新失败");
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
        if (project.getP_status() == 1){
            StallProjectMessage spm = new StallProjectMessage();
            spm.setP_id(project.getP_id());
            spm.setJoin_status(1);
            spm.setSpm_status(0);
            StallMentorMessage stallMentorMessage1 = new StallMentorMessage();
            StallProjectMessage stallProjectMessage1 = new StallProjectMessage();
            Stall stall1 = new Stall();
            List<StallProjectMessage> stallProjectMessages = stallProjectMessageDao.query(spm,-1,-1);
            for (StallProjectMessage stallProjectMessage : stallProjectMessages) {
                stallProjectMessage1 = stallProjectMessage;
                stallProjectMessage.setJoin_status(0);
                int result1 = stallProjectMessageDao.update(stallProjectMessage1,stallProjectMessage);
                if (result1 == 0){
                    return Result.fail("更新失败","");
                }
                StallTeamMessage stm = new StallTeamMessage();
                stm.setSt_id(stallProjectMessage.getSt_id());
                stm.setStm_status(0);
                stm.setJoin_status(1);
                List<StallTeamMessage> stallTeamMessages = stallTeamMessageDao.query(stm,-1,-1);
                if (!stallTeamMessages.isEmpty()) {
                    for (StallTeamMessage stallTeamMessage : stallTeamMessages) {
                        StallTeamMessage stallTeamMessage1 = new StallTeamMessage();
                        stallTeamMessage1 = stallTeamMessage;
                        stallTeamMessage.setJoin_status(0);
                        result1 = stallTeamMessageDao.update(stallTeamMessage1, stallTeamMessage);
                        if (result1 == 0) {
                            return Result.fail("更新失败", "");
                        }
                    }
                }
                StallMentorMessage smm = new StallMentorMessage();
                smm.setSt_id(stallProjectMessage.getSt_id());
                smm.setSmm_status(0);
                smm.setJoin_status(1);
                List<StallMentorMessage> stallMentorMessages = stallMentorMessageDao.query(smm, -1, -1);
                if (!stallMentorMessages.isEmpty()) {
                    for (StallMentorMessage stallMentorMessage : stallMentorMessages) {
                        stallMentorMessage1 = stallMentorMessage;
                        stallMentorMessage.setJoin_status(0);
                        result1 = stallMentorMessageDao.update(stallMentorMessage1, stallMentorMessage);
                        if (result1 == 0) {
                            return Result.fail("更新失败", "");
                        }
                    }
                }
                Stall stall = new Stall();
                stall.setSt_id(stallProjectMessage.getSt_id());
                stall.setSt_status(0);
                List<Stall> stalls = new StallDao().query(stall, -1, -1);
                if (!stalls.isEmpty()) {
                    for (Stall stall2 : stalls) {
                        stall1 = stall2;
                        stall2.setSt_status(1);
                        result1 = stallDao.update(stall2, stall1);
                        if (result1 == 0) {
                            return Result.fail("更新失败", "");
                        }
                    }
                }
            }
            return Result.success("项目关闭成功");
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
