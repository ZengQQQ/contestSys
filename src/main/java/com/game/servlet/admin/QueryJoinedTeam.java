package com.game.servlet.admin;


import com.alibaba.fastjson2.JSON;
import com.game.bean.PageBean;
import com.game.domain.Team;
import com.game.domain.TeamUserMessage;
import com.game.domain.User;
import com.game.domain.fixDomain.TeamFix;
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

@WebServlet(value = "/admin/queryJoinedTeam")
public class QueryJoinedTeam extends HttpServlet {
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
        TeamUserMessage chain = new TeamUserMessage();
        Team target = new Team();
        String joinType = req.getParameter("joinType");
        String targetType = req.getParameter("teamType");
        switch (joinType){
            case "joined":chain.setJoin_status(1);
                break;
            case "joining":chain.setJoin_status(0);chain.setTsm_pass(1);
                break;
            default:
        }
        switch (targetType){
            case "normal":target.setT_status(0);
                break;
            case "lock":target.setT_status(1);
                break;
            case "Disband":target.setT_status(2);
                break;
            case "illegal":target.setT_status(3);
                break;
            case "drop":target.setT_status(4);
                break;
            default:
        }
        Integer currentPage = Integer.valueOf(req.getParameter("currentPage"));
        // 将JSON字符串转换为User对象
        String jsonString = jsonBuilder.toString();
        User stall = JSON.parseObject(jsonString, User.class);
        Result<PageBean<TeamFix>> responseData =query.joinedTeamQuery(currentPage,stall,chain,target);
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
