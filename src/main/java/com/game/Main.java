package com.game;

import com.game.dao.base.BaseDao;
import com.game.domain.Administrator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        BaseDao<Administrator> te = new BaseDao<Administrator>();
        te.tableName="administrator";
        Map<String, Object> map = new HashMap<>();
        map.put("a_acc",123);
        Map<String, Object> map2 = new HashMap<>();
        map.put("a_pwd",1234);
        System.out.println(te.update(map,map2));
        System.out.println("Hello world!");
    }
}