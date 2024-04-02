package com.game.domain;

import com.game.utils.ReflectionUtils;

import java.util.Map;

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

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getM_acc() {
        return m_acc;
    }

    public void setM_acc(String m_acc) {
        this.m_acc = m_acc;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_pwd() {
        return m_pwd;
    }

    public void setM_pwd(String m_pwd) {
        this.m_pwd = m_pwd;
    }

    public String getM_direction() {
        return m_direction;
    }

    public void setM_direction(String m_direction) {
        this.m_direction = m_direction;
    }

    public String getM_tele() {
        return m_tele;
    }

    public void setM_tele(String m_tele) {
        this.m_tele = m_tele;
    }

    public String getM_mail() {
        return m_mail;
    }

    public void setM_mail(String m_mail) {
        this.m_mail = m_mail;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "m_id=" + m_id +
                ", m_acc='" + m_acc + '\'' +
                ", m_name='" + m_name + '\'' +
                ", m_pwd='" + m_pwd + '\'' +
                ", m_direction='" + m_direction + '\'' +
                ", m_tele='" + m_tele + '\'' +
                ", m_mail='" + m_mail + '\'' +
                '}';
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
