package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.MentorTeamCompLink;
import com.game.utils.Level;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MentorTeamCompLinkDao extends BaseDao<MentorTeamCompLink> {
    public MentorTeamCompLinkDao() {
        super("mentor_team_comp_link");
    }


    /**
     * 添加MTeamComLink
     */

    public boolean insert(MentorTeamCompLink MentorTeamCompLink) {

        boolean inserted = super.insert(MentorTeamCompLink.toMap());
        if (!inserted) {
            System.out.println("添加MTeamComLink失败");
            return false;
        } else {
            System.out.println("添加MTeamComLink成功");
            return true;
        }
    }

    /**
     * 删除MTeamComLink
     */
    public int delete(MentorTeamCompLink MentorTeamCompLink) {
        int deleted = super.delete(MentorTeamCompLink.toMap());
        if (deleted == 0) {
            System.out.println("删除MTeamComLink失败");
            return deleted;
        } else {
            System.out.println("删除MTeamComLink成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新MTeamComLink
     *
     * @param MentorTeamCompLink          更新内容
     * @param conditionMentorTeamCompLink 更新条件
     * @return 更新的条数
     */
    public int update(MentorTeamCompLink MentorTeamCompLink, MentorTeamCompLink conditionMentorTeamCompLink) {
        MentorTeamCompLink MentorTeamCompLink1 =new MentorTeamCompLink(MentorTeamCompLink);
        MentorTeamCompLink1.setC_id(null);
        // 更新条件
        Map<String, Object> condition = MentorTeamCompLink1.toMap();

        Map<String, Object> map = conditionMentorTeamCompLink.toMap();

        int updated = super.update(map, condition);

        if (updated == 0) {
            System.out.println("更新MTeamComLink失败");
            return 0;
        } else {
            System.out.println("更新MTeamComLink成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询MTeamComLink
     */
    public List<MentorTeamCompLink> query(MentorTeamCompLink MentorTeamCompLink, int current, int size) {
        // 查询结果
        Map<String, Object> map = MentorTeamCompLink.toMap();

        List<MentorTeamCompLink>  MentorTeamCompLinkList = super.query(MentorTeamCompLink.class, map, current, size);
        if (MentorTeamCompLinkList.isEmpty()) {
            System.out.println("查询MTeamComLink失败");
            return null;
        } else {
            System.out.println("查询MTeamComLink成功！");
            for (MentorTeamCompLink c : MentorTeamCompLinkList) {
                System.out.println(c);
            }
            return MentorTeamCompLinkList;
        }
    }


    public int statistics(MentorTeamCompLink MentorTeamCompLink) {
        return super.statistics(MentorTeamCompLink);
    }

    private final PageBean<MentorTeamCompLink> pageBean = new PageBean<MentorTeamCompLink>();

    public PageBean<MentorTeamCompLink> queryByPage(Integer currentPage, MentorTeamCompLink object){
        List<MentorTeamCompLink> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }


}
