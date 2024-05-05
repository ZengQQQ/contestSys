package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;

@Data

public class User extends ReflectionUtils {
    private Integer u_id;
    private String u_acc;
    private String u_pwd;
    private String u_name;
    private String u_tele;
    private String u_mail;
    private String u_wechat;
    private String u_qq;
    private String u_info;
    private String u_major;
    private Integer u_status;
    private Integer u_power;
    private Integer u_identity;
    private static String table_name = "user";

    public User() {
    }

    public User(Integer u_id, String u_acc, String u_pwd, String u_name, String u_tele, String u_mail, String u_wechat, String u_qq, String u_info, String u_major, Integer u_status, Integer u_power, Integer u_identity) {
        this.u_id = u_id;
        this.u_acc = u_acc;
        this.u_pwd = u_pwd;
        this.u_name = u_name;
        this.u_tele = u_tele;
        this.u_mail = u_mail;
        this.u_wechat = u_wechat;
        this.u_qq = u_qq;
        this.u_info = u_info;
        this.u_major = u_major;
        this.u_status = u_status;
        this.u_power = u_power;
        this.u_identity = u_identity;
    }

    public User(User user) {
        this.u_id = user.getU_id();
        this.u_acc = user.getU_acc();
        this.u_pwd = user.getU_pwd();
        this.u_name = user.getU_name();
        this.u_tele = user.getU_tele();
        this.u_mail = user.getU_mail();
        this.u_wechat = user.getU_wechat();
        this.u_qq = user.getU_qq();
        this.u_info = user.getU_info();
        this.u_major = user.getU_major();
        this.u_status = user.getU_status();
        this.u_power = user.getU_power();
        this.u_identity=user.getU_identity();
    }


    public User mapToClass(Map<String, Object> map) {
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

    public Map<String,String> toStringMap(){return mapFieldsString(this);}
}
