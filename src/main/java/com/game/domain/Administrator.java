package com.game.domain;


import com.game.utils.ReflectionUtils;

import java.util.Map;

public class Administrator extends ReflectionUtils {

    private int a_id;
    private String a_acc;
    private String a_pwd;

    public Administrator() {
    }

    public Administrator(int a_id, String a_acc, String a_pwd) {
        this.a_id = a_id;
        this.a_acc = a_acc;
        this.a_pwd = a_pwd;
    }

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

    @Override
    public String toString() {
        return "Administrator{" +
                "a_id=" + a_id +
                ", a_acc='" + a_acc + '\'' +
                ", a_pwd='" + a_pwd + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }

}
