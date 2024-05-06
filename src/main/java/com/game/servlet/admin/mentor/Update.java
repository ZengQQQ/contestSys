package com.game.servlet.admin.mentor;

import com.game.domain.Mentor;
import com.game.serve.MentorServe;
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

@WebServlet("/admin/mentor/Update")
public class Update extends HttpServlet {

    private static final MentorServe mentorServe = new MentorServe();

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
        Mentor mentor = new Mentor().mapToClass(paramMap);
        Result<String> result = mentorServe.update(mentor);
        resp.getWriter().write(Result.toJson(result));
    }
}
