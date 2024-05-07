package com.game.serve;

import com.game.dao.ProjectDao;
import com.game.dao.StallMentorMessageDao;
import com.game.dao.StallProjectMessageDao;
import com.game.dao.StallTeamMessageDao;
import com.game.domain.Project;
import com.game.domain.StallMentorMessage;
import com.game.domain.StallProjectMessage;
import com.game.domain.StallTeamMessage;
import com.game.utils.Result;

import java.util.List;

public class ProjectService {

    private static final ProjectDao projectDao = new ProjectDao();
    private static final StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();
    private static final StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    private static final StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();

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
