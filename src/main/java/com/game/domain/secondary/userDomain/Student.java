package com.game.domain.secondary.userDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Student extends ReflectionUtils {
    private Integer s_id;
    private String s_xuehao;
    private String s_name;
    private String s_major;
    private Integer s_status;
    private static String table_name="student";

    public Student() {
    }

    public Student(Integer s_id, String s_xuehao, String s_name, String s_major,Integer s_status) {
        this.s_id = s_id;
        this.s_xuehao = s_xuehao;
        this.s_name = s_name;
        this.s_major = s_major;
        this.s_status=s_status;
    }

    public Student(Student student){
        this.s_id = student.getS_id();
        this.s_xuehao = student.getS_xuehao();
        this.s_name = student.getS_name();
        this.s_major = student.getS_major();
        this.s_status=student.getS_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
