package com.game.domain.fixDomain;

import com.game.domain.Project;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StallProjectMessageFix {
    private Integer spm_id;
    private StallFix stallFix;
    private Project project;
    private String spm_info;
    private Integer spm_pass;
    private Integer spm_status;
    private Integer spm_dct;
    private LocalDateTime spm_time;
    private Integer stall_view;
    private Integer project_view;
    private Integer join_status;

    public StallProjectMessageFix() {
    }

    public StallProjectMessageFix(Integer spm_id, StallFix stallFix, Project project, String spm_info, Integer spm_pass, Integer spm_status, Integer spm_dct, LocalDateTime spm_time, Integer stall_view, Integer project_view, Integer join_status) {
        this.spm_id = spm_id;
        this.stallFix = stallFix;
        this.project = project;
        this.spm_info = spm_info;
        this.spm_pass = spm_pass;
        this.spm_status = spm_status;
        this.spm_dct = spm_dct;
        this.spm_time = spm_time;
        this.stall_view = stall_view;
        this.project_view = project_view;
        this.join_status = join_status;
    }
}
