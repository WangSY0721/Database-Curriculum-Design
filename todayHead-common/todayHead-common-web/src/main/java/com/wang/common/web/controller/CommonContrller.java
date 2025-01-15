package com.wang.common.web.controller;

import com.wang.common.util.RegexUtil;
import com.wang.common.util.VerifyCodeUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * 公共的Servlet
 * @author Zjx
 */
@Controller
@RequestMapping(value = "/common")
@Log4j2
public class CommonContrller extends BaseController {
    @Autowired
    private RegexUtil regexUtil;

    /**
     * 生成随机的验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/imgCode")
    public void imgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("==验证的controller是否执行==");

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 生成随机字串
        String verifyCode = regexUtil.randStr("2", 4);
        // 存入会话session
        HttpSession session = request.getSession(true);
        /* 把生成的随机数放到session中 */
        session.setAttribute("rand", verifyCode);
        // 生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}