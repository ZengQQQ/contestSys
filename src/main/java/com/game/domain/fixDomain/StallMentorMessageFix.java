package com.game.domain.fixDomain;

import com.game.domain.Stall;
import com.game.domain.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StallMentorMessageFix {
    private Integer smm_id;
    private StallFix stallFix;
    private User mentor;
    private String smm_info;
    private Integer smm_pass;
    private Integer smm_status;
    private Integer smm_dct;
    private LocalDateTime smm_time;
    private Integer stall_view;
    private Integer mentor_view;
    private Integer join_status;

    public StallMentorMessageFix() {
    }

    public StallMentorMessageFix(Integer smm_id, StallFix stallFix, User mentor, String smm_info, Integer smm_pass, Integer smm_status, Integer smm_dct, LocalDateTime smm_time, Integer stall_view, Integer mentor_view, Integer join_status) {
        this.smm_id = smm_id;
        this.stallFix = stallFix;
        this.mentor = mentor;
        this.smm_info = smm_info;
        this.smm_pass = smm_pass;
        this.smm_status = smm_status;
        this.smm_dct = smm_dct;
        this.smm_time = smm_time;
        this.stall_view = stall_view;
        this.mentor_view = mentor_view;
        this.join_status = join_status;
    }
}
