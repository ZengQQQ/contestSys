package com.game.servlet;
import com.game.domain.User;
import com.game.serve.SignUpControlServe;
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Enumeration<String> parameterNames = req.getParameterNames();

        // 创建一个Map来存储请求参数
        Map<String, Object> paramMap = new HashMap<>();

        // 遍历参数Enumeration并将参数名和值放入Map中
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }

//        String account = req.getParameter("account");
//        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        SignUpControlServe signUpControlServe = new SignUpControlServe();
        User user = (new User()).mapToClass(paramMap);
//        user.setU_acc(account);
//        user.setU_pwd(password);
        Map<String, String> responseData = new HashMap<>();
        switch (identity) {
            case "student":
                responseData = signUpControlServe.studentSignUp(user);
                break;
            case "mentor":
                responseData = signUpControlServe.mentorSignUp(user);
                break;
            default:
                responseData.put("code", "0");
                responseData.put("message", "身份选择错误");
                break;
        }
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