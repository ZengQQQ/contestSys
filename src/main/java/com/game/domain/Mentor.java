package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Mentor extends ReflectionUtils {
    private Integer m_id;
    private String m_acc;
    private String m_name;
    private String m_info;
    private String m_tele;
    private String m_mail;
    private static String table_name="mentor";

    public Mentor() {
    }

    public Mentor(Integer m_id, String m_acc, String m_name, String m_info, String m_tele, String m_mail) {
        this.m_id = m_id;
        this.m_acc = m_acc;
        this.m_name = m_name;
        this.m_info = m_info;
        this.m_tele = m_tele;
        this.m_mail = m_mail;
    }

    public Mentor(Mentor mentor){
        this.m_id = mentor.getM_id();
        this.m_acc = mentor.getM_acc();
        this.m_name = mentor.getM_name();
        this.m_info = mentor.getM_info();
        this.m_tele = mentor.getM_tele();
        this.m_mail = mentor.getM_mail();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }

    public static void main(String[] args) {
        Mentor m = new Mentor(null,"123",null,null,null,null);
        System.out.println(m.getM_id());
    }
}
