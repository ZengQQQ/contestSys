package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.StallMentorMessage;

import java.util.List;
import java.util.Map;

public class StallMentorMessageDao extends BaseDao<StallMentorMessage> {
    public StallMentorMessageDao() {
        super("stall_mentor_message");
    }
    private StallMentorMessage model;


    /**
     * 添加StallMentorMessage
     */

    public boolean insert(StallMentorMessage StallMentorMessage) {

        boolean inserted = super.insert(StallMentorMessage.toMap());
        if (!inserted) {
            System.out.println("添加StallMentorMessage失败");
            return false;
        } else {
            System.out.println("添加StallMentorMessage成功");
            return true;
        }
    }

    /**
     * 删除StallMentorMessage
     */
    public int delete(StallMentorMessage StallMentorMessage) {
        int deleted = super.delete(StallMentorMessage.toMap());
        if (deleted == 0) {
            System.out.println("删除StallMentorMessage失败");
            return deleted;
        } else {
            System.out.println("删除StallMentorMessage成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新StallMentorMessage
     *
     * @param StallMentorMessage          更新内容
     * @param conditionStallMentorMessage 更新条件
     * @return 更新的条数
     */
    public int update(StallMentorMessage StallMentorMessage, StallMentorMessage conditionStallMentorMessage) {
        StallMentorMessage StallMentorMessage1 =new StallMentorMessage(StallMentorMessage);
        StallMentorMessage1.setSt_id(null);
        // 更新条件
        Map<String, Object> condition = StallMentorMessage1.toMap();

        Map<String, Object> map = conditionStallMentorMessage.toMap();

        int updated = super.update(map, condition);

        if (updated == 0) {
            System.out.println("更新StallMentorMessage失败");
            return 0;
        } else {
            System.out.println("更新StallMentorMessage成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询StallMentorMessage
     */
    public List<StallMentorMessage> query(StallMentorMessage StallMentorMessage, int current, int size) {
        // 查询结果
        Map<String, Object> map = StallMentorMessage.toMap();

        List<StallMentorMessage>  StallMentorMessageList = super.query(StallMentorMessage.class, map, current, size);
        if (StallMentorMessageList.isEmpty()) {
            System.out.println("查询StallMentorMessage失败");
            return null;
        } else {
            System.out.println("查询StallMentorMessage成功！");
            for (StallMentorMessage c : StallMentorMessageList) {
                System.out.println(c);
            }
            return StallMentorMessageList;
        }
    }


    private PageBean<StallMentorMessage> pageBean = new PageBean<StallMentorMessage>();


    public void initPage( StallMentorMessage object){
        this.model = object;
        List<StallMentorMessage> total =query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallMentorMessage> queryByPage(Integer currentPage, StallMentorMessage object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<StallMentorMessage> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }


}
