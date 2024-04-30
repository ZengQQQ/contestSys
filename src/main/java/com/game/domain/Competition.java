package com.game.domain;

import com.game.utils.ReflectionUtils;
import com.game.utils.Level;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import java.util.Map;

@Data
public class Competition extends ReflectionUtils {

    /**
     * 竞赛信息实体类
     */

    private Integer c_id = null;
    private String c_name = null;
    private Level c_level = null;
    private LocalDateTime c_sign_t = null;
    private String c_url = null;
    private String c_img = null;
    private LocalDateTime c_st=null;
    private LocalDateTime c_ddl=null;
    private static String table_name="competition";

    public Competition() {
    }

    public Competition(Integer c_id, String c_name, Level c_level, LocalDateTime c_sign_t, String c_url, String c_img, LocalDateTime c_st, LocalDateTime c_ddl) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_level = c_level;
        this.c_sign_t = c_sign_t;
        this.c_url = c_url;
        this.c_img = c_img;
        this.c_st = c_st;
        this.c_ddl = c_ddl;
    }

    public Competition(Competition competition){
        this.c_id = competition.getC_id();;
        this.c_name = competition.getC_name();
        this.c_level = competition.getC_level();
        this.c_sign_t = competition.getC_sign_t();
        this.c_url = competition.getC_url();
        this.c_img = competition.getC_img();
        this.c_st = competition.getC_st();
        this.c_ddl = competition.getC_ddl();
    }
    public Competition mapToClass(Map<String, Object> map) {
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

    public Map<String, Object> toMap() {
        return mapFields(this);
    }

}
