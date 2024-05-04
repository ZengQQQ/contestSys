package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;
@Data
public class Stall extends ReflectionUtils {
    private Integer st_id;
    private String st_name;
    private String st_info;
    private String u_acc;
    private Integer st_status;
    private static String table_name = "stall";

    public Stall() {
    }

    public Stall(Integer st_id, String st_name, String st_info, String u_acc, Integer st_status) {
        this.st_id = st_id;
        this.st_name = st_name;
        this.st_info = st_info;
        this.u_acc = u_acc;
        this.st_status = st_status;
    }

    public Stall(Stall s) {
        this.st_id = s.getSt_id();
        this.u_acc = s.getU_acc();
        this.st_info = s.getSt_info();
        this.st_name=s.getSt_name();
        this.st_status = s.getSt_status();

    }

    public Stall mapToClass(Map<String, Object> map) {
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
