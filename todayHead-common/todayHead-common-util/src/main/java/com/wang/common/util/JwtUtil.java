package com.wang.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * JwtUtil的工具类
 */
@Component
public class JwtUtil {
    /**
     * jwt的签名
     */
    private static final String SIGN_STR = "wang";

    private String payloadKey = "username";

    /**
     * 生成一个jwt的字符串
     *
     * @param souStr 用户的唯一标识
     * @return
     */
    public String createJwt(String souStr) {
        //设置令牌的过期时间位 100 s
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE, 30);

        /**
         * 创建token
         *  //payload
         *  //设置过期时间
         *  //签名(这里自定义密钥即可)
         */
        String token = JWT.create()
                .withClaim(payloadKey, souStr)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN_STR));
        return token;
    }

    /**
     * 解析一个jwt的字符串
     *
     * @param token jwt的字符串
     * @return
     */
    public String parseJwt(String token) {
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGN_STR)).build();
        //验证Token(验证失败，会引发异常)
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim(payloadKey).asString();
    }

    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createJwt("test");
        System.out.println("==token==" + token);

        token = "eyJhb1GciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJleHAiOjE3MDQyMDM0Nzl9.P9ik3RWmzcyhdDOFnyH5mQKtlfX6sfSJxrCdliWm9AU";
        String result = jwtUtil.parseJwt(token);
        System.out.println("==result==" + result);
    }
}
