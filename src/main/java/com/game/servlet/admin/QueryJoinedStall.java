package com.game.servlet.admin;


import com.alibaba.fastjson2.JSON;
import com.game.bean.PageBean;
import com.game.domain.*;
import com.game.domain.fixDomain.StallFix;
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

@WebServlet(value = "/admin/queryJoinedStall")
public class QueryJoinedStall extends HttpServlet {
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
        Team chain1 = new Team();
        StallTeamMessage chain2 = new StallTeamMessage();
        Stall target = new Stall();
        String joinType = req.getParameter("joinType");
        String teamType = req.getParameter("teamType");
        String joinStallType = req.getParameter("joinStallType");
        String stallType = req.getParameter("stallType");

        switch (joinType){
            case "joined":chain.setJoin_status(1);
                break;
            case "joining":chain.setJoin_status(0);chain.setTsm_pass(1);
                break;
            default:
        }
        switch (teamType){
            case "normal":chain1.setT_status(0);
                break;
            case "lock":chain1.setT_status(1);
                break;
            case "Disband":chain1.setT_status(2);
                break;
            case "illegal":chain1.setT_status(3);
                break;
            case "drop":chain1.setT_status(4);
                break;
            default:
        }
        switch (joinStallType){
            case "joined":chain2.setJoin_status(1);
                break;
            case "joining":chain2.setJoin_status(0);chain2.setStm_status(1);
                break;
            default:
        }
        switch (stallType){
            case "normal":target.setSt_status(0);
                break;
            case "lock":target.setSt_status(1);
                break;
            case "illegal":target.setSt_status(2);
                break;
            case "drop":target.setSt_status(3);
                break;
            default:
        }

        Integer currentPage = Integer.valueOf(req.getParameter("currentPage"));
        // 将JSON字符串转换为User对象
        String jsonString = jsonBuilder.toString();
        User stall = JSON.parseObject(jsonString, User.class);
        Result<PageBean<StallFix>> responseData =query.joinedStallQuery(currentPage,stall,chain,chain1,chain2,target);
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
