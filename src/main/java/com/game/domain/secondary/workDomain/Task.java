package com.game.domain.secondary.workDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class Task extends ReflectionUtils {
    private Integer tk_id;
    private String tk_name;
    private String tk_info;
    private String tk_r;
    private String tk_cc;
    private Integer s_id;
    private Integer m_id;
    private Integer un_id;
    private static String table_name = "task";

    public Task() {
    }

    public Task(Integer tk_id, String tk_name, String tk_info, String tk_r, String tk_cc, Integer s_id, Integer m_id, Integer un_id) {
        this.tk_id = tk_id;
        this.tk_name = tk_name;
        this.tk_info = tk_info;
        this.tk_r = tk_r;
        this.tk_cc = tk_cc;
        this.s_id = s_id;
        this.m_id = m_id;
        this.un_id = un_id;
    }

    public Task(Task task){
        this.tk_id = task.tk_id;
        this.tk_name = task.getTk_name();
        this.tk_info = task.getTk_info();
        this.tk_r = task.getTk_r();
        this.tk_cc = task.getTk_cc();
        this.s_id = task.getS_id();
        this.m_id = task.getM_id();
        this.un_id = task.getUn_id();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
