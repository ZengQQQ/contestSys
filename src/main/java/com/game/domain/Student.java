package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class Student extends ReflectionUtils {
    private Integer s_id;
    private String s_xuehao;
    private String s_name;
    private String s_major;
    private Integer s_status;
    private static String table_name="student";

    public Student() {
    }

    public Student(Integer s_id, String s_xuehao, String s_name, String s_major,Integer s_status) {
        this.s_id = s_id;
        this.s_xuehao = s_xuehao;
        this.s_name = s_name;
        this.s_major = s_major;
        this.s_status=s_status;
    }

    public Student(Student student){
        this.s_id = student.getS_id();
        this.s_xuehao = student.getS_xuehao();
        this.s_name = student.getS_name();
        this.s_major = student.getS_major();
        this.s_status=student.getS_status();
    }
    public Student mapToClass(Map<String, Object> map) {
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
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
