package com.game.domain;


import com.game.utils.ReflectionUtils;
import lombok.Data;

import java.util.Map;
@Data
public class Administrator extends ReflectionUtils {

    private int a_id;
    private String a_acc;
    private String a_pwd;

    public Administrator() {
    }

    public Administrator(int a_id, String a_acc, String a_pwd) {
        this.a_id = a_id;
        this.a_acc = a_acc;
        this.a_pwd = a_pwd;
    }


    public Map<String, Object> toMap(){
        return mapFields(this);
    }

}
