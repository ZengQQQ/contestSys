package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.CompetitionDao;
import com.game.domain.secondary.workDomain.Competition;

import java.util.HashMap;
import java.util.List;

public class CompetitionServe extends CompetitionDao {

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



}
