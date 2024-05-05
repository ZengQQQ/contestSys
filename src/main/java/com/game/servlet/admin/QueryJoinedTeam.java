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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/admin/queryJoinedTeam")
public class QueryJoinedTeam extends HttpServlet {
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
        Team target = new Team();
        String joinType = req.getParameter("joinType");
        String targetType = req.getParameter("teamType");
        if(joinType==null){
            joinType="";
        }
        if(targetType==null){
            joinType="";
        }
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
            case "disband":target.setT_status(2);
                break;
            case "illegal":target.setT_status(3);
                break;
            case "drop":target.setT_status(4);
                break;
            default:
        }
        Result<PageBean<TeamFix>> responseData =query.joinedTeamQuery(currentPage,stall,chain,target);
        String json = new Gson().toJson(responseData);
        resp.setContentType("application/json");
        resp.getWriter().println(json);
        resp.getWriter().flush();
    }
}
