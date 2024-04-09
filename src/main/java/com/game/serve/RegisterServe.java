package com.game.serve;

import com.game.bean.PageBean;
import com.game.dao.RegisterDao;
import com.game.domain.Competition;
import com.game.domain.Register;


import java.util.HashMap;
import java.util.List;

public class RegisterServe extends RegisterDao {
    private final PageBean<Register> pageBean = new PageBean<Register>(statistics(new HashMap<>()));

    public PageBean<Register> queryByPage(Integer currentPage, Register object){
        List<Register> result = null;
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalSize(statistics(object));
        result=query(object,pageBean.getBegin(),pageBean.getEnd());
        pageBean.setListPage(result);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

    public boolean addRegisterInfo(List<Register> registers) {
        boolean success = true; // 初始化一个标志以跟踪整体成功情况
        for (Register register : registers) {
            try {
                // 尝试添加竞赛信息
                insert(register);
            } catch (Exception e) {
                // 记录错误并继续处理其他竞赛
                // 你可能想要记录具体失败的竞赛
                System.err.println("添加竞赛信息时出错：" + e.getMessage());
                success = false; // 如果发生任何错误，则将成功标志设置为false
            }
        }
        return success; // 返回整体成功标志
    }

    public boolean addRegisterInfo(Register register){
        return insert(register);
    }
}
