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
    private Integer u_id;
    private Integer tk_status;
    private static String table_name = "task";

    public Task() {
    }

    public Task(Integer tk_id, String tk_name, String tk_info, String tk_r, String tk_cc,Integer u_id ,Integer tk_status) {
        this.tk_id = tk_id;
        this.tk_name = tk_name;
        this.tk_info = tk_info;
        this.tk_r = tk_r;
        this.tk_cc = tk_cc;
        this.u_id = u_id;
        this.tk_status = tk_status;
    }

    public Task(Task task){
        this.tk_id = task.tk_id;
        this.tk_name = task.getTk_name();
        this.tk_info = task.getTk_info();
        this.tk_r = task.getTk_r();
        this.tk_cc = task.getTk_cc();
        this.u_id = task.getU_id();
        this.tk_status = task.getTk_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
