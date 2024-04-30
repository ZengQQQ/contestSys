package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class StudentTeamLink extends ReflectionUtils {
    private Integer s_id;
    private Integer t_id;
    private Integer pass;
    private Integer invite;
    private String info;
    private Integer st_id;
    private static String table_name="student_team_link";

    public StudentTeamLink() {
    }

    public StudentTeamLink(Integer s_id, Integer t_id, Integer pass, Integer invite, String info, Integer st_id) {
        this.s_id = s_id;
        this.t_id = t_id;
        this.pass = pass;
        this.invite = invite;
        this.info = info;
        this.st_id = st_id;
    }

    public StudentTeamLink(StudentTeamLink s) {
        this.s_id = s.getS_id();
        this.t_id = s.getT_id();
        this.pass = s.getPass();
        this.invite = s.getInvite();
        this.info = s.getInfo();
        this.st_id = s.getSt_id();
    }

    public StudentTeamLink mapToClass(Map<String, Object> map) {
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
