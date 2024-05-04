package com.game.domain.fixDomain;

import com.game.domain.User;
import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.List;

@Data
public class TeamFix extends ReflectionUtils {
    private Integer t_id;
    private User captain;
    private List<User> members;
    private String t_name;
    private String t_info;
    private Integer t_curnum;
    private Integer t_maxnum;
    private Integer t_status;

    public TeamFix() {
    }

    public TeamFix(Integer t_id, User captain, List<User> members, String t_name, String t_info, Integer t_curnum, Integer t_maxnum, Integer t_status) {
        this.t_id = t_id;
        this.captain = captain;
        this.members = members;
        this.t_name = t_name;
        this.t_info = t_info;
        this.t_curnum = t_curnum;
        this.t_maxnum = t_maxnum;
        this.t_status = t_status;
    }
}
