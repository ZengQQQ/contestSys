package com.game.domain.fixDomain;

import com.game.domain.Project;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StallProjectMessageFix {
    private Integer smm_id;
    private StallFix stallFix;
    private Project project;
    private String smm_info;
    private Integer smm_pass;
    private Integer smm_status;
    private Integer smm_dct;
    private LocalDateTime smm_time;
    private Integer stall_view;
    private Integer project_view;
    private Integer join_status;

    public StallProjectMessageFix() {
    }

    public StallProjectMessageFix(Integer smm_id, StallFix stallFix, Project project, String smm_info, Integer smm_pass, Integer smm_status, Integer smm_dct, LocalDateTime smm_time, Integer stall_view, Integer project_view, Integer join_status) {
        this.smm_id = smm_id;
        this.stallFix = stallFix;
        this.project = project;
        this.smm_info = smm_info;
        this.smm_pass = smm_pass;
        this.smm_status = smm_status;
        this.smm_dct = smm_dct;
        this.smm_time = smm_time;
        this.stall_view = stall_view;
        this.project_view = project_view;
        this.join_status = join_status;
    }
}
