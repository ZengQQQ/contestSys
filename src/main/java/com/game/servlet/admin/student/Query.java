package com.game.servlet.admin.student;


import com.game.bean.PageBean;
import com.game.domain.Project;
import com.game.domain.Student;
import com.game.domain.fixDomain.ProjectFix;
import com.game.serve.QueryControlServe;
import com.game.utils.Result;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/admin/student/query")
public class Query extends HttpServlet {
    QueryControlServe query = new QueryControlServe();
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
        // 将JSON字符串转换为User对象
        Student student = new Student ().mapToClass(paramMap);
        Result<PageBean<Student>> responseData =query.queryPage(currentPage, student);
        String json = new Gson().toJson(responseData);
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
