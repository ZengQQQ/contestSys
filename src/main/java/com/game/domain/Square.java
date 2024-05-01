package com.game.domain;

import com.game.utils.Level;
import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class Square extends ReflectionUtils {
    //比赛
    private Integer c_id = null;
    private String c_name = null;
    private Level c_level = null;
    private LocalDateTime c_sign_t = null;
    private String c_url = null;
    private String c_img = null;
    private LocalDateTime c_st=null;
    private LocalDateTime c_ddl=null;
    //队伍
    private Integer t_id;
    private Integer captain_id;
    private String t_desc;
    private Integer t_cnum;
    private Integer t_maxnum;
    //枚举变量，只有四个状态："组队中","参赛中","已完赛","已解散"
    private Integer t_status;
    //队长用户
    private Integer uc_id;
    private String  uc_acc;
    private String  uc_pwd;
    private String  uc_name;
    private String  uc_tele;
    private String  uc_mail;
    private String  uc_wechat;
    private String  uc_qq;
    private Integer  uc_status;
    //导师用户
    private Integer m_id;
    private String  m_acc;
    private String  m_pwd;
    private String  m_name;
    private String  m_tele;
    private String  m_mail;
    private String  m_wechat;
    private String  m_qq;
    private Integer  m_status;
    //mentor team competition
    private Integer pass;
    private Integer for_team;
    private Integer info;
    private Integer mtc_id;
    private LocalDateTime mtc_st;
    private LocalDateTime mtc_ddl;
    //成员学生
    private static String table_name="projectview";

    public Square() {
    }

    public Square(Integer c_id, String c_name, Level c_level, LocalDateTime c_sign_t, String c_url, String c_img, LocalDateTime c_st, LocalDateTime c_ddl, Integer t_id, Integer captain_id, String t_desc, Integer t_cnum, Integer t_maxnum, Integer t_status, Integer uc_id, String uc_acc, String uc_pwd, String uc_name, String uc_tele, String uc_mail, String uc_wechat, String uc_qq, Integer uc_status, Integer m_id, String m_acc, String m_pwd, String m_name, String m_tele, String m_mail, String m_wechat, String m_qq, Integer m_status, Integer pass, Integer for_team, Integer info, Integer mtc_id, LocalDateTime mtc_st, LocalDateTime mtc_ddl) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_level = c_level;
        this.c_sign_t = c_sign_t;
        this.c_url = c_url;
        this.c_img = c_img;
        this.c_st = c_st;
        this.c_ddl = c_ddl;
        this.t_id = t_id;
        this.captain_id = captain_id;
        this.t_desc = t_desc;
        this.t_cnum = t_cnum;
        this.t_maxnum = t_maxnum;
        this.t_status = t_status;
        this.uc_id = uc_id;
        this.uc_acc = uc_acc;
        this.uc_pwd = uc_pwd;
        this.uc_name = uc_name;
        this.uc_tele = uc_tele;
        this.uc_mail = uc_mail;
        this.uc_wechat = uc_wechat;
        this.uc_qq = uc_qq;
        this.uc_status = uc_status;
        this.m_id = m_id;
        this.m_acc = m_acc;
        this.m_pwd = m_pwd;
        this.m_name = m_name;
        this.m_tele = m_tele;
        this.m_mail = m_mail;
        this.m_wechat = m_wechat;
        this.m_qq = m_qq;
        this.m_status = m_status;
        this.pass = pass;
        this.for_team = for_team;
        this.info = info;
        this.mtc_id = mtc_id;
        this.mtc_st = mtc_st;
        this.mtc_ddl = mtc_ddl;

    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
