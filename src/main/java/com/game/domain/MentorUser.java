package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Map;
@Data
public class MentorUser extends ReflectionUtils {
    private Integer m_id;
    private String  m_acc;
    private String  m_pwd;
    private String  m_name;
    private String  m_tele;
    private String  m_mail;
    private String  m_wechat;
    private String  m_qq;
    private Integer  m_status;
    private static String table_name="projectview";

    public MentorUser() {
    }

    public MentorUser(Integer m_id,String  m_acc, String  m_pwd, String  m_name, String  m_tele, String  m_mail, String  m_wechat, String  m_qq, Integer  m_status) {
        this. m_id =  m_id;
        this. m_acc =  m_acc;
        this. m_pwd =  m_pwd;
        this. m_name =  m_name;
        this. m_tele =  m_tele;
        this. m_mail =  m_mail;
        this. m_wechat =  m_wechat;
        this. m_qq =  m_qq;
        this.m_status = m_status;
    }


    public MentorUser(User user) {
        this.m_id = user.getU_id();
        this.m_acc = user.getU_acc();
        this.m_pwd = user.getU_pwd();
        this.m_name = user.getU_name();
        this.m_tele = user.getU_tele();
        this.m_mail = user.getU_mail();
        this.m_wechat = user.getU_wechat();
        this.m_qq = user.getU_qq();
        this.m_status = user.getU_status();
    }
    public MentorUser mapToClass(Map<String, Object> map) {
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
