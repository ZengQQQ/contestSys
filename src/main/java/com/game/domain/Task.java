package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;

@Data
public class Task extends ReflectionUtils {
    private Integer tk_id;
    private String tk_name;
    private String tk_info;
    private String tk_r;
    private String tk_cc;
    private Integer u_id;
    private Integer tk_status;
    private static String table_name = "task";

    public Task() {
    }

    public Task(Integer tk_id, String tk_name, String tk_info, String tk_r, String tk_cc,Integer u_id ,Integer tk_status) {
        this.tk_id = tk_id;
        this.tk_name = tk_name;
        this.tk_info = tk_info;
        this.tk_r = tk_r;
        this.tk_cc = tk_cc;
        this.u_id = u_id;
        this.tk_status = tk_status;
    }

    public Task(Task task){
        this.tk_id = task.tk_id;
        this.tk_name = task.getTk_name();
        this.tk_info = task.getTk_info();
        this.tk_r = task.getTk_r();
        this.tk_cc = task.getTk_cc();
        this.u_id = task.getU_id();
        this.tk_status = task.getTk_status();
    }
    public Task mapToClass(Map<String, Object> map) {
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
