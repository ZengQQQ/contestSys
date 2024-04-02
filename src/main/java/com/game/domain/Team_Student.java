package com.game.domain;

public class Team_Student {
    private Integer t_id;
    private Integer m_id;

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
}
