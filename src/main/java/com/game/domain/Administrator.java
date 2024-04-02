package com.game.domain;


import lombok.Data;

@Data
public class Administrator {

    private int a_id;
    private String a_acc;
    private String a_pwd;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_acc() {
        return a_acc;
    }

    public void setA_acc(String a_acc) {
        this.a_acc = a_acc;
    }

    public String getA_pwd() {
        return a_pwd;
    }

    public void setA_pwd(String a_pwd) {
        this.a_pwd = a_pwd;
    }
}
