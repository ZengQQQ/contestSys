package com.game.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JwtData {
    private String username;
    private String password;
    private identity identity = null;


    public JwtData(String username, String password, identity identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("identity", identity.name());
        return map;
    }


}
