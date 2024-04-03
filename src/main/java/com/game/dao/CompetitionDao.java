package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Competition;
import com.game.utils.Level;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * CompetitionDao
 * 比赛数据访问类
 */
public class CompetitionDao extends BaseDao<Competition> {

    public CompetitionDao() {
        super("competition");
    }


    /**
     * 添加比赛
     */

    public boolean instert(Competition competition) {

        HashMap<String, Object> map = new HashMap<>();
        if (competition.getC_id() != null) {
            map.put("c_id", competition.getC_id());
        }
        if (competition.getC_name() != null) {
            map.put("c_name", competition.getC_name());
        }
        if (competition.getC_sign_t() != null) {
            map.put("c_sign_t", competition.getC_sign_t());
        }
        if (competition.getC_url() != null) {
            map.put("c_url", competition.getC_url());
        }
        if (competition.getC_img() != null) {
            map.put("c_img", competition.getC_img());
        }
        if (competition.getC_level() != null) {
            map.put("c_level", competition.getC_level());
        }

        boolean inserted = super.insert(map);
        if (!inserted) {
            System.out.println("添加比赛失败");
            return false;
        } else {
            System.out.println("添加比赛成功");
            return true;
        }
    }

    /**
     * 删除比赛
     */
    public int delete(Competition competition) {
        HashMap<String, Object> map = new HashMap<>();
        if (competition.getC_id() != null) {
            map.put("c_id", competition.getC_id());
        }
        int deleted = super.delete(map);
        if (deleted == 0) {
            System.out.println("删除比赛失败");
            return deleted;
        } else {
            System.out.println("删除比赛成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新比赛
     *
     * @param competition          更新内容
     * @param conditionCompetition 更新条件
     * @return 更新的条数
     */
    public int update(Competition competition, Competition conditionCompetition) {

        // 更新条件
        HashMap<String, Object> condition = new HashMap<>();
        if (conditionCompetition.getC_id() != null) {
            condition.put("c_id", conditionCompetition.getC_id());
        }
        if (conditionCompetition.getC_name() != null) {
            condition.put("c_name", conditionCompetition.getC_name());
        }
        if (conditionCompetition.getC_sign_t() != null) {
            condition.put("c_sign_t", conditionCompetition.getC_sign_t());
        }
        if (conditionCompetition.getC_url() != null) {
            condition.put("c_url", conditionCompetition.getC_url());
        }
        if (conditionCompetition.getC_img() != null) {
            condition.put("c_img", conditionCompetition.getC_img());
        }


        // 更新内容
        HashMap<String, Object> map = new HashMap<>();
        if (competition.getC_name() != null) {
            map.put("c_name", competition.getC_name());
        }
        if (competition.getC_sign_t() != null) {
            map.put("c_sign_t", competition.getC_sign_t());
        }
        if (competition.getC_url() != null) {
            map.put("c_url", competition.getC_url());
        }
        if (competition.getC_img() != null) {
            map.put("c_img", competition.getC_img());
        }


        int updated = super.update(map, condition);


        if (updated == 0) {
            System.out.println("更新比赛失败");
            return 0;
        } else {
            System.out.println("更新比赛成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询比赛
     */
    public List<Competition> select(Competition competition, int current, int size) {
        // 查询结果
        List<Competition> competitionList = null;

        // 查询条件
        HashMap<String, Object> map = new HashMap<>();
        if (competition.getC_id() != null) {
            map.put("c_id", competition.getC_id());
        }
        if (competition.getC_name() != null) {
            map.put("c_name", competition.getC_name());
        }
        if (competition.getC_sign_t() != null) {
            map.put("c_sign_t", competition.getC_sign_t());
        }
        if (competition.getC_url() != null) {
            map.put("c_url", competition.getC_url());
        }
        if (competition.getC_img() != null) {
            map.put("c_img", competition.getC_img());
        }

        competitionList = super.query(Competition.class, map, current, size);
        if (competitionList.isEmpty()) {
            System.out.println("查询比赛失败");
            return null;
        } else {
            System.out.println("查询比赛成功！");
            for (Competition c : competitionList) {
                System.out.println(c);
            }
            return competitionList;
        }
    }


    public static void main(String[] args) {
        CompetitionDao competitionDao = new CompetitionDao();
        Competition competition = new Competition();
        competition.setC_id(1);
        competition.setC_name("比赛1");
        competition.setC_sign_t(LocalDateTime.of(2021, 1, 1, 0, 0, 0));
        competition.setC_url("www.baidu.com");
        competition.setC_img("img");
        competition.setC_level(Level.A);

        // 添加比赛
//        boolean instert = competitionDao.instert(competition);
//        System.out.println(instert);

//         查询比赛
//        List<Competition> competitionList = competitionDao.select(competition, 0, 10);
//        for (Competition c : competitionList) {
//            System.out.println(c.toString());
//        }
//
//        // 更新比赛
//        competition.setC_name("比赛2");
//        Competition conditionCompetition = new Competition();
//        conditionCompetition.setC_id(1);
//        int updated = competitionDao.update(competition, conditionCompetition);
//        System.out.println(updated);
//
//        // 删除比赛
        int delete = competitionDao.delete(competition);
        System.out.println(delete);
    }


}
