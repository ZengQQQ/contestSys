package com.game.domain.secondary.teamDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Team extends ReflectionUtils {
    private Integer t_id;
    private Integer captain_id;
    private String t_desc;
    private Integer c_id;
    private Integer t_num;
    //枚举变量，只有四个状态："组队中","参赛中","已完赛","已解散"
    private String t_status;
    private static String table_name="team";

    public Team() {
    }

    public Team(Integer t_id, Integer captain_id, String t_desc, Integer c_id, Integer t_num, String t_status) {
        this.t_id = t_id;
        this.captain_id = captain_id;
        this.t_desc = t_desc;
        this.c_id = c_id;
        this.t_num = t_num;
        this.t_status = t_status;
    }

    public Team(Team team){
        this.t_id = team.getT_id();
        this.captain_id = team.getCaptain_id();
        this.t_desc = team.getT_desc();
        this.c_id = team.getC_id();
        this.t_num = team.getT_num();
        this.t_status = team.getT_status();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
