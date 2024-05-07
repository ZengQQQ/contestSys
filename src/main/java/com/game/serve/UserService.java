package com.game.serve;

import com.game.dao.UserDao;
import com.game.domain.User;
import com.game.utils.Result;

import java.util.List;

public class UserService {

    private static final UserDao userDao = new UserDao();

    public Result<String> insert(User user) {
        List<User> exited = userDao.query(user,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("添加失败","用户已存在");
        }
        boolean inserted = userDao.insert(user);
        if (inserted){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败",null);
    }

    /**
     * 更新用户信息
     * @param user 新的用户信息，要求包含原id或acc
     * @return 更新结果
     */
    public Result<String> update(User user) {

        // 通过id或acc定位用户
        User cond = new User();
        cond.setU_id(user.getU_id());
        cond.setU_acc(user.getU_acc());
        // 清楚新数据的唯一性字段
        user.setU_id(null);
        user.setU_acc(null);


        List<User> exited = userDao.query(cond,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该用户");
        }
        int updated = userDao.update(user, cond);
        if (updated == 0){
            return Result.fail("更新失败",null);
        }
        return Result.success("更新成功");
    }

    /**
     * 删除用户
     只使用id或acc
     * @param user 要删除的用户，要求包含id或acc
     * @return 删除结果
     */
    public Result<String> delete(User user) {
        User cond = new User();
        cond.setU_id(user.getU_id());
        cond.setU_acc(user.getU_acc());


        List<User> exited = userDao.query(cond,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("删除失败，没有该用户",null);
        }
        int deleted = userDao.delete(cond);
        if (deleted == 0){
            return Result.fail("删除失败",null);
        }
        return Result.success("删除成功");
    }

}
