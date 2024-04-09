package com.game.dao;

import com.game.dao.base.BaseDao;
import com.game.domain.Administrator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员表的增删改查
 */
public class AdministratorDao extends BaseDao<Administrator> {

    public AdministratorDao() {
        super("administrator");
    }



    public boolean insert(Administrator administrator) {
        Map<String, Object> map=administrator.toMap();
        return super.insert(map);
    }


    public int delete(Administrator administrator) {
        Map<String, Object> map=administrator.toMap();
        return super.delete(map);
    }


    public List<Administrator> query( Administrator administrator, int start, int end) {
        Map<String, Object> map=administrator.toMap();
        return super.query(Administrator.class, map, start, end);
    }

    public int update(Administrator administrator, Administrator condition) {
        Map<String, Object> map = administrator.toMap();
        map.remove("a_id");
        Map<String, Object> con = condition.toMap();
        return super.update(map, con);
    }

    /**
     * 检查管理员账号密码是否正确
     * @param administrator 管理员对象，包含账号密码
     * @return 是否正确
     */
    public boolean checkPassword(Administrator administrator) {
        List<Administrator> ads = null;
        HashMap<String, Object> map = new HashMap<>();
        map.put("a_acc", administrator.getA_acc());
        map.put("a_pwd", administrator.getA_pwd());
        ads= super
                .query(Administrator.class, map, 0, 1);
        return !ads.isEmpty();
    }


    public static void main(String[] args) {
        AdministratorDao administratorDao = new AdministratorDao();
        Administrator administrator = new Administrator();
//        administrator.setA_acc("123");
//        administrator.setA_pwd("1234");
//        System.out.println(administratorDao.checkPassword(administrator));
        System.out.println(administratorDao.statistics());
        System.out.println(administratorDao.query(administrator,-1,-1));
    }

}
