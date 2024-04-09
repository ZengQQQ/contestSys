package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class TeamStudent extends ReflectionUtils {
    private Integer t_id;
    private Integer m_id;

    public TeamStudent() {
    }

    public TeamStudent(Integer t_id, Integer m_id) {
        this.t_id = t_id;
        this.m_id = m_id;
    }

    public TeamStudent(TeamStudent teamStudent) {
        this.t_id = teamStudent.getT_id();
        this.m_id = teamStudent.getM_id();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
