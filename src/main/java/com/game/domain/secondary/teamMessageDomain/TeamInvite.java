package com.game.domain.secondary.teamMessageDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class TeamInvite extends ReflectionUtils {
    private Integer ti_id;
    private Integer u_id;
    private Integer t_id;
    private String ti_reason;
    private Integer ti_status;
    private static String table_name="team_invite";
    public TeamInvite() {
    }

    public TeamInvite(Integer ti_id, Integer u_id, Integer t_id, String ti_reason, Integer ti_status) {
        this.ti_id = ti_id;
        this.u_id = u_id;
        this.t_id = t_id;
        this.ti_reason = ti_reason;
        this.ti_status = ti_status;
    }
    public TeamInvite(TeamInvite teamInvite) {
        this.ti_id = teamInvite.getTi_id();
        this.u_id = teamInvite.getU_id();
        this.t_id = teamInvite.getT_id();
        this.ti_reason = teamInvite.getTi_reason();
        this.ti_status = teamInvite.getTi_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}