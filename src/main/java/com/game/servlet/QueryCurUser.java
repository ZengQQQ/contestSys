package com.game.servlet;


import com.alibaba.fastjson2.JSON;
import com.game.bean.PageBean;
import com.game.domain.User;
import com.game.serve.QueryControlServe;
import com.game.utils.JWTUtils;
import com.game.utils.Result;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(value = "/getCurrentUser")
public class QueryCurUser extends HttpServlet {
    QueryControlServe query = new QueryControlServe();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        String jsonString = JWTUtils.decodedJWT(token).getClaim("token").toString();
        User user=JSON.parseObject(jsonString,User.class);
        String json = new Gson().toJson(Result.success(user));
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
