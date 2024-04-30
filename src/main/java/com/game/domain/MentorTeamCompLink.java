package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class MentorTeamCompLink extends ReflectionUtils {
    private Integer m_id;
    private Integer t_id;
    private Integer c_id;
    private Integer pass;
    private Integer for_team;
    private Integer info;
    private Integer mtc_id;
    private static String table_name = "mentor_team_comp_link";

    public MentorTeamCompLink() {
    }

    public MentorTeamCompLink(Integer m_id, Integer t_id, Integer c_id, Integer pass, Integer for_team, Integer info, Integer mtc_id) {
        this.m_id = m_id;
        this.t_id = t_id;
        this.c_id = c_id;
        this.pass = pass;
        this.for_team = for_team;
        this.info = info;
        this.mtc_id = mtc_id;
    }

    public MentorTeamCompLink(MentorTeamCompLink m) {
        this.m_id = m.getM_id();
        this.t_id = m.getT_id();
        this.c_id = m.getC_id();
        this.pass = m.getPass();
        this.for_team = m.getFor_team();
        this.info = m.getInfo();
        this.mtc_id = m.getMtc_id();
    }

    public MentorTeamCompLink mapToClass(Map<String, Object> map) {
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
