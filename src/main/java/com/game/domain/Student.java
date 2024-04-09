package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Student extends ReflectionUtils {
    private Integer s_id;
    private String s_xuehao;
    private String s_name;
    private String s_major;
    private String s_pwd;
    private String s_tele;
    //封禁：0， 正常：1
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

    public Student(Student student){
        this.s_id = student.getS_id();
        this.s_xuehao = student.getS_xuehao();
        this.s_name = student.getS_name();
        this.s_major = student.getS_major();
        this.s_pwd = student.getS_pwd();
        this.s_tele = student.getS_tele();
        this.s_status = student.getS_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
