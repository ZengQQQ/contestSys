package com.game.domain;

import com.game.utils.ReflectionUtils;
import com.game.utils.Level;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import java.util.Map;

@Data
public class Project extends ReflectionUtils {

    private Integer p_id ;
    private String p_name ;
    private String p_info ;
    private Level p_level = null;
    private LocalDateTime p_st = null;
    private LocalDateTime p_ddl=null;
    private String p_url;
    private String p_img = null;
    private String p_cc = null;
    private Integer p_maxtime;
    private Integer p_resagree;
    private static String table_name="project";

    public Project() {
    }

    public Project(Integer p_id, String p_name, String p_info, Level p_level, LocalDateTime p_st, LocalDateTime p_ddl, String p_url, String p_img, String p_cc, Integer p_maxtime, Integer p_resagree) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_info = p_info;
        this.p_level = p_level;
        this.p_st = p_st;
        this.p_ddl = p_ddl;
        this.p_url = p_url;
        this.p_img = p_img;
        this.p_cc = p_cc;
        this.p_maxtime = p_maxtime;
        this.p_resagree = p_resagree;
    }

    public Project(Project p){
        this.p_id = p.getP_id();
        this.p_name = p.getP_name();
        this.p_info = p.getP_info();
        this.p_level = p.getP_level();
        this.p_st = p.getP_st();
        this.p_ddl = p.getP_ddl();
        this.p_url = p.getP_url();
        this.p_img = p.getP_img();
        this.p_cc = p.getP_cc();
        this.p_maxtime=p.getP_maxtime();
        this.p_resagree=p.getP_resagree();
    }
    public Project mapToClass(Map<String, Object> map) {
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
