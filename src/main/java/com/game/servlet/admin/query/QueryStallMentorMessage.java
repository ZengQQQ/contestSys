package com.game.servlet.admin.query;

import com.alibaba.fastjson2.JSON;
import com.game.bean.PageBean;
import com.game.domain.StallMentorMessage;
import com.game.domain.fixDomain.StallMentorMessageFix;
import com.game.serve.QueryControlServe;
import com.game.utils.CurPage;
import com.game.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/admin/queryStallMentorMessage")
public class QueryStallMentorMessage extends HttpServlet {
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
        String jsonString = JSON.toJSONString(paramMap);
        StallMentorMessage stall = JSON.parseObject(jsonString, StallMentorMessage.class);
         Integer currentPage;
        try{
            currentPage=JSON.parseObject(jsonString, CurPage.class).getCurrentPage();
        }catch (Exception e){
            currentPage =1;
        }

        // 将JSON字符串转换为User对象
        Result<PageBean<StallMentorMessageFix>> responseData =query.queryPage(currentPage,stall);
        String json = JSON.toJSONString(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
