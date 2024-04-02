package com.game.domain;

import lombok.Data;

@Data
public class Register {
    private Integer r_id;
    private String s_xuehao;

    private String r_name;

    private String r_major;

    @Override
    public String toString() {
        return "Register{" +
                "r_id=" + r_id +
                ", s_xuehao='" + s_xuehao + '\'' +
                ", r_name='" + r_name + '\'' +
                ", r_major='" + r_major + '\'' +
                '}';
    }
}
