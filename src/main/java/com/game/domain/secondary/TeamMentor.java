package com.game.domain.secondary;

import com.game.utils.ReflectionUtils;

import java.util.Map;

public class TeamMentor extends ReflectionUtils {
    //队伍id
    private Integer t_id;
    //导师id
    private Integer m_id;

    public TeamMentor() {
    }

    public TeamMentor(Integer t_id, Integer m_id) {
        this.t_id = t_id;
        this.m_id = m_id;
    }

    public TeamMentor(TeamMentor teamMentor){
        this.t_id = teamMentor.getT_id();
        this.m_id = teamMentor.getM_id();
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    @Override
    public String toString() {
        return "TeamMentor{" +
                "t_id=" + t_id +
                ", m_id=" + m_id +
                '}';
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
