package com.game.servlet;


import com.game.domain.Administrator;
import com.game.domain.User;
import com.game.serve.LoginControlServe;
import com.game.utils.JsonParams;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.game.utils.Result;

@WebServlet("/login")
public class Login extends HttpServlet {

    private static final LoginControlServe loginControlServe = new LoginControlServe();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> jsonParams = JsonParams.getJsonParams(req);

        System.out.println("map data"+jsonParams);

        User user = new User();
        user.setU_acc(jsonParams.get("account").toString());
        user.setU_pwd(jsonParams.get("password").toString());
        Administrator administrator = new Administrator(null, user.getU_acc(), user.getU_pwd());
        Map<String, String> responseData = null;
        String identity = jsonParams.get("identity").toString();
        System.out.println("identity"+identity);
        switch (identity) {
            case "student":
                responseData = loginControlServe.studentLogin(user);

                break;
            case "mentor":
                responseData = loginControlServe.mentorLogin(user);
                break;
            case "admin":
                responseData = loginControlServe.administratorLogin(administrator);
                break;
            default:
                responseData.put("code", "0");
                responseData.put("message", "身份选择错误");
                break;
        }
        if (responseData != null) {
            resp.getWriter().write(JSON.toJSONString(responseData));
        } else {
            resp.getWriter().write(Result.toJson(Result.error("登录失败", null)));
        }

    }
}
