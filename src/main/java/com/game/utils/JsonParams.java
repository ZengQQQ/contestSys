package com.game.utils;

import com.alibaba.fastjson2.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class JsonParams {

    public static Map<String, Object> getJsonParams(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonStringBuilder.append(line);
        }
        String jsonString = jsonStringBuilder.toString();
        Map<String, Object> map = JSON.parseObject(jsonString, Map.class);
        return map;
    }

}
