package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class Mentor extends ReflectionUtils {
    private Integer m_id;
    private String m_acc;
    private String m_name;
    private Integer m_status;
    private static String table_name="mentor";

    public Mentor() {
    }

    public Mentor(Integer m_id, String m_acc, String m_name,Integer m_status) {
        this.m_id = m_id;
        this.m_acc = m_acc;
        this.m_name = m_name;
        this.m_status=m_status;
    }

    public Mentor(Mentor mentor){
        this.m_id = mentor.getM_id();
        this.m_acc = mentor.getM_acc();
        this.m_name = mentor.getM_name();
        this.m_status=mentor.getM_status();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
    public Mentor mapToClass(Map<String, Object> map) {
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
        Mentor m = new Mentor(null,"123",null,null);
        System.out.println(m.getM_id());
    }
}
