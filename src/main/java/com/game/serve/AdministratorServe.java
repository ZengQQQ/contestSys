package com.game.serve;

import com.game.dao.AdministratorDao;
import com.game.dao.CompetitionDao;
import com.game.dao.StudentDao;
import com.game.domain.Administrator;
import com.game.domain.Competition;
import com.game.domain.Student;

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
            System.out.println("信息不完全");
            return false;
        }


    }




}
