package com.game.servlet.user.operation;

import com.alibaba.fastjson2.JSON;
import com.game.domain.TeamUserMessage;
import com.game.serve.RelationshipServe;
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

@WebServlet(value = "/user/TeamMessageApproval")
public class TeamMessageApproval extends HttpServlet {
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
        TeamUserMessage teamUserMessage = JSON.parseObject(jsonString, TeamUserMessage.class);
        TeamUserMessage tar = new TeamUserMessage();
        tar.setT_id(teamUserMessage.getT_id());
        tar.setU_acc(teamUserMessage.getU_acc());
        tar.setTsm_info(teamUserMessage.getTsm_info());
        tar.setTsm_dct(teamUserMessage.getTsm_dct());

        Result<String> result = relation.updateTeamRelation(tar);
        String json = JSON.toJSONString(result);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
