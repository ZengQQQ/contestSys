//package com.game.servlet.user;
//
//import com.alibaba.fastjson2.JSON;
//import com.game.bean.PageBean;
//import com.game.domain.Project;
//import com.game.domain.StallTeamMessage;
//import com.game.domain.fixDomain.ProjectFix;
//import com.game.serve.QueryControlServe;
//import com.game.utils.Result;
//import com.google.gson.Gson;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//@WebServlet(value = "/user/queryProject")
//public class QueryProject extends HttpServlet {
//    QueryControlServe query = new QueryControlServe();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Enumeration<String> parameterNames = req.getParameterNames();
//
//        Map<String, Object> paramMap = new HashMap<>();
//
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//            String paramValue = req.getParameter(paramName);
//            paramMap.put(paramName, paramValue);
//        }
//        Integer currentPage;
//        if(paramMap.get("currentPage")==null){
//            currentPage =1;
//        }else {
//            currentPage = Integer.parseInt((String) paramMap.get("currentPage"));
//        }
//        // 将JSON字符串转换为User对象
//        Project stall = new Project ().mapToClass(paramMap);
//        Result<PageBean<ProjectFix>> responseData =query.queryPage(currentPage,stall);
//        String json = JSON.toJSONString(responseData);
//        resp.setContentType("application/json");
//        resp.getWriter().write(json);
//        resp.getWriter().flush();
//    }
//}
