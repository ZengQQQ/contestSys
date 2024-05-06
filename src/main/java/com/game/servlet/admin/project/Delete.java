package com.game.servlet.admin.project;

import com.game.domain.Project;
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


@WebServlet(value = "/admin/project/delete")
public class Delete extends HttpServlet {
    public static final ProjectService projectService = new ProjectService();

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
        // 将JSON字符串转换为User对象
        Project project = new Project().mapToClass(paramMap);

        Result<String> result = projectService.delete(project);
        resp.getWriter().write(Result.toJson(result));
    }
}
