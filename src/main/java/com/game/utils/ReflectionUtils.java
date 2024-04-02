package com.game.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {

    public static <T> Map<String, Object> mapFields(T object) {
        Map<String, Object> resultMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 设置字段为可访问，即使是私有字段也可以访问
            try {
                Object value = field.get(object); // 获取字段的值
                resultMap.put(field.getName(), value); // 将字段名和值映射到Map中
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
            private int intValue = 10;
            private String stringValue = "Hello";
            private boolean booleanValue = true;
        }

        // 创建示例对象
        MyClass obj = new MyClass();

        // 映射变量名和值到Map中
        Map<String, Object> resultMap = mapFields(obj);

        // 输出结果
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
