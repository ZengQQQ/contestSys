package com.game.domain;

import com.game.utils.ReflectionUtils;
import com.game.utils.TeamStatus;
import lombok.Data;

import java.util.Map;
@Data
public class TeamApplication extends ReflectionUtils {
    private Integer ta_id;
    private Integer s_id;
    private Integer t_id;
    private String ta_reason;
    //枚举变量：“申请中”，“已拒绝”，“已同意”
    private TeamStatus ta_status;

    public TeamApplication() {
    }

    public TeamApplication(Integer ta_id, Integer s_id, Integer t_id, String ta_reason, TeamStatus ta_status) {
        this.ta_id = ta_id;
        this.s_id = s_id;
        this.t_id = t_id;
        this.ta_reason = ta_reason;
        this.ta_status = ta_status;
    }

    public TeamApplication(TeamApplication teamApplication){
        this.ta_id = teamApplication.getTa_id();
        this.s_id = teamApplication.getS_id();
        this.t_id = teamApplication.getT_id();
        this.ta_reason = teamApplication.getTa_reason();
        this.ta_status = teamApplication.getTa_status();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
