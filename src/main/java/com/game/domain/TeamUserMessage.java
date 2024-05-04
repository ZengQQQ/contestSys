package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class TeamUserMessage extends ReflectionUtils {
    private Integer tsm_id;
    private Integer t_id;
    private String u_acc;
    private String tsm_info;
    private Integer team_view;
    private Integer user_view;
    private Integer tsm_status;
    private Integer tsm_pass;
    private Integer join_status;
    private Integer tsm_dct;
    private LocalDateTime tsm_time;
    private static String table_name = "team_user_message";

    public TeamUserMessage() {
    }

    public TeamUserMessage(Integer tsm_id, Integer t_id, String u_acc, String tsm_info, Integer team_view, Integer user_view, Integer tsm_status, Integer tsm_pass, Integer join_status, Integer tsm_dct, LocalDateTime tsm_time) {
        this.tsm_id = tsm_id;
        this.t_id = t_id;
        this.u_acc = u_acc;
        this.tsm_info = tsm_info;
        this.team_view = team_view;
        this.user_view = user_view;
        this.tsm_status = tsm_status;
        this.tsm_pass = tsm_pass;
        this.join_status = join_status;
        this.tsm_dct = tsm_dct;
        this.tsm_time = tsm_time;
    }

    public TeamUserMessage(TeamUserMessage t) {
        this.tsm_id = t.getTsm_id();
        this.t_id = t.getT_id();
        this.u_acc = t.getU_acc();
        this.tsm_info = t.getTsm_info();
        this.team_view = t.getTeam_view();
        this.user_view = t.getUser_view();
        this.tsm_status = t.getTsm_status();
        this.tsm_pass = t.getTsm_pass();
        this.join_status = t.getJoin_status();
        this.tsm_dct = t.getTsm_dct();
        this.tsm_time = t.getTsm_time();
    }
    public TeamUserMessage mapToClass(Map<String, Object> map) {
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
