package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Work extends ReflectionUtils {
    private Integer w_id;
    private Integer w_apprestrict;
    private Integer c_id;
    private Integer p_id;
    private Integer tk_id;
    private Integer w_countlimit;
    private Integer w_teammin;
    private Integer w_teammax;
    private LocalDateTime w_st;
    private LocalDateTime w_ddl;
    private Integer w_status;

    public Work() {
    }

    public Work(Integer w_id, Integer w_apprestrict, Integer c_id, Integer p_id, Integer tk_id, Integer w_countlimit, Integer w_teammin, Integer w_teammax, LocalDateTime w_st, LocalDateTime w_ddl, Integer w_status) {
        this.w_id = w_id;
        this.w_apprestrict = w_apprestrict;
        this.c_id = c_id;
        this.p_id = p_id;
        this.tk_id = tk_id;
        this.w_countlimit = w_countlimit;
        this.w_teammin = w_teammin;
        this.w_teammax = w_teammax;
        this.w_st = w_st;
        this.w_ddl = w_ddl;
        this.w_status = w_status;
    }
    public Work(Work work) {
        this.w_id = work.getW_id();
        this.w_apprestrict = work.getW_apprestrict();
        this.c_id = work.getC_id();
        this.p_id = work.getP_id();
        this.tk_id = work.getTk_id();
        this.w_countlimit = work.getW_countlimit();
        this.w_teammin = work.getW_teammin();
        this.w_teammax = work.getW_teammax();
        this.w_st = work.getW_st();
        this.w_ddl = work.getW_ddl();
        this.w_status = work.getW_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
