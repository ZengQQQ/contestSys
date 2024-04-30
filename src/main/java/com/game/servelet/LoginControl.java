package com.game.servelet;

import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.serve.LoginControlServe;
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

@WebServlet(value = "/loginControl")
public class LoginControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Enumeration<String> parameterNames = req.getParameterNames();

        Map<String, Object> paramMap = new HashMap<>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }

        User user = (new User()).mapToClass(paramMap);
        Administrator administrator = new Administrator(null,user.getU_acc(),user.getU_pwd());

        LoginControlServe loginControlServe = new LoginControlServe();
//        String account = req.getParameter("account");
//        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        Map<String, String> responseData = new HashMap<>();

        switch (identity) {
            case "student":
                responseData = loginControlServe.studentLogin(user);
                break;
            case "mentor":
                responseData = loginControlServe.mentorLogin(user);
                break;
            case "admin":
                responseData = loginControlServe.administratorLogin(administrator);
            default:
                responseData.put("code","0");
                responseData.put("message","身份选择错误");
                break;
        }

        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
