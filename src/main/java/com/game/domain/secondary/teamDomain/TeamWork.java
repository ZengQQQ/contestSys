package com.game.domain.secondary.teamDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class TeamWork extends ReflectionUtils {
    private Integer t_id;
    private Integer w_id;
    private static String table_name = "team_work";

    public TeamWork() {
    }

    public TeamWork(Integer t_id, Integer w_id) {
        this.t_id = t_id;
        this.w_id = w_id;
    }
    public TeamWork(TeamWork teamWork) {
        this.t_id = teamWork.getT_id();
        this.w_id = teamWork.getW_id();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }

}
