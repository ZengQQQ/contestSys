package com.game.domain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class College extends ReflectionUtils {
    private Integer co_id;
    private String co_name;
    private String co_info;

    public College() {
    }

    public College(Integer co_id, String co_name, String co_info) {
        this.co_id = co_id;
        this.co_name = co_name;
        this.co_info = co_info;
    }

    public College(College college) {
        this.co_id = college.getCo_id();
        this.co_name = college.getCo_name();
        this.co_info = college.getCo_info();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
