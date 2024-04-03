package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Team_Student extends ReflectionUtils {
    private Integer t_id;
    private Integer m_id;

    public Team_Student() {
    }

    public Team_Student(Integer t_id, Integer m_id) {
        this.t_id = t_id;
        this.m_id = m_id;
    }

    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
