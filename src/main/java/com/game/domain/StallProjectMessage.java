package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class StallProjectMessage extends ReflectionUtils {
    private Integer smm_id;
    private Integer st_id;
    private Integer p_id;
    private String smm_info;
    private Integer stall_view;
    private Integer project_view;
    private Integer smm_pass;
    private Integer smm_status;
    private Integer join_status;
    private Integer smm_dct;
    private LocalDateTime smm_time;
    private static String table_name = "stall_task_message";

    public StallProjectMessage() {
    }

    public StallProjectMessage(Integer smm_id, Integer st_id, Integer p_id, String smm_info, Integer stall_view, Integer project_view, Integer smm_pass, Integer smm_status, Integer join_status, Integer smm_dct, LocalDateTime smm_time) {
        this.smm_id = smm_id;
        this.st_id = st_id;
        this.p_id = p_id;
        this.smm_info = smm_info;
        this.stall_view = stall_view;
        this.project_view = project_view;
        this.smm_pass = smm_pass;
        this.smm_status = smm_status;
        this.join_status = join_status;
        this.smm_dct = smm_dct;
        this.smm_time = smm_time;
    }

    public StallProjectMessage(StallProjectMessage s) {
        this.smm_id = s.getSmm_id();
        this.st_id = s.getSt_id();
        this.p_id = s.getP_id();
        this.smm_info = s.getSmm_info();
        this.stall_view =s.getStall_view();
        this.project_view = s.getProject_view();
        this.smm_pass = s.getSmm_pass();
        this.smm_status = s.getSmm_status();
        this.join_status = s.getJoin_status();
        this.smm_dct = s.getSmm_dct();
        this.smm_time = s.getSmm_time();
    }

    public StallProjectMessage mapToClass(Map<String, Object> map) {
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
