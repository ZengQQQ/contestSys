package com.game.servelet;

import com.game.domain.Administrator;
import com.game.domain.Mentor;
import com.game.domain.Student;
import com.game.serve.AdministratorServe;
import com.game.serve.MentorServe;
import com.game.serve.StudentServe;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet(value = "/login")
public class LoginServeLet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        LocalDateTime now = LocalDateTime.now();

        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化日期时间为字符串
        String formattedDateTime = now.format(formatter);

        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        Boolean loginResult = false;
        switch (identity){
            case "student":
                StudentServe studentServe = new StudentServe();
                Student student = new Student();
                student.setS_xuehao(account);
                student.setS_pwd(password);
                loginResult = studentServe.checkPassword(student);
                break;
            case "mentor":
                MentorServe mentorServe = new MentorServe();
                Mentor mentor = new Mentor();
                mentor.setM_acc(account);
                mentor.setM_pwd(password);
                loginResult = mentorServe.checkPassword(mentor);
                break;
            case "admin":
                AdministratorServe administratorServe = new AdministratorServe();
                Administrator administrator = new Administrator();
                administrator.setA_acc(account);
                administrator.setA_pwd(password);
                loginResult = administratorServe.checkPassword(administrator);
        }

        JSONObject responseJson = new JSONObject();
        responseJson.put("time", formattedDateTime); // 添加时间到JSON响应中
        responseJson.put("account", account); // 添加账号到JSON响应中
        responseJson.put("identity", identity); // 添加身份到JSON响应中
        responseJson.put("loginSuccess", loginResult); // 添加登录结果到JSON响应中

        if (loginResult) {
            System.out.println("At:" + formattedDateTime + account + "登录成功");
            responseJson.put("message", "登录成功");
        } else {
            System.out.println("At:" + formattedDateTime + account + "登录失败");
            responseJson.put("message", "登录失败");
        }

        // 设置响应内容类型为JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 发送JSON响应到前端
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(responseJson.toJSONString());
        printWriter.flush();
    }



    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println(req);

        // 解析请求参数
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String identity = req.getParameter("identity");
        Boolean loginResult = false;

        //检查数据
        if(account==null||password==null||identity==null){
            System.out.println("变量为空");
            return;
        }


        // 登录逻辑
        switch (identity) {
            case "student":
                StudentServe studentServe = new StudentServe();
                Student student = new Student();
                student.setS_xuehao(account);
                student.setS_pwd(password);
                loginResult = studentServe.checkPassword(student);
                break;
            case "mentor":
                MentorServe mentorServe = new MentorServe();
                Mentor mentor = new Mentor();
                mentor.setM_acc(account);
                mentor.setM_pwd(password);
                loginResult = mentorServe.checkPassword(mentor);
                break;
            case "admin":
                AdministratorServe administratorServe = new AdministratorServe();
                Administrator administrator = new Administrator();
                administrator.setA_acc(account);
                administrator.setA_pwd(password);
                loginResult = administratorServe.checkPassword(administrator);
                break;
            default:
                // Handle unknown identity or error
                break;
        }

        // 构建JSON响应
        JSONObject responseJson = new JSONObject();
        responseJson.put("loginSuccess", loginResult);
        responseJson.put("account", account);
        responseJson.put("identity", identity);

        if (loginResult) {
            responseJson.put("message", "登录成功");
        } else {
            responseJson.put("message", "登录失败");
        }

        // 发送响应
        PrintWriter out = resp.getWriter();
        out.print(responseJson.toString());
        out.flush();
    }
}
