package com.game.bean;

import lombok.Data;

import java.util.Set;
@Data
public class ConditionBean {
    private String tableName;
    private Set<String> keys;

    public ConditionBean(String tableName, Set<String> keys) {
        this.tableName = tableName;
        this.keys = keys;
    }

    public ConditionBean() {
    }
}
