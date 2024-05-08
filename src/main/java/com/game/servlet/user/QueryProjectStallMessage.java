package com.game.servlet.user;

import com.alibaba.fastjson2.JSON;
import com.game.bean.PageBean;
import com.game.domain.StallProjectMessage;
import com.game.domain.User;
import com.game.domain.fixDomain.StallProjectMessageFix;
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

@WebServlet(value = "/user/ProjectStallMessage")
public class QueryProjectStallMessage extends HttpServlet {
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
         Integer currentPage;
        try{
            currentPage=JSON.parseObject(jsonString, CurPage.class).getCurrentPage();
        }catch (Exception e){
            currentPage =1;
        }
        StallProjectMessage stallProjectMessage = new StallProjectMessage();
        String way = req.getParameter("way");
        User user =JSON.parseObject(jsonString, User.class);

        if(way==null){
            way="";
        }
        switch (way){
            case "put":
                stallProjectMessage.setSpm_dct(0);
                break;
            case "get":
                stallProjectMessage.setSpm_dct(1);
                break;
            default:
        }
        Result<PageBean<StallProjectMessageFix>> responseData =query.queryProStallPage(currentPage,user);
        String json = JSON.toJSONString(responseData);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
        resp.getWriter().flush();
    }
}
