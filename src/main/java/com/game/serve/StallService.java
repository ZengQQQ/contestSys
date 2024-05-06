package com.game.serve;

import com.game.dao.*;
import com.game.domain.*;
import com.game.utils.Result;

import java.util.List;

public class StallService {
    private static final StallDao stalldao = new StallDao();
    private static final ProjectDao projectDao = new ProjectDao();
    private static final TeamDao teamDao = new TeamDao();
    private static final StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    private static final StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    private static final StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();

    public Result<String> insert(Team team, Project project) {
        List<Project> projectList = projectDao.query(project, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (projectList.isEmpty() || teamList.isEmpty()) {
            return Result.fail("添加失败", "无目标项目与队伍");
        }
        Stall stall = new Stall();
        stall.setU_acc(team.getU_acc());
        List<Stall> stallList = stalldao.query(stall, -1, -1);
        if (!stallList.isEmpty()) {
            for (Stall s : stallList) {
                StallProjectMessage spm = new StallProjectMessage();
                spm.setP_id(project.getP_id());
                spm.setSt_id(s.getSt_id());
                List<StallProjectMessage> spmList = stallProjectMessageDao.query(spm, -1, -1);
                if (!spmList.isEmpty()) {
                    return Result.fail("添加失败", "已存在该团队项目对应关系");
                }
            }
        }
        boolean inserted = stalldao.insert(stall);
        if (inserted) {
            StallTeamMessage stm = new StallTeamMessage();
            stm.setSt_id(stall.getSt_id());
            stm.setT_id(team.getT_id());
            StallProjectMessage spm = new StallProjectMessage();
            spm.setP_id(project.getP_id());
            spm.setSt_id(stall.getSt_id());
            boolean inserted1 = stallTeamMessageDao.insert(stm);
            boolean inserted2 = stallProjectMessageDao.insert(spm);
            if (inserted2&&inserted1) {
                return Result.success("添加成功");
            }
        }
        return Result.fail("添加失败", "添加失败");
    }

    public Result<String> updateMentor(Stall stall,Mentor mentor) {
        Stall tar = new Stall();
        tar.setSt_id(stall.getSt_id());
        List<Stall> exited = stalldao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该房间");
        }
        Mentor mentor1 = new Mentor();
        mentor1.setM_acc(mentor.getM_acc());
        List<Mentor> mentorList = (new MentorDao()).query(mentor1,-1,-1);
        if (mentorList.isEmpty()){
            return Result.fail("更新失败","没有该导师");
        }
        StallMentorMessage smm = new StallMentorMessage();
        smm.setSt_id(stall.getSt_id());
        smm.setU_acc(mentor.getM_acc());
        List<StallMentorMessage> smmList = stallMentorMessageDao.query(smm,-1,-1);
        if (!smmList.isEmpty()){
            return Result.fail("更新失败","已存在该房间导师对应关系");
        }
        boolean inserted = stallMentorMessageDao.insert(smm);
        if (!inserted){
            return Result.success("添加房间导师关联表失败");
        }
        int updated = stalldao.update(stall,tar);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }

//    public Result<String> updateTeam(Stall stall,Team team) {
//        Stall tar = new Stall();
//        tar.setSt_id(stall.getSt_id());
//        List<Stall> exited = stalldao.query(tar,-1,-1);
//        if (exited.isEmpty()){
//            return Result.fail("更新失败","没有该房间");
//        }
//        Team team1 = new Team();
//        team1.setT_id(team.getT_id());
//        List<Team> teamList = teamDao.query(team1,-1,-1);
//        if (teamList.isEmpty()){
//            return Result.fail("更新失败","没有该团队");
//        }
//        StallTeamMessage stm = new StallTeamMessage();
//        stm.setSt_id(stall.getSt_id());
//        stm.setT_id(team.getT_id());
//        List<StallTeamMessage> stmList = stallTeamMessageDao.query(stm,-1,-1);
//        if (!stmList.isEmpty()){
//            return Result.fail("更新失败","已存在该房间队伍对应关系");
//        }
//        boolean inserted = stallTeamMessageDao.insert(stm);
//        if (!inserted){
//            return Result.success("添加房间导师关联表失败");
//        }
//        int updated = stalldao.update(stall,tar);
//        if (updated == 0){
//            return Result.fail("更新失败","更新失败");
//        }
//        return Result.success("更新成功");
//    }

//    public Result<String> updateProject(Stall stall,Project project) {
//        Stall tar = new Stall();
//        tar.setSt_id(stall.getSt_id());
//        List<Stall> exited = stalldao.query(tar,-1,-1);
//        if (exited.isEmpty()){
//            return Result.fail("更新失败","没有该房间");
//        }
//        Project project1 = new Project();
//        project1.setP_id(project.getP_id());
//        List<Project> projectList = projectDao.query(project1,-1,-1);
//        if (projectList.isEmpty()){
//            return Result.fail("更新失败","没有该项目");
//        }
//        StallProjectMessage spm = new StallProjectMessage();
//        spm.setSt_id(stall.getSt_id());
//        spm.setP_id(project.getP_id());
//        List<StallProjectMessage> spmList = stallProjectMessageDao.query(spm,-1,-1);
//        if (!spmList.isEmpty()){
//            return Result.fail("更新失败","已存在该房间项目对应关系");
//        }
//        boolean inserted = stallProjectMessageDao.insert(spm);
//        if (!inserted){
//            return Result.success("添加房间导师关联表失败");
//        }
//        int updated = stalldao.update(stall,tar);
//        if (updated == 0){
//            return Result.fail("更新失败","更新失败");
//        }
//        return Result.success("更新成功");
//    }

    public Result<String> deleteMentor(Stall stall,Mentor mentor) {
        Stall tar = new Stall();
        tar.setSt_id(stall.getSt_id());
        List<Stall> exited = stalldao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("删除失败","没有该房间");
        }
        Mentor mentor1 = new Mentor();
        mentor1.setM_acc(mentor.getM_acc());
        List<Mentor> mentorList = (new MentorDao()).query(mentor1,-1,-1);
        if (mentorList.isEmpty()){
            return Result.fail("删除失败","没有该导师");
        }
        StallMentorMessage smm = new StallMentorMessage();
        smm.setSt_id(stall.getSt_id());
        smm.setU_acc(mentor.getM_acc());
        List<StallMentorMessage> smmList = stallMentorMessageDao.query(smm,-1,-1);
        if (smmList.isEmpty()){
            return Result.fail("删除失败","没有该房间导师对应关系");
        }
        int deleted = stallMentorMessageDao.delete(smm);
        if (deleted == 0){
            return Result.success("删除房间导师关联表失败");
        }
        return Result.success("删除成功");
    }

    public Result<String> update(Stall stall) {
        Stall tar = new Stall();
        tar.setSt_id(stall.getSt_id());
        List<Stall> exited = stalldao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该团队");
        }
        int updated = stalldao.update(stall,tar);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }
}
