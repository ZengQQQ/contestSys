package com.game.serve;

import com.game.dao.MentorDao;
import com.game.domain.Mentor;
import com.game.utils.Result;

import java.util.List;

public class MentorServe {

    private static final MentorDao mentorDao = new MentorDao();

    public Result<String> insert(Mentor mentor) {
        List<Mentor> exited = mentorDao.query(mentor,-1,-1);
        if (!exited.isEmpty()){
            return Result.fail("添加失败","导师已存在");
        }
        boolean inserted = mentorDao.insert(mentor);
        if (inserted){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败","添加失败");
    }

    /**
     * 更新导师信息
     * @param mentor 新的导师信息，要求包含原id或acc
     * @return 更新结果
     */
    public Result<String> update(Mentor mentor) {

        // 通过id或acc定位导师
        Mentor cond = new Mentor();
        cond.setM_id(mentor.getM_id());
        cond.setM_acc(mentor.getM_acc());
        // 清楚新数据的唯一性字段
        mentor.setM_id(null);
        mentor.setM_acc(null);


        List<Mentor> exited = mentorDao.query(cond,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该导师");
        }
        int updated = mentorDao.update(mentor, cond);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }

    /**
     * 删除导师
     只使用id或acc
     * @param mentor 要删除的导师，要求包含id或acc
     * @return 删除结果
     */
    public Result<String> delete(Mentor mentor) {
        Mentor cond = new Mentor();
        cond.setM_id(mentor.getM_id());
        cond.setM_acc(mentor.getM_acc());


        List<Mentor> exited = mentorDao.query(cond,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("删除失败，没有该导师",null);
        }
        int deleted = mentorDao.delete(cond);
        if (deleted == 0){
            return Result.fail("删除失败",null);
        }
        return Result.success("删除成功");
    }

}
