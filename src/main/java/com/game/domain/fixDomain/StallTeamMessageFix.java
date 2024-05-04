package com.game.domain.fixDomain;

import com.game.domain.Stall;
import com.game.domain.Team;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StallTeamMessageFix {
    private Integer smm_id;
    private StallFix stallFix;
    private Team team;
    private String smm_info;
    private Integer smm_pass;
    private Integer smm_status;
    private Integer smm_dct;
    private LocalDateTime smm_time;
    private Integer stall_view;
    private Integer team_view;
    private Integer join_status;

    public StallTeamMessageFix() {
    }

    public StallTeamMessageFix(Integer smm_id, StallFix stallFix, Team team, String smm_info, Integer smm_pass, Integer smm_status, Integer smm_dct, LocalDateTime smm_time, Integer stall_view, Integer team_view, Integer join_status) {
        this.smm_id = smm_id;
        this.stallFix = stallFix;
        this.team = team;
        this.smm_info = smm_info;
        this.smm_pass = smm_pass;
        this.smm_status = smm_status;
        this.smm_dct = smm_dct;
        this.smm_time = smm_time;
        this.stall_view = stall_view;
        this.team_view = team_view;
        this.join_status = join_status;
    }
}
