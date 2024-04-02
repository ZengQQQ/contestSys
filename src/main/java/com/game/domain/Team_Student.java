package com.game.domain;

import com.game.utils.ReflectionUtils;

import java.util.Map;

public class Team_Student extends ReflectionUtils {
    private Integer t_id;
    private Integer m_id;

    public Team_Student() {
    }

    public Team_Student(Integer t_id, Integer m_id) {
        this.t_id = t_id;
        this.m_id = m_id;
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
        return "Team_Student{" +
                "t_id=" + t_id +
                ", m_id=" + m_id +
                '}';
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
