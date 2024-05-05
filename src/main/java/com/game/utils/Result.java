package com.game.utils;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.Map;

@Data
public class Result<T> {
    public int code = 200;
    public String message = "success";
    public T data = null;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回成功结果
     * @param data 返回的数据
     * @return 结果类
     * @param <T> 返回的数据类型
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(200, "success", data);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<T>(400, message, data);
    }

    public static <T> Result<T> unAuth(T data) {
        return new Result<T>(401, "未登录", data);
    }

    public static <T> Result<T> unPri() {
        return new Result<T>(403, "无权限", null);
    }


    /**
     * 业务失败
     * @param message 失败信息
     * @param data 返回的数据
     * @return 结果json
     * @param <T> 返回的结果中的数据
     */

    public static <T> Result<T> error(String message, T data) {
        return new Result<T>(500, message, data);
    }


    /**
     * 将结果类转换为json字符串
     * @param res 结果类
     * @return json字符串
     */

    public static String toJson(Result res) {
        return JSON.toJSONString(res);
    }


    /**
     * 将map转换为结果类
     *
     */
    public static <T> Result<T> fromMap(Map<String, Object> map) {
        Result<T> res = new Result<T>();
        if (map.containsKey("code")) {
            res.code = (int) map.get("code");
        }
        if (map.containsKey("message")) {
            res.message = (String) map.get("message");
        }
        if (map.containsKey("data")) {
            res.data = (T) map.get("data");
        }
        return res;
    }



}
