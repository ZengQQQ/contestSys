package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class Team extends ReflectionUtils {
    private Integer t_id;
    private Integer captain_id;
    private String t_desc;
    private Integer t_cnum;
    private Integer t_maxnum;
    //枚举变量，只有四个状态："组队中","参赛中","已完赛","已解散"
    private Integer t_status;
    private static String table_name="team";

    public Team() {
    }

    public Team(Integer t_id, Integer captain_id, String t_desc, Integer t_maxnum, Integer t_status) {
        this.t_id = t_id;
        this.captain_id = captain_id;
        this.t_desc = t_desc;
        this.t_maxnum = t_maxnum;
        this.t_status = t_status;
    }

    public Team(Team team){
        this.t_id = team.getT_id();
        this.captain_id = team.getCaptain_id();
        this.t_desc = team.getT_desc();
        this.t_cnum = team.getT_cnum();
        this.t_maxnum = team.getT_maxnum();
        this.t_status = team.getT_status();
    }
    public Team mapToClass(Map<String, Object> map) {
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
