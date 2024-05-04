package com.game.domain.fixDomain;

import com.game.domain.User;
import com.game.utils.Level;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ProjectFix {
    private Integer p_id ;
    private String p_name ;
    private String p_info ;
    private Level p_level = null;
    private LocalDateTime p_st = null;
    private LocalDateTime p_ddl=null;
    private String p_url;
    private String p_img = null;
    private String p_cc = null;
    private Integer p_maxtime;
    private Integer p_resagree;
    private User editor;

    public ProjectFix() {
    }

    public ProjectFix(Integer p_id, String p_name, String p_info, Level p_level, LocalDateTime p_st, LocalDateTime p_ddl, String p_url, String p_img, String p_cc, Integer p_maxtime, Integer p_resagree, User editor) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_info = p_info;
        this.p_level = p_level;
        this.p_st = p_st;
        this.p_ddl = p_ddl;
        this.p_url = p_url;
        this.p_img = p_img;
        this.p_cc = p_cc;
        this.p_maxtime = p_maxtime;
        this.p_resagree = p_resagree;
        this.editor = editor;
    }
}
