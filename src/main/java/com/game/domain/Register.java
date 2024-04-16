package com.game.domain;

import com.game.utils.ReflectionUtils;

import java.util.Map;
import lombok.Data;
@Data
public class Register extends ReflectionUtils {
    private Integer r_id;
    private String s_xuehao;
    private String r_name;
    private String r_major;
    private static String table_name="register";


    public Register(Integer r_id, String s_xuehao, String r_name, String r_major) {
        this.r_id = r_id;
        this.s_xuehao = s_xuehao;
        this.r_name = r_name;
        this.r_major = r_major;
    }

    public Register(){}

    public Register(Register register){
        this.r_id = register.getR_id();
        this.s_xuehao = register.getS_xuehao();
        this.r_name = register.getR_name();
        this.r_major = register.getR_major();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
