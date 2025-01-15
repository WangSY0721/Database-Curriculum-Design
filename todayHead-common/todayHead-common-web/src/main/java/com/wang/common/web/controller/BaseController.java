package com.wang.common.web.controller;

import com.alibaba.fastjson2.JSONObject;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.ConstatFinalUtil;
import com.wang.common.util.DateUtil;
import com.wang.common.util.EncryptUtil;
import com.wang.common.util.JwtUtil;
import com.wang.common.util.RedisUtil;
import com.wang.common.util.RegexUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储的是所有Servlet中的公共代码
 *
 * @author Zjx
 */
public class BaseController {
    /**
     * 所有的Servlet都会用到工具类
     */
    @Autowired
    protected EncryptUtil encryptUtil;
    @Autowired
    protected DateUtil dateUtil;
    @Autowired
    protected RedisUtil redisUtil;
    @Autowired
    protected RegexUtil regexUtil;
    @Autowired
    protected JwtUtil jwtUtil;

    /**
     * 拼装json结果
     *
     * @param dbRes
     * @return
     */
    protected JSONObject proccedResponseJson(boolean dbRes) {
        return this.proccedResponseJson(dbRes, new HashMap<>(1));
    }

    /**
     * 拼装json结果
     *
     * @param dbRes     true:成功,false:失败
     * @param paramsMap 参数
     * @return
     */
    protected JSONObject proccedResponseJson(boolean dbRes, Map<String, String> paramsMap) {
        if (!paramsMap.containsKey(ConstatFinalUtil.FINAL_CONFIG_INFO_SUCCESS_PARAM1)) {
            paramsMap.put(ConstatFinalUtil.FINAL_CONFIG_INFO_SUCCESS_PARAM1, "");
        }
        JSONObject resultJson = new JSONObject();
        if (dbRes) {
            resultJson.put("code", "0");
            String info = "操作成功";
            resultJson.put("info", info);
        } else {
            String info = "操作失败";
            resultJson.put("code", "1");
            resultJson.put("info", info);
        }
        return resultJson;
    }

    /**
     * 拼装json结果
     *
     * @param dbRes     true:成功,false:失败
     * @param paramsMap 参数
     * @return
     */
    protected JSONObject proccedResponseHeadJson(boolean dbRes, Map<String, String> paramsMap) {
        if (!paramsMap.containsKey(ConstatFinalUtil.FINAL_CONFIG_INFO_SUCCESS_PARAM1)) {
            paramsMap.put(ConstatFinalUtil.FINAL_CONFIG_INFO_SUCCESS_PARAM1, "");
        }
        JSONObject resultJson = new JSONObject();
        if (dbRes) {
            resultJson.put("code", "0");
            String info = "操作成功";
            resultJson.put("info", info);
        } else {
            String info = "操作成失败";
            resultJson.put("code", "1");

            resultJson.put("info", info);
        }
        return resultJson;
    }

    /**
     * 获取令牌
     * @param request
     * @return
     */
    protected String findIdentity(HttpServletRequest request) {
        /* 获取session */
        String jwtToken = request.getHeader("jwtToken");
        if (StringUtils.isEmpty(jwtToken)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("jwtToken".equalsIgnoreCase(cookie.getName())) {
                        jwtToken = cookie.getValue();
                        break;
                    }
                }
            }
        }
        return jwtToken;
    }

    protected Object findObjFromSession(HttpServletRequest request, BasePojoEnum userType){
        HttpSession session = request.getSession();
        Object result = null;
        if (userType == BasePojoEnum.SESS_USERS) {
            result = session.getAttribute("usersSess");
        } else if (userType == BasePojoEnum.SESS_ADMINS){
            result = session.getAttribute("adminsSess");
        }
        return result;
    }
}