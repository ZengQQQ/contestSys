package com.game.servlet;
import com.game.domain.User;
import com.game.serve.SignUpControlServe;
import com.game.utils.Result;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/signUpControl")
public class SignUpControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Enumeration<String> parameterNames = req.getParameterNames();

        // 创建一个Map来存储请求参数
        Map<String, Object> paramMap = new HashMap<>();

        // 遍历参数Enumeration并将参数名和值放入Map中
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }

        String identity = req.getParameter("identity");


        SignUpControlServe signUpControlServe = new SignUpControlServe();
        User user = (new User()).mapToClass(paramMap);
        switch (identity){
            case "mentor":
                user.setU_identity(1);
            default:
                user.setU_identity(0);
        }
        Result<String> responseData= signUpControlServe.SignUp(user);
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

}