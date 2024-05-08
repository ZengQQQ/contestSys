package com.game.serve;

import com.game.dao.*;
import com.game.domain.*;
import com.game.utils.Result;

import java.util.List;

public class StallService {
    private static final StallDao stalldao = new StallDao();
    private static final ProjectDao projectDao = new ProjectDao();

    private static final UserDao userDao = new UserDao();
    private static final MentorDao mentorDao = new MentorDao();
    private static final TeamDao teamDao = new TeamDao();
    private static final StallProjectMessageDao stallProjectMessageDao = new StallProjectMessageDao();
    private static final StallTeamMessageDao stallTeamMessageDao = new StallTeamMessageDao();
    private static final StallMentorMessageDao stallMentorMessageDao = new StallMentorMessageDao();

    public Result<String> insert(TeamProjectMessage teamProjectMessage) {
        Team team = new Team();
        team.setT_id(teamProjectMessage.getT_id());
        Project project = new Project();
        project.setP_id(teamProjectMessage.getP_id());
        List<Project> projectList = projectDao.query(project, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (projectList.isEmpty() || teamList.isEmpty()) {
            return Result.fail("添加失败,无目标项目与队伍", "");
        }
        team = teamList.get(0);
        project = projectList.get(0);
        Stall stall = new Stall();
        stall.setU_acc(team.getU_acc());
        stall.setSt_id(team.getT_id());
        List<Stall> stallList = stalldao.query(stall, -1, -1);
        if (!stallList.isEmpty()) {
            for (Stall s : stallList) {
                StallProjectMessage spm = new StallProjectMessage();
                spm.setP_id(project.getP_id());
                spm.setSt_id(s.getSt_id());
                List<StallProjectMessage> spmList = stallProjectMessageDao.query(spm, -1, -1);
                if (!spmList.isEmpty()) {
                    return Result.fail("添加失败,已存在该团队项目对应关系", "");
                }
            }
        }

        boolean inserted = stalldao.insert(stall);
        StallTeamMessage stm1 = new StallTeamMessage();
        stm1.setJoin_status(1);
        stm1.setSt_id(stall.getSt_id());
        List<StallTeamMessage> stmList1 = stallTeamMessageDao.query(stm1, -1, -1);
        if (project.getP_maxtime() != 0 && stmList1.size() >= project.getP_maxtime()) {
            return Result.fail("添加失败,超出队伍数量限制", "");
        }
        if (inserted) {
            StallTeamMessage stm = new StallTeamMessage();
            stm.setSt_id(stall.getSt_id());
            stm.setT_id(team.getT_id());
            StallProjectMessage spm = new StallProjectMessage();
            spm.setP_id(project.getP_id());
            spm.setSt_id(stall.getSt_id());
            for (Project p : projectList) {
                if (p.getP_resagree() == 0) {
                    stm.setStm_pass(1);
                    stm.setJoin_status(1);
                    stm.setStm_dct(1);
                    spm.setSpm_pass(1);
                    spm.setJoin_status(1);
                    spm.setSpm_dct(1);
                    boolean inserted1 = stallTeamMessageDao.insert(stm);
                    boolean inserted2 = stallProjectMessageDao.insert(spm);
                    if (inserted2 && inserted1) {
                        return Result.success("加入成功（无需申请）");
                    }
                }
            }
            if (teamProjectMessage.getTp_dict() == 1) {
                stm.setStm_pass(1);
                stm.setJoin_status(1);
                stm.setStm_dct(1);
                spm.setSpm_dct(1);
                spm.setSpm_info(teamProjectMessage.getTp_info());
                boolean inserted1 = stallTeamMessageDao.insert(stm);
                boolean inserted2 = stallProjectMessageDao.insert(spm);
                if (inserted2 && inserted1) {
                    return Result.success("已发送申请");
                }
            } else if (teamProjectMessage.getTp_dict() == 0) {
                spm.setSpm_pass(1);
                spm.setJoin_status(1);
                spm.setSpm_dct(0);
                stm.setStm_dct(0);
                stm.setStm_pass(0);
                stm.setStm_info(teamProjectMessage.getTp_info());
                boolean inserted1 = stallTeamMessageDao.insert(stm);
                boolean inserted2 = stallProjectMessageDao.insert(spm);
                if (inserted2 && inserted1) {
                    return Result.success("已发送邀请");
                }
            }
        }
        return Result.fail("添加失败", "");
    }

    public Result<String> updateApproval(TeamProjectMessage teamProjectMessage) {
        StallTeamMessage stm = new StallTeamMessage();
        StallProjectMessage spm = new StallProjectMessage();
        stm.setSt_id(teamProjectMessage.getSt_id());
        spm.setSt_id(teamProjectMessage.getSt_id());
        spm.setP_id(teamProjectMessage.getP_id());
        List<StallProjectMessage> spmList = stallProjectMessageDao.query(spm, -1, -1);
        if (spmList.isEmpty()) {
            return Result.fail("更新失败,没有该团队项目对应关系", "");
        }
        spm = spmList.get(0);
        Project project = new Project();
        Team team = new Team();
        project.setP_id(teamProjectMessage.getP_id());
        team.setT_id(teamProjectMessage.getT_id());
        List<Project> projectList = projectDao.query(project, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (teamProjectMessage.getTp_pass() == 1) {
            for (Project p : projectList) {
                if (p.getP_status() != 0) {
                    return Result.fail("更新失败,项目已经关闭", "");
                }
            }
            for (Team t : teamList) {
                if (t.getT_status() != 0) {
                    return Result.fail("更新失败,团队状态异常", "");
                }
            }
            StallTeamMessage stm1 = new StallTeamMessage();
            stm1.setJoin_status(1);
            stm1.setSt_id(teamProjectMessage.getT_id());
            List<StallTeamMessage> stmList1 = stallTeamMessageDao.query(stm1, -1, -1);
            if (project.getP_maxtime() != 0 && stmList1.size() >= project.getP_maxtime()) {
                return Result.fail("添加失败,超出队伍数量限制", "");
            }
            stm.setStm_pass(1);
            stm.setJoin_status(1);
            spm.setSpm_pass(1);
            spm.setJoin_status(1);
            int updated1 = stallTeamMessageDao.update(stm, stm);
            int updated2 = stallProjectMessageDao.update(spm, spm);
            if (updated1 == 0 || updated2 == 0) {
                return Result.fail("更新失败", "");
            }
        } else if (teamProjectMessage.getTp_pass() == 2) {
            if (spm.getSpm_dct() == 0) {
                stm.setStm_pass(2);
                return Result.success("拒绝邀请成功");
            } else if (spm.getSpm_dct() == 1) {
                spm.setSpm_pass(2);
                return Result.success("拒绝申请成功");
            }
        } else if (teamProjectMessage.getTp_pass() == 3) {
            if (spm.getSpm_dct() == 0) {
                stm.setStm_pass(3);
                return Result.success("撤回邀请成功");
            } else if (spm.getSpm_dct() == 1) {
                spm.setSpm_pass(3);
                return Result.success("撤回申请成功");
            }
        } else if (teamProjectMessage.getTp_join() == 0) {
            stm.setJoin_status(0);
            stm.setStm_pass(0);
            spm.setJoin_status(0);
            spm.setSpm_pass(0);
            int updated1 = stallTeamMessageDao.update(stm, stm);
            int updated2 = stallProjectMessageDao.update(spm, spm);
            if (updated1 == 0 || updated2 == 0) {
                return Result.fail("更新失败", "");
            }
        }
        return Result.fail("更新失败,参数错误", "");
    }


    public Result<String> updateApproval(TeamMentorMessage message) {
        StallMentorMessage smm = new StallMentorMessage();
        smm.setSt_id(message.getSt_id());
        Mentor mentor1 = new Mentor();
        mentor1.setM_acc(message.getM_acc());
        mentor1 = new MentorDao().querySingle(mentor1);
        smm.setU_acc(mentor1.getM_acc());
        List<StallMentorMessage> spmList = stallMentorMessageDao.query(smm, -1, -1);
        if (spmList.isEmpty()) {
            return Result.fail("更新失败,没有该团队导师对应关系", "");
        }
        smm = spmList.get(0);
        Mentor mentor = new Mentor();
        Team team = new Team();
        mentor.setM_acc(message.getM_acc());
        team.setT_id(message.getT_id());
        mentor = mentorDao.querySingle(mentor);
        User user = new User();
        user.setU_acc(mentor.getM_acc());
        List<User> mentorList = userDao.query(user, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (message.getSm_pass() == 1) {
            for (User p : mentorList) {
                if (p.getU_status() != 0) {
                    return Result.fail("更新失败,导师状态异常", "");
                }
            }
            for (Team t : teamList) {
                if (t.getT_status() != 0) {
                    return Result.fail("更新失败,团队状态异常", "");
                }
            }
            StallMentorMessage stallMentorMessage = new StallMentorMessage();
            stallMentorMessage = smm;
            smm.setSmm_pass(1);
            smm.setJoin_status(1);
            int updated2 = stallMentorMessageDao.update(smm, stallMentorMessage);
            if (updated2 == 0) {
                return Result.fail("更新失败", "");
            }
        } else if (message.getSm_pass() == 2) {
            if (smm.getSmm_dct() == 0) {
                smm.setSmm_pass(2);
                StallMentorMessage stallMentorMessage = new StallMentorMessage();
                stallMentorMessage = smm;
                int updated2 = stallMentorMessageDao.update(smm, stallMentorMessage);
                if (updated2 == 0) {
                    return Result.fail("更新失败", "");
                }
                return Result.success("拒绝邀请成功");
            } else if (smm.getSmm_dct() == 1) {
                smm.setSmm_pass(2);
                StallMentorMessage stallMentorMessage = new StallMentorMessage();
                stallMentorMessage = smm;
                int updated2 = stallMentorMessageDao.update(smm, stallMentorMessage);
                if (updated2 == 0) {
                    return Result.fail("更新失败", "");
                }
                return Result.success("拒绝申请成功");
            }
        } else if (message.getSm_join() == 0) {
            smm.setJoin_status(0);
            smm.setSmm_pass(0);
            int updated2 = stallMentorMessageDao.update(smm, smm);
            if (updated2 == 0) {
                return Result.fail("更新失败", "");
            }
            return Result.success("解除导师成功");
        }
        return Result.fail("更新失败,参数错误", "");
    }


    public Result<String> insert(TeamMentorMessage message) {
        Team team = new Team();
        team.setT_id(message.getT_id());
        Mentor mentor = new Mentor();
        mentor.setM_acc(message.getM_acc());
        mentor = mentorDao.querySingle(mentor);
        User user = new User();
        user.setU_acc(mentor.getM_acc());
        List<User> mentorList = userDao.query(user, -1, -1);
        List<Team> teamList = teamDao.query(team, -1, -1);
        if (mentorList.isEmpty() || teamList.isEmpty()) {
            return Result.fail("添加失败,无目标导师与队伍", "");
        }
        team = teamList.get(0);
        user = mentorList.get(0);
        Stall stall = new Stall();
        stall.setU_acc(team.getU_acc());
        stall.setSt_id(team.getT_id());
        List<Stall> stallList = stalldao.query(stall, -1, -1);
        if (!stallList.isEmpty()) {
            for (Stall s : stallList) {
                StallMentorMessage spm = new StallMentorMessage();
                spm.setU_acc(user.getU_acc());
                spm.setSt_id(s.getSt_id());
                List<StallMentorMessage> spmList = stallMentorMessageDao.query(spm, -1, -1);
                if (!spmList.isEmpty()) {
                    return Result.fail("添加失败,已存在该导师项目对应关系", "");
                }
            }
        }


        StallTeamMessage stm1 = new StallTeamMessage();
        stm1.setJoin_status(1);
        stm1.setSt_id(stall.getSt_id());

        StallMentorMessage smm = new StallMentorMessage();
        smm.setU_acc(user.getU_acc());
        smm.setSt_id(stall.getSt_id());
        if (message.getSm_dct() == 1) {
            smm.setSmm_dct(1);
            smm.setSmm_info(message.getSm_info());
            boolean inserted2 = stallMentorMessageDao.insert(smm);
            if (inserted2) {
                return Result.success("已发送申请");
            }
        } else if (message.getSm_dct() == 0) {
            smm.setSmm_dct(0);
            smm.setSmm_info(message.getSm_info());
            boolean inserted2 = stallMentorMessageDao.insert(smm);
            if (inserted2) {
                return Result.success("已发送邀请");
            }
        }
        return Result.fail("添加失败", "");
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

    public Result<String> deleteMentor(Stall stall, Mentor mentor) {
        Stall tar = new Stall();
        tar.setSt_id(stall.getSt_id());
        List<Stall> exited = stalldao.query(tar, -1, -1);
        if (exited.isEmpty()) {
            return Result.fail("删除失败", "没有该房间");
        }
        Mentor mentor1 = new Mentor();
        mentor1.setM_acc(mentor.getM_acc());
        List<Mentor> mentorList = (new MentorDao()).query(mentor1, -1, -1);
        if (mentorList.isEmpty()) {
            return Result.fail("删除失败", "没有该导师");
        }

        StallMentorMessage smm = new StallMentorMessage();
        smm.setSt_id(stall.getSt_id());
        smm.setU_acc(mentor.getM_acc());
        List<StallMentorMessage> smmList = stallMentorMessageDao.query(smm, -1, -1);
        if (smmList.isEmpty()) {
            return Result.fail("删除失败", "没有该房间导师对应关系");
        }
        int deleted = stallMentorMessageDao.delete(smm);
        if (deleted == 0) {
            return Result.success("删除房间导师关联表失败");
        }
        return Result.success("删除成功");
    }


    public Result<String> update(Stall stall) {
        Stall tar = new Stall();
        tar.setSt_id(stall.getSt_id());
        List<Stall> exited = stalldao.query(tar, -1, -1);
        if (exited.isEmpty()) {
            return Result.fail("更新失败", "没有该团队");
        }
        int updated = stalldao.update(stall, tar);
        if (updated == 0) {
            return Result.fail("更新失败", "更新失败");
        }
        return Result.success("更新成功");
    }
}
