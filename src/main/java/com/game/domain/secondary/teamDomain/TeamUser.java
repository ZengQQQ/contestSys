package com.game.domain.secondary.teamDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class TeamUser extends ReflectionUtils {
    private Integer t_id;
    private Integer u_id;
    private static String table_name="team_user";
    public TeamUser() {
    }

    public TeamUser(Integer t_id, Integer u_id) {
        this.t_id = t_id;
        this.u_id = u_id;
    }

    public TeamUser(TeamUser teamUser){
        this.t_id = teamUser.getT_id();
        this.u_id = teamUser.getU_id();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
