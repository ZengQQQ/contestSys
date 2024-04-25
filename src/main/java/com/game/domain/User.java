package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data

public class User extends ReflectionUtils {
    private Integer u_id;
    private String u_pwd;
    private String u_name;
    private String u_tele;
    private String u_mail;
    private String u_wechat;
    private String u_qq;
    private Integer u_status;
    private Integer s_id;
    private Integer m_id;
    private Integer co_id;
    private static String table_name = "user";

    public User() {
    }

    public User(Integer u_id, String u_pwd, String u_name, String u_tele, String u_mail, String u_wechat, String u_qq, Integer u_status, Integer s_id, Integer m_id, Integer co_id) {
        this.u_id = u_id;
        this.u_pwd = u_pwd;
        this.u_name = u_name;
        this.u_tele = u_tele;
        this.u_mail = u_mail;
        this.u_wechat = u_wechat;
        this.u_qq = u_qq;
        this.u_status = u_status;
        this.s_id = s_id;
        this.m_id = m_id;
        this.co_id = co_id;
    }

    public User(User user) {
        this.u_id = user.getU_id();
        this.u_pwd = user.getU_pwd();
        this.u_name = user.getU_name();
        this.u_tele = user.getU_tele();
        this.u_mail = user.getU_mail();
        this.u_wechat = user.getU_wechat();
        this.u_qq = user.getU_qq();
        this.u_status = user.getU_status();
        this.s_id = user.getS_id();
        this.m_id = user.getM_id();
        this.co_id = user.getCo_id();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
