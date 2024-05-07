package com.game.servlet.user.operation;

import com.alibaba.fastjson2.JSON;
import com.game.domain.*;
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

@WebServlet(value = "/user/MentorTeamMessageSend")
public class MentorTeamMessageSend extends HttpServlet {
    StallService relation = new StallService();
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
        TeamMentorMessage teamMentorMessage = JSON.parseObject(jsonString, TeamMentorMessage.class);
        TeamMentorMessage tar = new TeamMentorMessage();
        tar.setM_acc(teamMentorMessage.getM_acc());
        tar.setT_id(teamMentorMessage.getT_id());
        tar.setSm_info(teamMentorMessage.getSm_info());
        tar.setTp_dct(teamMentorMessage.getTp_dct());

        Result<String> result = relation.insert(tar);
        String json = JSON.toJSONString(result);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
