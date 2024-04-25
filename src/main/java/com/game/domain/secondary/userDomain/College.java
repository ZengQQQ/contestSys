package com.game.domain.secondary.userDomain;

import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;

@Data
public class College extends ReflectionUtils {
    private Integer co_id;
    private String co_name;
    private String co_acc;
    private Integer co_status;
    private static String table_name="college";

    public College() {
    }

    public College(Integer co_id, String co_name, String co_acc, Integer co_status) {
        this.co_id = co_id;
        this.co_name = co_name;
        this.co_acc = co_acc;
        this.co_status = co_status;
    }

    public College(College college) {
        this.co_id = college.getCo_id();
        this.co_name = college.getCo_name();
        this.co_acc = college.getCo_acc();
        this.co_status = college.getCo_status();
    }
    public Map<String, Object> toMap(){
        return mapFields(this);
    }
}
