package com.game.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JwtData {
    private String username;
    private String password;
    private Role role = null;


    public JwtData(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("role", role.name());
        return map;
    }


}
