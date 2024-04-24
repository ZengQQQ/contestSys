package com.game.dao;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectJoin;
import com.game.dao.base.BaseDao;
import com.game.domain.Register;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterDao extends BaseDao<Register> {

    public RegisterDao(){
        super("register");
    }


    public boolean insertByAllInfo(String r_xuehao, String r_name, String r_major) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("r_id",null);
        hashMap.put("s_xuehao",r_xuehao);
        hashMap.put("r_name",r_name);
        hashMap.put("r_major",r_major);

        return super.insert(hashMap);
    }

    public boolean findRecordByXuehao(String s_xuehao) {
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("s_xuehao",s_xuehao);
        List<Register> lists = super.query(Register.class, hashMap,2, 1);
        return !lists.isEmpty();
    }

    public int deleteByXuehao(String s_xuehao) {
        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put("s_xuehao",s_xuehao);
        return super.delete(hashMap);
    }

    /**
     * 查询数据
     *不要参数
     * @return 查询结果为Regiser类的列表，可能为空
     */
    public List<Register> queryAllStudent(){
        HashMap<String,Object> hashMap = new HashMap<>();
        return super.query(Register.class,hashMap,2,1);
    }

    //更新信息
    public int updateByXuehao(String s_xuehao,String new_name,String new_major){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("s_xuehao",s_xuehao);
        Map<String , Object> condition = new HashMap<>();
        condition.put("r_name",new_name);
        condition.put("r_major",new_major);
        return super.update(condition,hashMap);
    }

    public boolean insert(Register register){
       Map<String,Object> map = register.toMap();
       return super.insert(map);
    }

    public int delete(Register register) {
        Map<String,Object> map = register.toMap();
        return super.delete(map);
    }

    public int update(Register register,Register condition){
        Map<String,Object> map = register.toMap();
        map.remove("r_id");
        Map<String,Object> conditionMap = condition.toMap();
        return super.update(map,conditionMap);
    }

    public List<Register> query(Register register,int start,int end){
        Map<String,Object> map = register.toMap();
        return super.query(Register.class,map,start,end);
    }


    public int statistics(Register register) {
        return super.statistics(register);
    }

    public static void main(String[] args) {

    }

}
