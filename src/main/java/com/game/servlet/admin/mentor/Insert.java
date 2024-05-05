package com.game.servlet.admin.mentor;

import com.game.bean.PageBean;
import com.game.dao.ProjectDao;
import com.game.domain.Mentor;
import com.game.domain.Project;
import com.game.serve.ProjectService;
import com.game.serve.QueryControlServe;
import com.game.utils.Result;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Insert extends HttpServlet {
    ProjectService query = new ProjectService();

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
        Integer currentPage = Integer.parseInt((String) paramMap.get("currentPage"));
        // 将JSON字符串转换为User对象
//        Project project = new Project().mapToClass(paramMap);
//        Result<PageBean<Project>> responseData = query.queryPage(currentPage, project);
//        String json = new Gson().toJson(responseData);
//        resp.setContentType("application/json");
//        resp.getWriter().write(json);
//        resp.getWriter().flush();
    }
}
