package com.wang.head.controller;

import com.alibaba.fastjson2.JSONObject;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.ConstatFinalUtil;
import com.wang.common.web.controller.BaseController;
import com.wang.users.pojo.AtUsersDesc;
import com.wang.users.pojo.AtUsersDescQuery;
import com.wang.users.service.AtUsersDescService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;


/**
 * 不需要登陆的Controller
 *
 * @author wangsh
 */
@Log4j2
@Controller
public class NoLoginHeadController extends BaseController {
    @Autowired
    private AtUsersDescService usersDescService;

    /**
     * 打开登陆页面
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("==login==");
        /* 获取对象,此对象是登录的时候放进去;
         * 如果有,说明登录了.(买票)
         * 如果没有,说明没有登录,(没有买票,去买票)
         *  */
        AtUsersDesc users = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        if (users != null) {
            if (StringUtils.isEmpty(users.getId())) {
                return "/head/usersLogin";
            }
            /* 说明用户肯定是登录了 */
            return "redirect:/head/users/main";
        }
        return "/head/usersLogin";
    }

    /**
     * 登陆提交操作
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/loginSubmit")
    public String loginSubmit(HttpServletRequest request, String email, String password, String code, HttpSession session,
                              HttpServletResponse response, Model model) {
        log.info("==loginSubmit==");
        String info = "";
        /* 接收参数 */
        /* 获取到图片中的code */
        String sessionCode = session.getAttribute("rand") + "";
        log.info("==email==" + email);
        log.info("==password==" + password);
        log.info(code + "==password==" + password);
        if (email == null || "".equalsIgnoreCase(email)) {
            info = "请输入邮箱";
        } else {
            /* 万能验证码 */
            if (sessionCode.equalsIgnoreCase(code) || ConstatFinalUtil.FINAL_CODE_STR.equalsIgnoreCase(code)) {
                /* 这叫query对象 */
                AtUsersDescQuery usersQuery = new AtUsersDescQuery();
                AtUsersDesc usersParam = usersQuery.getEntity();
                usersParam.setEmail(email);
                AtUsersDesc users = this.usersDescService.findOne(usersQuery);
                /* 关联对象 */
                if (Objects.nonNull(users)) {
                    /* 根据邮箱查询到了===验证密码 */
                    if (this.encryptUtil.match(users.getPassword(), password)) {
                        /* 密码正确==看状态 */
                        if (BasePojoEnum.STATUS_ENABLE.getCode().equalsIgnoreCase(users.getStatus())) {
                            /* 登录成功 */
                            /* 发票 */
                            session.setAttribute(BasePojoEnum.SESS_USERS.getCode(), users);
                            session.setAttribute("lastLoginTime", users.getLastLoginTime());
                            /* 更新上次登录时间 */
                            users.setLastLoginTime(new Date());
                            boolean dbFlag = this.usersDescService.updateById(users);
                            log.info("==用户操作数据库结果:=={}", dbFlag);
                            return "redirect:/head/users/main";
                        } else {
                            info = "账号状态不正确,请联系管理员";
                        }
                    } else {
                        /* 密码不正确 */
                        info = "密码不正确";
                    }
                    model.addAttribute("email", email);
                } else {
                    /* 邮箱填写不正确 */
                    info = "邮箱填写不正确";
                }
            } else {
                /* 验证码不正确 */
                info = "验证码不正确";
            }
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("info", info);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        return this.login(request, response, session);
    }

    /**
     * 打开删除页面
     *
     * @return  跳转页面路径
     * @throws IOException
     */
    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        log.info("==register==");
        return "/head/usersRegister";
    }

    /**
     * 登陆提交操作
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/registerSubmit")
    public String registerSubmit(HttpServletRequest request, String email, String password, String code, HttpSession session,
                              HttpServletResponse response, Model model) {
        log.info("==registerSubmit==");
        String info = "";
        /* 接收参数 */
        try {
            /* 获取到图片中的code */
            String sessionCode = session.getAttribute("rand") + "";
            if (email == null || "".equalsIgnoreCase(email)) {
                info = "请输入邮箱";
            } else {
                /* 万能验证码 */
                if (sessionCode.equalsIgnoreCase(code) || ConstatFinalUtil.FINAL_CODE_STR.equalsIgnoreCase(code)) {
                    /* 这叫query对象 */
                    AtUsersDesc users = new AtUsersDesc();
                    users.setEmail(email);
                    users.setPassword(this.encryptUtil.encrypt(password));
                    /* 关联对象 */
                    boolean dbFlag = this.usersDescService.save(users);
                    log.info("==用户操作数据库结果:=={}", dbFlag);
                    info = "注册成功";
                    model.addAttribute("email", email);
                } else {
                    /* 验证码不正确 */
                    info = "验证码不正确";
                }
            }
        } catch (Exception e) {
            log.error("==注册用户报错==", e);
            info = e.getMessage();
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("info", info);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        return this.register(request, response, session);
    }
}