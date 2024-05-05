package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class StallTeamMessage extends ReflectionUtils {
    private Integer stm_id;
    private Integer st_id;
    private Integer t_id;
    private String stm_info;
    private Integer stall_view;
    private Integer team_view;
    private Integer stm_pass;
    private Integer stm_status;
    private Integer join_status;
    private Integer stm_dct;
    private LocalDateTime stm_time;
    private static String table_name = "stall_team_message";

    public StallTeamMessage() {
    }

    public StallTeamMessage(Integer stm_id, Integer st_id, Integer t_id, String stm_info, Integer stall_view, Integer team_view, Integer stm_pass, Integer stm_status, Integer join_status, Integer stm_dct, LocalDateTime stm_time) {
        this.stm_id = stm_id;
        this.st_id = st_id;
        this.t_id = t_id;
        this.stm_info = stm_info;
        this.stall_view = stall_view;
        this.team_view = team_view;
        this.stm_pass = stm_pass;
        this.stm_status = stm_status;
        this.join_status = join_status;
        this.stm_dct = stm_dct;
        this.stm_time = stm_time;
    }

    public StallTeamMessage(StallTeamMessage s) {
        this.stm_id = s.getStm_id();
        this.st_id = s.getSt_id();
        this.t_id = s.getT_id();
        this.stm_info = s.getStm_info();
        this.stall_view = s.getStall_view();
        this.team_view = s.getTeam_view();
        this.stm_pass = s.getStm_pass();
        this.stm_status = s.getStm_status();
        this.join_status = s.getJoin_status();
        this.stm_dct = s.getStm_dct();
        this.stm_time = s.getStm_time();
    }

    public StallTeamMessage mapToClass(Map<String, Object> map) {
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
