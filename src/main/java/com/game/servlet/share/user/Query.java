package com.game.servlet.share.user;//package com.game.servlet.user;
//
//
//import com.alibaba.fastjson2.JSON;
//import com.game.bean.PageBean;
//import com.game.dao.UserDao;
//import com.game.domain.User;
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
//@WebServlet(value = "/user/queryUser")
//public class QueryUser extends HttpServlet {
//    QueryControlServe query = new QueryControlServe();
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json;charset=UTF-8");
//
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
//        User stall = (new User()).mapToClass(paramMap);
//        Result<PageBean<User>> responseData =query.queryPage(currentPage,stall);
//        String json = JSON.toJSONString(responseData);
//        resp.setContentType("application/json");
//        resp.getWriter().write(json);
//        resp.getWriter().flush();
//    }
//}
