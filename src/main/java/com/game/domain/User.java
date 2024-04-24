package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data

public class User extends ReflectionUtils {
    private Integer u_id;
    private Integer u_pwd;
    private Integer u_name;
    private Integer u_status;
    private Integer s_id;
    private Integer m_id;
    private Integer co_id;
    private static String table_name = "user";

    public User() {
    }

    public User(Integer u_id, Integer u_pwd, Integer u_name, Integer u_status, Integer s_id, Integer m_id, Integer co_id) {
        this.u_id = u_id;
        this.u_pwd = u_pwd;
        this.u_name = u_name;
        this.u_status = u_status;
        this.s_id = s_id;
        this.m_id = m_id;
        this.co_id = co_id;
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
