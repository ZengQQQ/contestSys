package com.game.servlet.admin;

import com.game.domain.TeamUserMessage;
import com.game.serve.RelationshipServe;
import com.game.utils.Result;
import com.google.gson.Gson;

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

@WebServlet(value = "/admin/RelationTeamUserUpdate")
public class RelationTeamUserUpdate extends HttpServlet {
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
        TeamUserMessage teamUserMessage =new TeamUserMessage().mapToClass(paramMap);

        Result<String> result = relation.updateTeamRelation(teamUserMessage);
        String json = new Gson().toJson(result);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
