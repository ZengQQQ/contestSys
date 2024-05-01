package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class Captain extends ReflectionUtils {
    private Integer uc_id;
    private String  uc_acc;
    private String  uc_pwd;
    private String  uc_name;
    private String  uc_tele;
    private String  uc_mail;
    private String  uc_wechat;
    private String  uc_qq;
    private Integer  uc_status;
    private static String table_name="projectview";

    public Captain() {
    }

    public Captain(Integer uc_id, String uc_acc, String uc_pwd, String uc_name, String uc_tele, String uc_mail, String uc_wechat, String uc_qq, Integer uc_status) {
        this.uc_id = uc_id;
        this.uc_acc = uc_acc;
        this.uc_pwd = uc_pwd;
        this.uc_name = uc_name;
        this.uc_tele = uc_tele;
        this.uc_mail = uc_mail;
        this.uc_wechat = uc_wechat;
        this.uc_qq = uc_qq;
        this.uc_status = uc_status;
    }
    public Captain(User user) {
        this.uc_id = user.getU_id();
        this.uc_acc = user.getU_acc();
        this.uc_pwd = user.getU_pwd();
        this.uc_name = user.getU_name();
        this.uc_tele = user.getU_tele();
        this.uc_mail = user.getU_mail();
        this.uc_wechat = user.getU_wechat();
        this.uc_qq = user.getU_qq();
        this.uc_status = user.getU_status();
    }

    public Captain mapToClass(Map<String, Object> map) {
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
