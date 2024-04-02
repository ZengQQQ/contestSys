package com.game.domain;

public class TeamApplication {
    private Integer ta_id;
    private Integer s_id;
    private Integer t_id;
    private String ta_reason;
    //枚举变量：“申请中”，“已拒绝”，“已同意”
    private String ta_status;

    public TeamApplication(Integer ta_id, Integer s_id, Integer t_id, String ta_reason, String ta_status) {
        this.ta_id = ta_id;
        this.s_id = s_id;
        this.t_id = t_id;
        this.ta_reason = ta_reason;
        this.ta_status = ta_status;
    }

    public Integer getTa_id() {
        return ta_id;
    }

    public void setTa_id(Integer ta_id) {
        this.ta_id = ta_id;
    }

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public String getTa_reason() {
        return ta_reason;
    }

    public void setTa_reason(String ta_reason) {
        this.ta_reason = ta_reason;
    }

    public String getTa_status() {
        return ta_status;
    }

    public void setTa_status(String ta_status) {
        this.ta_status = ta_status;
    }
}
