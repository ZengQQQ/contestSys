package com.game.servlet.user;

import com.alibaba.fastjson2.JSON;
import com.game.bean.PageBean;
import com.game.domain.StallTeamMessage;
import com.game.domain.fixDomain.StallTeamMessageFix;
import com.game.serve.QueryControlServe;
import com.game.utils.Result;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(value = "/admin/queryStallTeamMessage")
public class QueryStallTeamMessage extends HttpServlet {
    QueryControlServe query = new QueryControlServe();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }
        Integer currentPage = Integer.valueOf(req.getParameter("currentPage"));
        // 将JSON字符串转换为User对象
        String jsonString = jsonBuilder.toString();
        StallTeamMessage stall = JSON.parseObject(jsonString, StallTeamMessage.class);
        Result<PageBean<StallTeamMessageFix>> responseData =query.queryPage(currentPage,stall);
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}