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
            return Result.fail("更新失败","导师已存在");
        }
        boolean inserted = mentorDao.insert(mentor);
        if (inserted){
            return Result.success("");
        }
        return Result.fail("更新失败","");
    }

    public Result<String> update(Mentor mentor) {
        Mentor tar = new Mentor();
        tar.setM_id(mentor.getM_id());
        List<Mentor> exited = mentorDao.query(tar,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("更新失败","没有该导师");
        }
        int updated = mentorDao.update(mentor,tar);
        if (updated == 0){
            return Result.fail("更新失败","更新失败");
        }
        return Result.success("更新成功");
    }


    public Result<String> delete(Mentor mentor) {
        List<Mentor> exited = mentorDao.query(mentor,-1,-1);
        if (exited.isEmpty()){
            return Result.fail("删除失败，没有该导师",null);
        }
        int deleted = mentorDao.delete(mentor);
        if (deleted == 0){
            return Result.fail("删除失败",null);
        }
        return Result.success("删除成功");
    }

}
