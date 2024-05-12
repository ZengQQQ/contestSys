package com.game.servlet.admin.stall;

import com.alibaba.fastjson2.JSON;
import com.game.domain.Stall;
import com.game.serve.StallService;
import com.game.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/admin/stall/update")
public class StallBan extends HttpServlet {

    public static final StallService stallService = new StallService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
        String jsonString = JSON.toJSONString(paramMap);
        Stall stall = JSON.parseObject(jsonString, Stall.class);
        Stall newStall = new Stall();
        newStall.setSt_id(stall.getSt_id());
        newStall.setSt_status(stall.getSt_status());

        Result<String> stringResult = stallService.updateStatus(newStall);
        String json = JSON.toJSONString(stringResult);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
