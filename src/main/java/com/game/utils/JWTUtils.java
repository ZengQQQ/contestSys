package com.game.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * JWT工具类
 * 请通过类调用方法
 生成token使用encodeJwt方法
 verifyAdmin和verifyUser方法首相保证token有效未过期，然后判断是否为管理员或用户
 */

public class JWTUtils {

    /**
     * 生成token  header.payload.singature
     */
    private static final String SING = "!Q@W#E$R";

    public static String encodeJwt(JwtData jwtData) {

        Calendar instance = Calendar.getInstance();
        // 默认1天过期
        instance.add(Calendar.DATE, 1);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        Map<String, String> map = jwtData.toMap();
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));
    }

    public static String encodeJwt(String account, String password,String identity) {

        Calendar instance = Calendar.getInstance();
        // 默认1天过期
        instance.add(Calendar.DATE, 1);

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        // payload
        Map<String, String> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        map.put("identity", identity);

        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime())  //指定令牌过期时间
                .sign(Algorithm.HMAC256(SING));
    }






    /**
     * 解码token
     * @param token token
     * @return 解码后的token，如果解码失败则返回null
     */
    public static DecodedJWT decodedJWT(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        } catch (IllegalArgumentException e) {
            return null;
        }
//        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);

    }


    /**
     * 验证token是否过期
     * @param decodedJWT 解码后的token
     * @return 过期返回true，未过期返回false
     */
    public static boolean isExpire(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(Calendar.getInstance().getTime());
    }



    /**
     * 验证token 是否为管理员
     * @param decodedJWT 解码后的token
     * @return 是管理员返回true，否则返回false
     */

    public static boolean verifyAdmin(DecodedJWT decodedJWT) {

        if (decodedJWT == null) {
            return false;
        }
        // 检查是否为管理员
        identity identity = null;
        identity = identity.valueOf(decodedJWT.getClaim("identity").asString());
        return identity == identity.admin;
    }

    /**
     * 验证token 是否为导师
     * @param decodedJWT 解码后的token
     * @return 是用户返回true，否则返回false
     */
    public static boolean verifyUser(DecodedJWT decodedJWT) {

        // 检查签名
        if (decodedJWT == null) {
            return false;
        }
        // 检查是否为导师
        identity identity = null;
        identity = identity.valueOf(decodedJWT.getClaim("identity").asString());
        return identity == identity.mentor;
    }

    /**
     * 验证token 是否为学生
     * @param decodedJWT 解码后的token
     * @return 是用户返回true，否则返回false
     */
    public static boolean verifyStudent(DecodedJWT decodedJWT) {

        // 检查签名
        if (decodedJWT == null) {
            return false;
        }
        // 检查是否为学生
        identity identity = null;
        identity = identity.valueOf(decodedJWT.getClaim("identity").asString());
        return identity == identity.student;
    }

}

