package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class StallProjectMessage extends ReflectionUtils {
    private Integer spm_id;
    private Integer st_id;
    private Integer p_id;
    private String spm_info;
    private Integer stall_view;
    private Integer project_view;
    private Integer spm_pass;
    private Integer spm_status;
    private Integer join_status;
    private Integer spm_dct;
    private LocalDateTime spm_time;
    private static String table_name = "stall_project_message";

    public StallProjectMessage() {
    }

    public StallProjectMessage(Integer spm_id, Integer st_id, Integer p_id, String spm_info, Integer stall_view, Integer project_view, Integer spm_pass, Integer spm_status, Integer join_status, Integer spm_dct, LocalDateTime spm_time) {
        this.spm_id = spm_id;
        this.st_id = st_id;
        this.p_id = p_id;
        this.spm_info = spm_info;
        this.stall_view = stall_view;
        this.project_view = project_view;
        this.spm_pass = spm_pass;
        this.spm_status = spm_status;
        this.join_status = join_status;
        this.spm_dct = spm_dct;
        this.spm_time = spm_time;
    }

    public StallProjectMessage(StallProjectMessage s) {
        this.spm_id = s.getSpm_id();
        this.st_id = s.getSt_id();
        this.p_id = s.getP_id();
        this.spm_info = s.getSpm_info();
        this.stall_view =s.getStall_view();
        this.project_view = s.getProject_view();
        this.spm_pass = s.getSpm_pass();
        this.spm_status = s.getSpm_status();
        this.join_status = s.getJoin_status();
        this.spm_dct = s.getSpm_dct();
        this.spm_time = s.getSpm_time();
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
