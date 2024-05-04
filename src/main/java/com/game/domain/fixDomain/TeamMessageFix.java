package com.game.domain.fixDomain;

import com.game.domain.Team;
import com.game.domain.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TeamMessageFix {
    private Integer tsm_id;
    private TeamFix teamFix;
    private User user;
    private String tsm_info;
    private Integer tsm_status;
    private Integer tsm_pass;
    private Integer tsm_dct;
    private LocalDateTime tsm_time;

    public TeamMessageFix() {
    }

    public TeamMessageFix(Integer tsm_id, TeamFix teamFix, User user, String tsm_info, Integer tsm_status, Integer tsm_pass, Integer tsm_dct, LocalDateTime tsm_time) {
        this.tsm_id = tsm_id;
        this.teamFix = teamFix;
        this.user = user;
        this.tsm_info = tsm_info;
        this.tsm_status = tsm_status;
        this.tsm_pass = tsm_pass;
        this.tsm_dct = tsm_dct;
        this.tsm_time = tsm_time;
    }
}
