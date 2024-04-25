package com.game.dao;

import com.game.bean.PageBean;
import com.game.dao.base.BaseDao;
import com.game.domain.User;
import java.util.List;
public class UserDao extends BaseDao<User> {
    public UserDao() {
        super("user");
    }

    @Override
    public boolean insert(User object) {
        boolean value = super.insert(object);
        if (value){
            System.out.println("用户添加成功");
        }else {
            System.out.println("用户添加失败");
        }
        return value;
    }

    @Override
    public int delete(User object) {
        int value = super.delete(object);
        if (value == 0){
            System.out.println("用户删除失败");
        }else {
            System.out.println("用户删除成功");
            System.out.println("删除了"+value+"条信息");
        }
        return value;
    }

    @Override
    public List<User> query(User object, int start, int end) {
        List<User> value = super.query(object, start, end);
        if (value.isEmpty()){
            System.out.println("查询用户失败");
        }else {
            System.out.println("查询用户成功");
            for (User v : value){
                System.out.println(v);
            }
        }
        return value;
    }

    @Override
    public int update(User object1, User object2) {
        int value = super.update(object1, object2);
        if (value == 0){
            System.out.println("用户更新失败");
        }else {
            System.out.println("用户更新成功");
            System.out.println("更新了"+value+"条信息");
        }
        return value;
    }
    private final PageBean<User> pageBean = new PageBean<User>();

    public PageBean<User> queryByPage(Integer currentPage,User object) {
        List<User> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result = query(object, pageBean.getBegin(), pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}