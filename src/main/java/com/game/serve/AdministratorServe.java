package com.game.serve;

import com.game.dao.AdministratorDao;
import com.game.dao.CompetitionDao;
import com.game.dao.StudentDao;
import com.game.domain.Administrator;
import com.game.domain.Competition;
import com.game.domain.Student;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdministratorServe extends AdministratorDao {

    public boolean checkPassword(Administrator ad){
        if(ad.getA_acc()!=null && ad.getA_pwd()!=null){
            List<Administrator> adList = this.query(ad,-1,-1);
            return !adList.isEmpty();
        }
        else{
            return false;
        }
    }

    //封禁学生账号
    public boolean banStudentAccount(Student stu){
        if(stu.getS_status()!=1){
            HashMap<String,Object> newStatus = new HashMap<>();
            newStatus.put("s_status", 0);
            StudentDao studentDao = new StudentDao();
            return studentDao.update(newStatus, stu.toMap()) != 0;
        }else{
            System.out.println("该账号目前已经被封禁");
            return false;
        }
    }
  //解封学生账号
  public boolean unBanStudentAccount(Student stu){
      if(stu.getS_status()!=0){
          HashMap<String,Object> newStatus = new HashMap<>();
          newStatus.put("s_status", 1);
          StudentDao studentDao = new StudentDao();
          return studentDao.update(newStatus, stu.toMap()) != 0;
      }else{
          System.out.println("该账号处于正常状态");
          return false;
      }
  }

  //添加比赛信息
  public boolean addCompetitionInfo(Competition competition){
        if(competition.getC_name() !=null && competition.getC_level()!=null){
            CompetitionDao competitionDao = new CompetitionDao();
            return competitionDao.insert(competition);
        }else{
            System.out.println("添加失败,信息不完全");
            return false;
        }
    }

    public boolean addCompetitionInfo(List<Competition> competitionList) {
        boolean success = true; // 初始化一个标志以跟踪整体成功情况

        for (Competition competition : competitionList) {
            try {
                // 尝试添加竞赛信息
                addCompetitionInfo(competition);
            } catch (Exception e) {
                // 记录错误并继续处理其他竞赛
                // 你可能想要记录具体失败的竞赛
                System.err.println("添加竞赛信息时出错：" + e.getMessage());
                success = false; // 如果发生任何错误，则将成功标志设置为false
            }
        }
        return success; // 返回整体成功标志
    }

    //删除帖子
    public boolean deletePostings(){
        return true;
    }



}
