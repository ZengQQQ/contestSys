package com.game.domain.fixDomain;

import com.game.domain.Project;
import com.game.domain.Team;
import com.game.domain.User;
import com.game.utils.ReflectionUtils;
import lombok.Data;

@Data
public class StallFix extends ReflectionUtils {
    private Integer st_id;
    private User mentor;
    private TeamFix teamFix;
    private Project project;
    private User host;
    private String st_name;
    private String st_info;
    private Integer st_status;

    public StallFix() {
    }

    public StallFix(Integer st_id, User mentor, TeamFix team, Project project, User host, String st_name, String st_info, Integer st_status) {
        this.st_id = st_id;
        this.mentor = mentor;
        this.teamFix = team;
        this.project = project;
        this.host = host;
        this.st_name = st_name;
        this.st_info = st_info;
        this.st_status = st_status;
    }
}
