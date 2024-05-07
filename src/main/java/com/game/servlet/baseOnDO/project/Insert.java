package com.game.servlet.baseOnDO.project;

import com.alibaba.fastjson2.JSON;
import com.game.domain.Project;
import com.game.domain.fixDomain.ProjectFix;
import com.game.serve.ProjectService;
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

@WebServlet(value = "/project/add")
public class Insert extends HttpServlet {
    ProjectService projectService = new ProjectService();

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
        ProjectFix stall = JSON.parseObject(jsonString, ProjectFix.class);
        Result<String> result = projectService.insert(stall);
        resp.getWriter().write(Result.toJson(result));
    }
}
