package com.game.utils;

import com.game.bean.ConditionBean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.lang.reflect.Modifier;
import java.util.Set;

public class ReflectionUtils {

    /**
     * 反射非static数据为map
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Map<String, Object> mapFields(T object) {
        Map<String, Object> resultMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true); // 使得 private 字段可访问
                try {
                    Object value = field.get(object); // 获取字段的值
                    if (value != null) {
                        resultMap.put(field.getName(), value); // 将字段名和值映射到Map中
                    }
                } catch (IllegalAccessException e) {
                    // 处理异常
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    /**
     * 用于建立连接查询关系
     * @param object
     * @return
     * @param <T>
     */
    public static <T> ConditionBean mapFieldsKey(T object) {
        ConditionBean conditionBean = new ConditionBean();
        Set<String> keys=new HashSet<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true); // 使得 private 字段和静态字段可访问
                // 针对静态字段，传递 null 到 field.get() 方法
                keys.add(field.getName()); // 将字段名和值映射到Map中
            }
        }
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    Object value = Modifier.isStatic(field.getModifiers()) ? field.get(null) : field.get(object);
                    if (field.getName() == "table_name") {
                        String key = (String) value;
                        conditionBean.setTableName(key);
                        conditionBean.setKeys(keys);
                    }
                }
            }catch (IllegalAccessException e) {
                // 处理异常
                e.printStackTrace();
            }
        }
        return conditionBean;
    }


    /**
     *
     * 反射所有包括static数据为map
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Map<String, Object> mapFieldsPlus(T object) {
        Map<String, Object> resultMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 使得 private 字段和静态字段可访问
            try {
                // 针对静态字段，传递 null 到 field.get() 方法
                Object value = Modifier.isStatic(field.getModifiers()) ? field.get(null) : field.get(object);
                if (value != null) {
                    resultMap.put(field.getName(), value); // 将字段名和值映射到Map中
                }
            } catch (IllegalAccessException e) {
                // 处理异常
                e.printStackTrace();
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {
        // 示例类
        class MyClass {
            private Integer intValue ;
            private String stringValue ;
            private Boolean booleanValue;

            private static String table_name="table1_name";
            private static String table="1";

        }

        // 创建示例对象
        MyClass obj = new MyClass();

        // 映射变量名和值到Map中
        ConditionBean resultMap = mapFieldsKey(obj);

        // 输出结果
        for (String entry : resultMap.getKeys()) {
            System.out.println(resultMap.getTableName() + " : " + entry);
        }
    }
}
