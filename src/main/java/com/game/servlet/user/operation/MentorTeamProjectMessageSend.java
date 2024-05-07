package com.game.servlet.user.operation;

import com.alibaba.fastjson2.JSON;
import com.game.domain.Mentor;
import com.game.domain.StallMentorMessage;
import com.game.domain.TeamUserMessage;
import com.game.serve.RelationshipServe;
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

@WebServlet(value = "/user/MentorMessageSend")
public class MentorTeamProjectMessageSend extends HttpServlet {
    StallService stallService = new StallService();
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
        StallMentorMessage stallMentorMessage = JSON.parseObject(jsonString, StallMentorMessage.class);
        StallMentorMessage tar = new StallMentorMessage();
        tar.setU_acc(stallMentorMessage.getU_acc());
        tar.setSt_id(stallMentorMessage.getSt_id());
        tar.setSmm_info(stallMentorMessage.getSmm_info());
        Mentor mentor = new Mentor();
        mentor.setM_acc(tar.getU_acc());
        Result<String> result = stallService.addMentor(tar,mentor);
        String json = JSON.toJSONString(result);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
