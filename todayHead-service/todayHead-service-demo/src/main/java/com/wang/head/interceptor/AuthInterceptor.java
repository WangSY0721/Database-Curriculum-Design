package com.wang.head.interceptor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.ConstatFinalUtil;
import com.wang.common.util.RedisUtil;
import com.wang.common.web.controller.BaseController;
import com.wang.users.pojo.AtUsersDesc;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 登陆验证的拦截器
 *
 * @author wangsh
 */
@Log4j2
@Component("authInterceptor")
public class AuthInterceptor extends BaseController implements HandlerInterceptor {
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 先执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("--授权拦截器执行--");
        JSONObject resultJson = new JSONObject();
        HttpSession session = request.getSession();
        /* 获取用户对象 */
        AtUsersDesc users = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        if (users == null) {
            String info = (String) this.redisUtil.hashGet(RedisUtil.REDIS_KEY, ConstatFinalUtil.FINAL_CONFIG_INFO_FAILED);
            JSONObject infoJson = JSON.parseObject(info);
            resultJson.put("code", "1");

            resultJson.put("info", "非法访问,请先登录");

            /* 提示信息 */
            session.setAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);

            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
