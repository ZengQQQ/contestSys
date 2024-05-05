package com.game.domain.fixDomain;

import com.game.domain.Stall;
import com.game.domain.Team;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StallTeamMessageFix {
    private Integer stm_id;
    private StallFix stallFix;
    private Team team;
    private String stm_info;
    private Integer stm_pass;
    private Integer stm_status;
    private Integer stm_dct;
    private LocalDateTime stm_time;
    private Integer stall_view;
    private Integer team_view;
    private Integer join_status;

    public StallTeamMessageFix() {
    }

    public StallTeamMessageFix(Integer stm_id, StallFix stallFix, Team team, String stm_info, Integer stm_pass, Integer stm_status, Integer stm_dct, LocalDateTime stm_time, Integer stall_view, Integer team_view, Integer join_status) {
        this.stm_id = stm_id;
        this.stallFix = stallFix;
        this.team = team;
        this.stm_info = stm_info;
        this.stm_pass = stm_pass;
        this.stm_status = stm_status;
        this.stm_dct = stm_dct;
        this.stm_time = stm_time;
        this.stall_view = stall_view;
        this.team_view = team_view;
        this.join_status = join_status;
    }
}
