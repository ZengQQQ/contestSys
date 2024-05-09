package com.game.domain;

import lombok.Data;
import java.lang.reflect.Field;
import java.util.Map;

@Data
public class TeamProjectMessage {

    private Integer t_id;
    private Integer p_id;
    private Integer m_id;
    private Integer tp_dct;
    private Integer tp_pass;
    private String tp_info;
    private Integer tp_join;
    private Integer sm_dct;
    private Integer sm_pass;
    private String sm_info;
    private Integer sm_join;
    private Integer st_id;

    public TeamProjectMessage() {
    }

    public TeamProjectMessage(Integer t_id, Integer p_id, Integer m_id, Integer tp_dct, Integer tp_pass, String tp_info, Integer tp_join, Integer sm_dct, Integer sm_pass, String sm_info, Integer sm_join, Integer st_id) {
        this.t_id = t_id;
        this.p_id = p_id;
        this.m_id = m_id;
        this.tp_dct = tp_dct;
        this.tp_pass = tp_pass;
        this.tp_info = tp_info;
        this.tp_join = tp_join;
        this.sm_dct = sm_dct;
        this.sm_pass = sm_pass;
        this.sm_info = sm_info;
        this.sm_join = sm_join;
        this.st_id = st_id;
    }

    public TeamProjectMessage mapToClass(Map<String, Object> map) {
        Class<?> clazz = this.getClass();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 使用反射找到类的属性并设置值
            try {
                Field field = clazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(this, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

}
