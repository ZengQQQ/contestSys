package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.StallProjectMessage;

import java.util.List;
import java.util.Map;

public class StallProjectMessageDao extends BaseDao<StallProjectMessage> {
    public StallProjectMessageDao() {
        super("stall_task_message");
    }

    private StallProjectMessage model;


    /**
     * 添加StallTaskMessage
     */

    public boolean insert(StallProjectMessage StallTaskMessage) {

        boolean inserted = super.insert(StallTaskMessage.toMap());
        if (!inserted) {
            System.out.println("添加StallTaskMessage失败");
            return false;
        } else {
            System.out.println("添加StallTaskMessage成功");
            return true;
        }
    }

    /**
     * 删除StallTaskMessage
     */
    public int delete(StallProjectMessage StallTaskMessage) {
        int deleted = super.delete(StallTaskMessage.toMap());
        if (deleted == 0) {
            System.out.println("删除StallTaskMessage失败");
            return deleted;
        } else {
            System.out.println("删除StallTaskMessage成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新StallTaskMessage
     *
     * @param StallTaskMessage          更新内容
     * @param conditionStallTaskMessage 更新条件
     * @return 更新的条数
     */
    public int update(StallProjectMessage StallTaskMessage, StallProjectMessage conditionStallTaskMessage) {
        StallProjectMessage StallTaskMessage1 =new StallProjectMessage(StallTaskMessage);
        StallTaskMessage1.setSt_id(null);
        // 更新条件
        Map<String, Object> condition = StallTaskMessage1.toMap();

        Map<String, Object> map = conditionStallTaskMessage.toMap();

        int updated = super.update(map, condition);

        if (updated == 0) {
            System.out.println("更新StallTaskMessage失败");
            return 0;
        } else {
            System.out.println("更新StallTaskMessage成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询StallTaskMessage
     */
    public List<StallProjectMessage> query(StallProjectMessage StallTaskMessage, int current, int size) {
        // 查询结果
        Map<String, Object> map = StallTaskMessage.toMap();

        List<StallProjectMessage>  StallTaskMessageList = super.query(StallProjectMessage.class, map, current, size);
        if (StallTaskMessageList.isEmpty()) {
            System.out.println("查询StallTaskMessage失败");
            return null;
        } else {
            System.out.println("查询StallTaskMessage成功！");
            for (StallProjectMessage c : StallTaskMessageList) {
                System.out.println(c);
            }
            return StallTaskMessageList;
        }
    }


    public int statistics(StallProjectMessage StallTaskMessage) {
        return super.statistics(StallTaskMessage);
    }

    private PageBean<StallProjectMessage> pageBean = new PageBean<StallProjectMessage>();


    public void initPage(StallProjectMessage object){
        this.model=object;
        List<StallProjectMessage> total =query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<StallProjectMessage> queryByPage(Integer currentPage, StallProjectMessage object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<StallProjectMessage> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }


}
