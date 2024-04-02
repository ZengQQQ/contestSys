package com.game.domain;

public class Team {
    private Integer t_id;
    private Integer captain_id;
    private String t_desc;
    private int c_id;
    private int t_num;
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

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getCaptain_id() {
        return captain_id;
    }

    public void setCaptain_id(Integer captain_id) {
        this.captain_id = captain_id;
    }

    public String getT_desc() {
        return t_desc;
    }

    public void setT_desc(String t_desc) {
        this.t_desc = t_desc;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getT_num() {
        return t_num;
    }

    public void setT_num(int t_num) {
        this.t_num = t_num;
    }

    public String getT_status() {
        return t_status;
    }

    public void setT_status(String t_status) {
        this.t_status = t_status;
    }

    @Override
    public String toString() {
        return "Team{" +
                "t_id=" + t_id +
                ", captain_id=" + captain_id +
                ", t_desc='" + t_desc + '\'' +
                ", c_id=" + c_id +
                ", t_num=" + t_num +
                ", t_status='" + t_status + '\'' +
                '}';
    }
}
