package com.game.servlet.admin;

import com.game.domain.StallMentorMessage;
import com.game.domain.StallTeamMessage;
import com.game.serve.RelationshipServe;
import com.game.utils.CurPage;
import com.game.utils.Result;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/admin/RelationStallTeamInsert")
public class RelationStallTeamInsert extends HttpServlet {
    RelationshipServe relation = new RelationshipServe();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

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
        StallTeamMessage stallTeamMessage = JSON.parseObject(jsonString, StallTeamMessage.class);

        Result<String> result = relation.insertStallTeamRelation(stallTeamMessage);
        String json = JSON.toJSONString(result);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
