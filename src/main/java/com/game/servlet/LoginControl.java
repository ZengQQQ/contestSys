package com.game.servlet;

import com.alibaba.fastjson2.JSON;
import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.serve.LoginControlServe;
import com.game.utils.CurPage;
import com.game.utils.JWTUtils;
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

@WebServlet(value = "/loginControl")
public class LoginControl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        Enumeration<String> parameterNames = req.getParameterNames();

        Map<String, Object> paramMap = new HashMap<>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
        String jsonString = JSON.toJSONString(paramMap);
        User user = JSON.parseObject(jsonString, User.class);

        Administrator administrator = new Administrator(null,user.getU_acc(),user.getU_pwd());

        LoginControlServe loginControlServe = new LoginControlServe();
        String identity = req.getParameter("identity");
        if(identity==null){
            identity="";
        }
        Result<String> responseData ;
        switch (identity){
            case "admin":
                responseData = loginControlServe.administratorLogin(administrator);
                break;
            default:
                responseData = loginControlServe.login(user);
        }
        Gson gson = new Gson();
        resp.setContentType("application/json");
        String json2 = JSON.toJSONString(responseData);
        resp.getWriter().println(json2);
        resp.getWriter().flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
