package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.StudentTeamLink;

import java.util.List;
import java.util.Map;

public class StudentTeamLinkDao extends BaseDao<StudentTeamLinkDao> {
    public StudentTeamLinkDao() {
        super("student_team_link");
    }


    /**
     * 添加STeamLink
     */

    public boolean insert(StudentTeamLink StudentTeamLink) {

        Map<String, Object> map = StudentTeamLink.toMap();
        boolean inserted = super.insert(map);
        if (!inserted) {
            System.out.println("添加STeamLink失败");
            return false;
        } else {
            System.out.println("添加STeamLink成功");
            return true;
        }
    }

    /**
     * 删除STeamLink
     */
    public int delete(StudentTeamLink StudentTeamLink) {

        int deleted = super.delete(StudentTeamLink.toMap());
        if (deleted == 0) {
            System.out.println("删除STeamLink失败");
            return deleted;
        } else {
            System.out.println("删除STeamLink成功！");
            System.out.println("删除了" + deleted + "条数据");
            return deleted;
        }
    }

    /**
     * 更新STeamLink
     *
     * @param StudentTeamLink          更新内容
     * @param conditionStudentTeamLink 更新条件
     * @return 更新的条数
     */
    public int update(StudentTeamLink StudentTeamLink, StudentTeamLink conditionStudentTeamLink) {
        StudentTeamLink StudentTeamLink1 =new StudentTeamLink(StudentTeamLink);
        StudentTeamLink1.setSt_id(null);
        // 更新条件
        Map<String, Object> condition = StudentTeamLink1.toMap();

        Map<String, Object> map = conditionStudentTeamLink.toMap();

        int updated = super.update(map, condition);

        if (updated == 0) {
            System.out.println("更新STeamLink失败");
            return 0;
        } else {
            System.out.println("更新STeamLink成功！");
            System.out.println("更新了" + updated + "条数据");
            return updated;
        }
    }

    /**
     * 查询STeamLink
     */
    public List<StudentTeamLinkDao> query(StudentTeamLink StudentTeamLink, int current, int size) {
        // 查询结果
        List<StudentTeamLinkDao> StudentTeamLinkList = null;
        Map<String, Object> map = StudentTeamLink.toMap();

        StudentTeamLinkList = super.query(StudentTeamLinkDao.class, map, current, size);
        if (StudentTeamLinkList.isEmpty()) {
            System.out.println("查询STeamLink失败");
            return null;
        } else {
            System.out.println("查询STeamLink成功！");
            for (StudentTeamLinkDao c : StudentTeamLinkList) {
                System.out.println(c);
            }
            return StudentTeamLinkList;
        }
    }


    public int statistics(StudentTeamLinkDao StudentTeamLink) {
        return super.statistics(StudentTeamLink);
    }

    private final PageBean<StudentTeamLinkDao> pageBean = new PageBean<StudentTeamLinkDao>();

    public PageBean<StudentTeamLinkDao> queryByPage(Integer currentPage, StudentTeamLinkDao object){
        List<StudentTeamLinkDao> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}
