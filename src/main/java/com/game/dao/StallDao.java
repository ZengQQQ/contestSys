package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.Stall;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StallDao extends BaseDao<Stall> {
    public StallDao() {
        super("stall");
    }
    private Stall model;


    /**
     * 添加stall
     */

    public boolean insert(Stall stall) {

        boolean inserted = super.insert(stall.toMap());
        if (!inserted) {
            System.out.println("添加stall失败");
            return false;
        } else {
            System.out.println("添加stall成功");
            return true;
        }
    }

    /**
     * 删除stall
     */
    public int delete(Stall stall) {
        int deleted = super.delete(stall.toMap());
        if (deleted == 0) {
            System.out.println("删除stall失败");
            return deleted;
        } else {
            System.out.println("删除stall成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新stall
     *
     * @param stall          更新内容
     * @param conditionstall 更新条件
     * @return 更新的条数
     */
    public int update(Stall stall, Stall conditionstall) {
        Stall stall1 =new Stall(stall);
        stall1.setSt_id(null);
        // 更新条件
        Map<String, Object> condition = stall1.toMap();

        Map<String, Object> map = conditionstall.toMap();

        int updated = super.update(map, condition);

        if (updated == 0) {
            System.out.println("更新stall失败");
            return 0;
        } else {
            System.out.println("更新stall成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询stall
     */
    public List<Stall> query(Stall stall, int current, int size) {
        // 查询结果
        Map<String, Object> map = stall.toMap();

        List<Stall>  stallList = super.query(Stall.class, map, current, size);
        if (stallList.isEmpty()) {
            System.out.println("查询stall失败");
            return stallList;
        } else {
            System.out.println("查询stall成功！");
            for (Stall c : stallList) {
                System.out.println(c);
            }
            return stallList;
        }
    }


    private PageBean<Stall> pageBean = new PageBean<Stall>();


    public void initPage( Stall object){
        this.model=object;
        List<Stall> total =new ArrayList<>();
        total=query(object,-1,-1);
        pageBean.setTotalSize(total.size());
    }

    public PageBean<Stall> queryByPage(Integer currentPage, Stall object){
        if(!object.equals(this.model)){
            initPage(object);
        }
        List<Stall> result = null;
        pageBean.setCurrentPage(currentPage);
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        return pageBean;
    }


}
