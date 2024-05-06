package com.game.servlet.admin.student;

import com.game.domain.Project;

import com.game.domain.Student;
import com.game.serve.StudentService;
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

@WebServlet(value = "/admin/student/insert")
public class Insert extends HttpServlet {
    StudentService studentService = new StudentService();

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
//        Integer currentPage = Integer.parseInt((String) paramMap.get("currentPage"));
        // 将JSON字符串转换为User对象
        // todo Can not set java.lang.Integer field com.game.domain.Student.s_status to java.lang.String
        Student student = new Student().mapToClass(paramMap);
        Result<String> result = studentService.insert(student);
        resp.getWriter().write(Result.toJson(result));
    }
}
