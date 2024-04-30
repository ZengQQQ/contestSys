package com.game.domain.secondary.workDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Project extends ReflectionUtils {
    private Integer p_id;
    private String p_info;
    private Integer m_id;
    private Integer c_id;
    private LocalDateTime p_st;
    private LocalDateTime p_ddl;
    private Integer p_status;
    private static String table_name = "project";

    public Project() {
    }

    public Project(Integer p_id, String p_info, Integer m_id, Integer c_id, LocalDateTime p_st, LocalDateTime p_ddl, Integer p_status) {
        this.p_id = p_id;
        this.p_info = p_info;
        this.m_id = m_id;
        this.c_id = c_id;
        this.p_st = p_st;
        this.p_ddl = p_ddl;
        this.p_status = p_status;
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
