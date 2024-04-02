package com.game.domain;

public class Team_Mentor {
    //队伍id
    private Integer t_id;
    //导师id
    private Integer m_id;

    public Team_Mentor() {
    }

    public Team_Mentor(Integer t_id, Integer m_id) {
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
        return "Team_Mentor{" +
                "t_id=" + t_id +
                ", m_id=" + m_id +
                '}';
    }
}
