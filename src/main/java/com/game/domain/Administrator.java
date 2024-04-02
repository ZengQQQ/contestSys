package com.game.domain;


import lombok.Data;
@Data
public class Administrator {

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

    @Override
    public String toString() {
        return "Administrator{" +
                "a_id=" + a_id +
                ", a_acc='" + a_acc + '\'' +
                ", a_pwd='" + a_pwd + '\'' +
                '}';
    }
}
