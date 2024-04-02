package com.game.domain;

import com.game.domain.tool.ReflectionUtils;
import com.game.utils.Level;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.Map;

@Data
public class Competition extends ReflectionUtils {

    /**
     * 竞赛信息实体类
     */


    private Integer c_id = null;
    private String c_name = null;
    private Level c_level = null;
    private LocalDateTime c_sign_t = null;
    private String c_url = null;
    private String c_img = null;

    public Competition() {
    }

    public Competition(Integer c_id, String c_name, Level c_level, LocalDateTime c_sign_t, String c_url, String c_img) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_level = c_level;
        this.c_sign_t = c_sign_t;
        this.c_url = c_url;
        this.c_img = c_img;
    }

    public Integer getC_id() {
        return c_id;
    }

    public void setC_id(Integer c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Level getC_level() {
        return c_level;
    }

    public void setC_level(Level c_level) {
        this.c_level = c_level;
    }

    public LocalDateTime getC_sign_t() {
        return c_sign_t;
    }

    public void setC_sign_t(LocalDateTime c_sign_t) {
        this.c_sign_t = c_sign_t;
    }

    public String getC_url() {
        return c_url;
    }

    public void setC_url(String c_url) {
        this.c_url = c_url;
    }

    public String getC_img() {
        return c_img;
    }

    public void setC_img(String c_img) {
        this.c_img = c_img;
    }

    public Map<String, Object> toMap() {
        return mapFields(this);
    }
}
