package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.secondary.teamDomain.WorkApplication;
import java.util.List;

public class WorkApplicationDao extends BaseDao<WorkApplication> {
    public WorkApplicationDao() {
        super("work_applicaion");
    }
    @Override
    public boolean insert(WorkApplication object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("队伍申请工作添加成功");
        }else {
            System.out.println("队伍申请工作添加失败");
        }
        return value;
    }

    @Override
    public int delete(WorkApplication object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("队伍申请工作删除失败");
        }else {
            System.out.println("队伍申请工作删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<WorkApplication> query(WorkApplication object, int start, int end) {
        List<WorkApplication> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询队伍申请工作失败");
        }else {
            System.out.println("查询队伍申请工作成功");
            for (WorkApplication v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(WorkApplication object1, WorkApplication object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("队伍申请工作更新失败");
        }else {
            System.out.println("队伍申请工作更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }
    private final PageBean<WorkApplication> pageBean = new PageBean<WorkApplication>();

    public PageBean<WorkApplication> queryByPage(Integer currentPage,WorkApplication object) {
        List<WorkApplication> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
