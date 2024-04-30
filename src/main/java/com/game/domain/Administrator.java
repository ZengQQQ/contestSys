package com.game.domain;


import com.game.utils.ReflectionUtils;
import com.google.gson.Gson;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
@Data
public class Administrator extends ReflectionUtils{

    private Integer a_id;
    private String a_acc;
    private String a_pwd;
    private static String table_name="administrator";

    public Administrator() {
    }


    public Administrator(Administrator administrator){
        this.a_id = administrator.getA_id();
        this.a_acc = administrator.getA_acc();
        this.a_pwd = administrator.getA_pwd();
    }

    public Administrator(Integer a_id, String a_acc, String a_pwd) {
        this.a_id = a_id;
        this.a_acc = a_acc;
        this.a_pwd = a_pwd;
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }

    public Administrator mapToClass(Map<String, Object> map) {
        Class<?> clazz = this.getClass();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 使用反射找到类的属性并设置值
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true); // 设置可访问私有属性
                field.set(this, value);
            } catch (NoSuchFieldException e) {
                // 如果Map中的key不存在对应的类属性，可以选择忽略或者进行其他处理
                System.out.println("No such field: " + key);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("a_acc","123456");
        map.put("a_id",123456);
        map.put("M_id",123456);
        Administrator administrator = (new  Administrator()).mapToClass(map);

        System.out.println(administrator);
    }

}
