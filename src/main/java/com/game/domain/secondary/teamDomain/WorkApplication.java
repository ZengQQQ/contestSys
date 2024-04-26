package com.game.domain.secondary.teamDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class WorkApplication extends ReflectionUtils {
    private Integer wa_id;
    private Integer w_id;
    private Integer t_id;
    private String wa_reason;
    private Integer wa_status;
    private static String table_name="work_application";

    public WorkApplication() {
    }

    public WorkApplication(Integer wa_id, Integer w_id, Integer t_id, String wa_reason,Integer wa_status) {
        this.wa_id = wa_id;
        this.w_id = w_id;
        this.t_id = t_id;
        this.wa_reason = wa_reason;
        this.wa_status = wa_status;
    }
    public WorkApplication(WorkApplication workApplication) {
        this.wa_id = workApplication.getWa_id();
        this.w_id = workApplication.getW_id();
        this.t_id = workApplication.getT_id();
        this.wa_reason = workApplication.getWa_reason();
        this.wa_status = workApplication.getWa_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
