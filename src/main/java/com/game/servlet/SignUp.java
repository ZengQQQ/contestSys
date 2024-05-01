package com.game.servlet;

import com.alibaba.fastjson2.JSON;
import com.game.domain.User;
import com.game.serve.SignUpControlServe;
import com.game.utils.JsonParams;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private static final SignUpControlServe signUpControlServe = new SignUpControlServe();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将请求参数转换为json格式，再转换为map
        Map<String, Object> jsonParams = JsonParams.getJsonParams(req);
        // 将map 转换为 User 对象
        User user = new User();
        user.setU_acc(jsonParams.get("account").toString());
        user.setU_pwd(jsonParams.get("password").toString());
        // 接受消息的map
        Map<String, String> responseData = new HashMap<>();
        switch (jsonParams.get("identity").toString()){
            case "student":
                responseData = signUpControlServe.studentSignUp(user);
                break;
            case "mentor":
                responseData = signUpControlServe.mentorSignUp(user);
                break;
            default:
                responseData.put("code", "0");
                responseData.put("message", "身份选择错误");
                break;
        }
        resp.getWriter().write(JSON.toJSONString(responseData));
    }
}
