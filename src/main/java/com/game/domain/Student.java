package com.game.domain;

import com.game.utils.ReflectionUtils;

import java.util.Map;

public class Student extends ReflectionUtils {
    private Integer s_id;
    private String s_xuehao;
    private String s_name;
    private String s_major;
    private String s_pwd;
    private String s_tele;
    private Integer s_status;

    public Student() {
    }

    public Student(Integer s_id, String s_xuehao, String s_name, String s_major, String s_pwd, String s_tele, Integer s_status) {
        this.s_id = s_id;
        this.s_xuehao = s_xuehao;
        this.s_name = s_name;
        this.s_major = s_major;
        this.s_pwd = s_pwd;
        this.s_tele = s_tele;
        this.s_status = s_status;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public String getS_xuehao() {
        return s_xuehao;
    }

    public void setS_xuehao(String s_xuehao) {
        this.s_xuehao = s_xuehao;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_major() {
        return s_major;
    }

    public void setS_major(String s_major) {
        this.s_major = s_major;
    }

    public String getS_pwd() {
        return s_pwd;
    }

    public void setS_pwd(String s_pwd) {
        this.s_pwd = s_pwd;
    }

    public String getS_tele() {
        return s_tele;
    }

    public void setS_tele(String s_tele) {
        this.s_tele = s_tele;
    }

    public Integer getS_status() {
        return s_status;
    }

    public void setS_status(Integer s_status) {
        this.s_status = s_status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + s_id +
                ", s_xuehao='" + s_xuehao + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_major='" + s_major + '\'' +
                ", s_pwd='" + s_pwd + '\'' +
                ", s_tele='" + s_tele + '\'' +
                ", s_status=" + s_status +
                '}';
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
