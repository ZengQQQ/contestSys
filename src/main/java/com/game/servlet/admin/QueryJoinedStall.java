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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/admin/queryJoinedStall")
public class QueryJoinedStall extends HttpServlet {
    QueryControlServe query = new QueryControlServe();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

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
        Integer currentPage =(Integer) paramMap.get("currentPage");
        User stall = (new User()).mapToClass(paramMap);
        TeamUserMessage chain = new TeamUserMessage();
        Team chain1 = new Team();
        StallTeamMessage chain2 = new StallTeamMessage();
        Stall target = new Stall();
        String joinType = req.getParameter("joinType");
        String teamType = req.getParameter("teamType");
        String joinStallType = req.getParameter("joinStallType");
        String stallType = req.getParameter("stallType");
        if(joinType==null){
            joinType="";
        }
        if(teamType==null){
            joinType="";
        }
        if(joinStallType==null){
            joinType="";
        }
        if(stallType==null){
            joinType="";
        }

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
            case "disband":chain1.setT_status(2);
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

        Result<PageBean<StallFix>> responseData =query.joinedStallQuery(currentPage,stall,chain,chain1,chain2,target);
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
