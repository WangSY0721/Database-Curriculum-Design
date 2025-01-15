package com.wang.head.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.pojo.BaseQuery;
import com.wang.common.util.ConstatFinalUtil;
import com.wang.common.web.controller.BaseController;
import com.wang.users.pojo.AtUsersCollect;
import com.wang.users.pojo.AtUsersDesc;
import com.wang.users.pojo.AtUsersDescQuery;
import com.wang.users.service.AtUsersDescService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 用户首页操作
 *
 * @author wangsh
 */
@Log4j2
@Controller
@RequestMapping("/head/users/")
public class UsersHeadController extends BaseController {
    @Autowired
    private AtUsersDescService usersDescService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request, Model model) {
        log.info("--main--");
        AtUsersDesc usersSess = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        if (StringUtils.isEmpty(usersSess.getId())) {
            return "redirect:/login";
        }
        AtUsersDescQuery paramQuery = new AtUsersDescQuery();
        AtUsersDesc entity = paramQuery.getEntity();
        entity.setId(usersSess.getId());
        AtUsersDesc one = this.usersDescService.findOne(paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, one);
        return "/head/users/main";
    }

    /**
     * 欢迎页面
     *
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome() {
        log.info("--welcome--");
        return "/head/users/welcome";
    }

    /**
     * 打开用户更新
     *
     * @return
     */
    @RequestMapping("/usersUpdate")
    public String usersUpdate(HttpServletRequest request, AtUsersDescQuery paramQuery,Model model) {
        log.info("--usersUpdate--");

        /* 从session中获取信息 */
        AtUsersDesc one = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        AtUsersDesc entity = paramQuery.getEntity();
        entity.setId(one.getId());
        one = this.usersDescService.findOne(paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, one);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        if ("updateInfo".equalsIgnoreCase(paramQuery.getOperType())) {
            /* 根据不同的请求跳转不同的页面 */
            return "/head/users/usersUpdateInfo";
        } else if ("updatePass".equalsIgnoreCase(paramQuery.getOperType())) {
            /* 根据不同的请求跳转不同的页面 */
            return "/head/users/usersUpdatePass";
        }
        return "/head/users/usersInfo";
    }

    /**
     * 打开用户提交
     *
     * @return
     */
    @RequestMapping("/usersUpdateSubmit")
    public String usersUpdateSubmit(HttpServletRequest request,AtUsersDescQuery paramQuery, Model model) {
        log.info("--usersUpdateSubmit--");
        AtUsersDesc users = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        /* 从session中获取信息 */
        AtUsersDesc one = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        AtUsersDesc entityCond = paramQuery.getEntity();
        entityCond.setId(one.getId());
        one = this.usersDescService.findOne(paramQuery);

        if (BaseQuery.FINAL_OPER_UPDATE.equalsIgnoreCase(paramQuery.getOperType())) {
            AtUsersDesc entity = paramQuery.getEntity();

            boolean dbFlag = this.usersDescService.updateById(entity);
            JSONObject resultJson = this.proccedResponseJson(dbFlag);
            log.info("==dbFlag=={}", dbFlag);
            model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
            return this.usersUpdate(request, paramQuery,model);
        }
        return "/head/users/usersInfo";
    }

    /**
     * 退出页面
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session) {
        log.info("--logout--");
        /* 获取用户 */
        /* 删除 */
        session.removeAttribute(BasePojoEnum.SESS_USERS.getCode());
        session.removeAttribute("lastLoginTime");
        /* 客户端跳转到登陆页面 */
        return "redirect:/login";
    }

    /**
     * 打开用户更新
     *
     * @return
     */
    @RequestMapping("/usersList")
    public String usersList(HttpServletRequest request, AtUsersDescQuery paramQuery,Model model) {
        log.info("--usersList--");
        /* 分页对象 */
        IPage<AtUsersDesc> paramPage = null;
        try {
            paramPage = new Page<>(paramQuery.getCurrent(), paramQuery.getSize());
        } catch (Exception e) {
            paramPage = new Page<>(1, 20);
        }
        /* 时间处理 */
        if (StringUtils.isNotEmpty(paramQuery.getStDate())) {
            paramQuery.setPubTimeSt(this.dateUtil.strToDateTime(paramQuery.getStDate()));
        }
        paramQuery.addLike();

        List<AtUsersDesc> dataList = this.usersDescService.findList(paramPage, paramQuery);
        /* 列表数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_DATALIST, dataList);
        /* 分页数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PAGEINFOUTIL, paramPage);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ENUMSONE, new AtUsersCollect());
        return "/head/users/usersList";
    }
}
