package com.game.domain;

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

    public Team() {
    }

    public Team(Integer t_id, Integer captain_id, String t_desc, int c_id, int t_num, String t_status) {
        this.t_id = t_id;
        this.captain_id = captain_id;
        this.t_desc = t_desc;
        this.c_id = c_id;
        this.t_num = t_num;
        this.t_status = t_status;
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
