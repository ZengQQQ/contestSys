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
    private String s_info;
    private String s_tele;
    private String s_mail;
    private String s_wechat;
    private String s_qq;
    //封禁：0， 正常：1
    private Integer s_status;
    private static String table_name="student";

    public Student() {
    }

    public Student(Integer s_id, String s_xuehao, String s_name, String s_major, String s_info, String s_tele, String s_mail, String s_wechat, String s_qq, Integer s_status) {
        this.s_id = s_id;
        this.s_xuehao = s_xuehao;
        this.s_name = s_name;
        this.s_major = s_major;
        this.s_info = s_info;
        this.s_tele = s_tele;
        this.s_mail = s_mail;
        this.s_wechat = s_wechat;
        this.s_qq = s_qq;
        this.s_status = s_status;
    }

    public Student(Student student){
        this.s_id = student.getS_id();
        this.s_xuehao = student.getS_xuehao();
        this.s_name = student.getS_name();
        this.s_major = student.getS_major();
        this.s_tele = student.getS_tele();
        this.s_mail = student.getS_mail();
        this.s_wechat = student.getS_wechat();
        this.s_qq=student.getS_qq();
        this.s_status = student.getS_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
