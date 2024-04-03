package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Mentor extends ReflectionUtils {
    private Integer m_id;
    private String m_acc;
    private String m_name;
    private String m_pwd;
    private String m_direction;
    private String m_tele;
    private String m_mail;

    public Mentor() {
    }

    public Mentor(Integer m_id, String m_acc, String m_name, String m_pwd, String m_direction, String m_tele, String m_mail) {
        this.m_id = m_id;
        this.m_acc = m_acc;
        this.m_name = m_name;
        this.m_pwd = m_pwd;
        this.m_direction = m_direction;
        this.m_tele = m_tele;
        this.m_mail = m_mail;
    }


    public Map<String, Object> toMap(){
        return mapFields(this);
    }

    public static void main(String[] args) {
        Mentor m = new Mentor(null,"123",null,null,null,null,null);
        System.out.println(m.getM_id());
    }
}
