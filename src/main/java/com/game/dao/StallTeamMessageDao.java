package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.StallTeamMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StallTeamMessageDao extends BaseDao<StallTeamMessage> {
    public StallTeamMessageDao() {
        super("stall_team_message");
    }

    private StallTeamMessage model;


    /**
     * 添加StallTeamMessage
     */

    public boolean insert(StallTeamMessage StallTeamMessage) {

        boolean inserted = super.insert(StallTeamMessage.toMap());
        if (!inserted) {
            System.out.println("添加StallTeamMessage失败");
            return false;
        } else {
            System.out.println("添加StallTeamMessage成功");
            return true;
        }
    }

    /**
     * 删除StallTeamMessage
     */
    public int delete(StallTeamMessage StallTeamMessage) {
        int deleted = super.delete(StallTeamMessage.toMap());
        if (deleted == 0) {
            System.out.println("删除StallTeamMessage失败");
            return deleted;
        } else {
            System.out.println("删除StallTeamMessage成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新StallTeamMessage
     *
     * @param StallTeamMessage          更新内容
     * @param conditionStallTeamMessage 更新条件
     * @return 更新的条数
     */
    public int update(StallTeamMessage StallTeamMessage, StallTeamMessage conditionStallTeamMessage) {
        StallTeamMessage StallTeamMessage1 =new StallTeamMessage(StallTeamMessage);
        StallTeamMessage1.setSt_id(null);
        // 更新条件
        Map<String, Object> condition = StallTeamMessage1.toMap();

        Map<String, Object> map = conditionStallTeamMessage.toMap();

        int updated = super.update(map, condition);

        if (updated == 0) {
            System.out.println("更新StallTeamMessage失败");
            return 0;
        } else {
            System.out.println("更新StallTeamMessage成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询StallTeamMessage
     */
    public List<StallTeamMessage> query(StallTeamMessage StallTeamMessage, int current, int size) {
        // 查询结果
        Map<String, Object> map = StallTeamMessage.toMap();

        List<StallTeamMessage>  StallTeamMessageList = super.query(StallTeamMessage.class, map, current, size);
        if (StallTeamMessageList.isEmpty()) {
            System.out.println("查询StallTeamMessage失败");
            return null;
        } else {
            System.out.println("查询StallTeamMessage成功！");
            for (StallTeamMessage c : StallTeamMessageList) {
                System.out.println(c);
            }
            return StallTeamMessageList;
        }
    }


    public int statistics(StallTeamMessage StallTeamMessage) {
        return super.statistics(StallTeamMessage);
    }

    private PageBean<StallTeamMessage> pageBean = new PageBean<StallTeamMessage>();


    public void initPage( StallTeamMessage object){
        this.model=object;
        List<StallTeamMessage> total =new ArrayList<>();
        total=query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallTeamMessage> queryByPage(Integer currentPage, StallTeamMessage object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<StallTeamMessage> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }


}
