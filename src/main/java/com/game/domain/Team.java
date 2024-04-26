package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Team extends ReflectionUtils {
    private Integer t_id;
    private Integer captain_id;
    private String t_desc;
    private Integer t_cnum;
    private Integer t_maxnum;
    //枚举变量，只有四个状态："组队中","参赛中","已完赛","已解散"
    private Integer t_status;
    private static String table_name="team";

    public Team() {
    }

    public Team(Integer t_id, Integer captain_id, String t_desc, Integer t_cnum, Integer t_maxnum, Integer t_status) {
        this.t_id = t_id;
        this.captain_id = captain_id;
        this.t_desc = t_desc;
        this.t_maxnum = t_maxnum;
        this.t_status = t_status;
    }

    public Team(Team team){
        this.t_id = team.getT_id();
        this.captain_id = team.getCaptain_id();
        this.t_desc = team.getT_desc();
        this.t_cnum = team.getT_cnum();
        this.t_maxnum = team.getT_maxnum();
        this.t_status = team.getT_status();
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
