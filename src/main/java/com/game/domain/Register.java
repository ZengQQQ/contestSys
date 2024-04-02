package com.game.domain;

public class Register {
    private Integer r_id;
    private String s_xuehao;

    private String r_name;

    private String r_major;

    public Register(Integer r_id, String s_xuehao, String r_name, String r_major) {
        this.r_id = r_id;
        this.s_xuehao = s_xuehao;
        this.r_name = r_name;
        this.r_major = r_major;
    }

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public String getS_xuehao() {
        return s_xuehao;
    }

    public void setS_xuehao(String s_xuehao) {
        this.s_xuehao = s_xuehao;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_major() {
        return r_major;
    }

    public void setR_major(String r_major) {
        this.r_major = r_major;
    }
}
