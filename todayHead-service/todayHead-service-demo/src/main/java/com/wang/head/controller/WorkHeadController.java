package com.wang.head.controller;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.pojo.BaseQuery;
import com.wang.common.util.ConstatFinalUtil;
import com.wang.common.web.controller.BaseController;
import com.wang.users.pojo.AtUsersCollect;
import com.wang.users.pojo.AtUsersCollectQuery;
import com.wang.users.pojo.AtUsersComment;
import com.wang.users.pojo.AtUsersCommentQuery;
import com.wang.users.pojo.AtUsersDesc;
import com.wang.users.pojo.AtUsersMess;
import com.wang.users.pojo.AtUsersMessQuery;
import com.wang.users.pojo.AtWork;
import com.wang.users.pojo.AtWorkQuery;
import com.wang.users.service.AtUsersCollectService;
import com.wang.users.service.AtUsersCommentService;
import com.wang.users.service.AtUsersMessService;
import com.wang.users.service.AtWorkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;


/**
 * 用户首页操作
 *
 * @author wangsh
 */
@Log4j2
@Controller
@RequestMapping("/head/work/")
public class WorkHeadController extends BaseController {
    @Autowired
    private AtWorkService workService;
    @Autowired
    private AtUsersCollectService collectService;
    @Autowired
    private AtUsersCommentService commentService;
    @Autowired
    private AtUsersMessService messService;

    /**
     * 作品查询多条
     *
     * @return
     */
    @RequestMapping("/workList")
    public String workList(AtWorkQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==作品列表==");
        AtUsersDesc usersDescSess = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        /* 分页对象 */
        IPage<AtWork> paramPage = null;
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

        /* 增加条件 */
        AtWork entity = paramQuery.getEntity();
        if (AtWorkQuery.FINAL_SELF_OBJ_TRUE.equalsIgnoreCase(paramQuery.getSelfObj())) {
            entity.setUsersId(usersDescSess.getId());
        }

        /* 查询对象 */
        List<AtWork> dataList = this.workService.findList(paramPage, paramQuery);
        paramQuery.removeLike();
        /* 列表数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_DATALIST, dataList);
        /* 分页数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PAGEINFOUTIL, paramPage);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ENUMSONE, new AtWork());
        /* 查询数据 */
        return "/head/work/workList";
    }

    /**
     * 作品打开添加页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/workInsert")
    public String workInsert(AtWorkQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==作品添加==");
        AtWork entity = paramQuery.getEntity();
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, entity);
        /* 查询数据 */
        return "/head/work/workInsert";
    }

    /**
     * 作品打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/workInsertSubmit")
    public String workInsertSubmit(AtWorkQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==作品添加提交==");
        AtWork entity = paramQuery.getEntity();
        /* 从session中获取信息 */
        AtUsersDesc one = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        if (Objects.nonNull(one)) {
            entity.setUsersId(one.getId());
        }
        boolean dbFlag = this.workService.save(entity);
        JSONObject resultJson = this.proccedResponseJson(dbFlag);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        /* 查询数据 */
        return this.workInsert(paramQuery, model, request);
    }

    /**
     * 作品打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/workUpdate")
    public String workUpdate(AtWorkQuery paramQuery, Model model) {
        log.info("==作品更新==");
        /* 根据id查询 */
        AtWork one = this.workService.findOne(paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, one);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        if (BaseQuery.FINAL_OPER_UPDATE.equalsIgnoreCase(paramQuery.getOperType())) {
            /* 查询数据 */
            return "/head/work/workUpdate";
        }
        /* 更新浏览量 */
        one.setViewCount(one.getViewCount() + 1);
        this.workService.updateById(one);
        /* 查询评论列表 */
        AtUsersCommentQuery commentQuery = new AtUsersCommentQuery();
        AtUsersComment commentEntity = commentQuery.getEntity();
        commentEntity.setWorkId(one.getId());
        List<AtUsersComment> commentList = this.commentService.findList(null, commentQuery);
        model.addAttribute("commentList", commentList);
        /* 查询数据 */
        return "/head/work/workInfo";
    }

    /**
     * 作品打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/workUpdateSubmit")
    public String workUpdateSubmit(AtWorkQuery paramQuery, Model model) {
        log.info("==作品更新提交==");
        AtWork entity = paramQuery.getEntity();
        if (StringUtils.isNotEmpty(paramQuery.getPubTime())) {
            entity.setPubTime(this.dateUtil.strToDateTime(paramQuery.getPubTime()));
        }
        /* 根据id查询 */
        boolean dbFlag = this.workService.updateById(entity);
        JSONObject resultJson = this.proccedResponseJson(dbFlag);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        /* 查询数据 */
        return this.workUpdate(paramQuery, model);
    }

    /**
     * 收藏查询多条
     *
     * @return
     */
    @RequestMapping("/collectList")
    public String collectList(AtUsersCollectQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==收藏列表==");
        AtUsersDesc usersDescSess = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        /* 分页对象 */
        IPage<AtUsersCollect> paramPage = null;
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

        /* 增加条件 */
        AtUsersCollect entity = paramQuery.getEntity();
        entity.setUsersId(usersDescSess.getId());

        /* 查询对象 */
        List<AtUsersCollect> dataList = this.collectService.findList(paramPage, paramQuery);
        paramQuery.removeLike();
        /* 列表数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_DATALIST, dataList);
        /* 分页数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PAGEINFOUTIL, paramPage);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ENUMSONE, new AtUsersCollect());
        /* 查询数据 */
        return "/head/work/collectList";
    }

    /**
     * 收藏打开更新页面(ajax操作)
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping(value = "/collectInsertSubmit", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String collectInsertSubmit(AtUsersCollectQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==收藏添加提交==");
        JSONObject resultJson = new JSONObject();
        try {
            AtUsersCollect entity = paramQuery.getEntity();
            /* 从session中获取信息 */
            AtUsersDesc one = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
            if (Objects.nonNull(one)) {
                entity.setUsersId(one.getId());
            }
            boolean dbFlag = this.collectService.save(entity);
            resultJson = this.proccedResponseJson(dbFlag);
        } catch (Exception e) {
            log.error("操作失败", e);
            resultJson.put("info", e.getMessage());
            resultJson.put("code", "-1");
        }
        /* 查询数据 */
        return resultJson.toJSONString();
    }

    /**
     * 收藏删除
     * @return
     */
    @RequestMapping(value = "/collectDelete")
    public String collectDelete(AtUsersCollectQuery paramQuery, Model model, HttpServletRequest request) {
        AtUsersCollect entity = paramQuery.getEntity();
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, entity);
        int dbRes = this.collectService.deleteBatch(paramQuery);
        log.info("==收藏删除,id:{},删除条数:{}==", entity.getId(), dbRes);
        JSONObject resultJson = this.proccedResponseJson(dbRes > 0);
        /* 查询数据 */
        return this.collectList(paramQuery, model, request);
    }

    /**
     * 评论查询多条
     *
     * @return
     */
    @RequestMapping("/commentList")
    public String commentList(AtUsersCommentQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==评论列表==");
        AtUsersDesc usersDescSess = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        /* 分页对象 */
        IPage<AtUsersComment> paramPage = null;
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

        /* 增加条件 */
        AtUsersComment entity = paramQuery.getEntity();
        entity.setUsersId(usersDescSess.getId());

        /* 查询对象 */
        List<AtUsersComment> dataList = this.commentService.findList(paramPage, paramQuery);
        paramQuery.removeLike();
        /* 列表数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_DATALIST, dataList);
        /* 分页数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PAGEINFOUTIL, paramPage);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ENUMSONE, new AtUsersComment());
        /* 查询数据 */
        return "/head/work/commentList";
    }

    /**
     * 评论打开添加页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/commentInsert")
    public String commentInsert(AtUsersCommentQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==评论添加==");
        AtUsersComment entity = paramQuery.getEntity();
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, entity);
        /* 查询数据 */
        return "/head/work/commentInsert";
    }

    /**
     * 评论打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/commentInsertSubmit")
    public String commentInsertSubmit(AtUsersCommentQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==评论添加提交==");
        AtUsersComment entity = paramQuery.getEntity();
        /* 从session中获取信息 */
        AtUsersDesc one = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        if (Objects.nonNull(one)) {
            entity.setUsersId(one.getId());
        }
        boolean dbFlag = this.commentService.save(entity);
        JSONObject resultJson = this.proccedResponseJson(dbFlag);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        /* 查询数据 */
        return this.commentInsert(paramQuery, model, request);
    }

    /**
     * 评论打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/commentUpdate")
    public String commentUpdate(AtUsersCommentQuery paramQuery, Model model) {
        log.info("==评论更新==");
        /* 根据id查询 */
        AtUsersComment one = this.commentService.findOne(paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, one);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        if (BaseQuery.FINAL_OPER_UPDATE.equalsIgnoreCase(paramQuery.getOperType())) {
            /* 查询数据 */
            return "/head/work/commentUpdate";
        }
        /* 查询数据 */
        return "/head/work/commentInfo";
    }

    /**
     * 评论打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/commentUpdateSubmit")
    public String commentUpdateSubmit(AtUsersCommentQuery paramQuery, Model model) {
        log.info("==评论更新提交==");
        AtUsersComment entity = paramQuery.getEntity();
        if (StringUtils.isNotEmpty(paramQuery.getPubTime())) {
            entity.setPubTime(this.dateUtil.strToDateTime(paramQuery.getPubTime()));
        }
        /* 根据id查询 */
        boolean dbFlag = this.commentService.updateById(entity);
        JSONObject resultJson = this.proccedResponseJson(dbFlag);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        /* 查询数据 */
        return this.commentUpdate(paramQuery, model);
    }

    /**
     * 收藏删除
     * @return
     */
    @RequestMapping(value = "/commentDelete", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String commentDelete(AtUsersCommentQuery paramQuery, Model model) {
        AtUsersComment entity = paramQuery.getEntity();
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, entity);
        boolean dbFlag = this.collectService.removeById(entity.getId());
        log.info("==评论删除,id:{},结果:{}==", entity.getId(), dbFlag);
        JSONObject resultJson = this.proccedResponseJson(dbFlag);
        /* 查询数据 */
        return resultJson.toJSONString();
    }

    /**
     * 私信查询多条
     *
     * @return
     */
    @RequestMapping("/messList")
    public String messList(AtUsersMessQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==私信列表==");
        AtUsersDesc usersDescSess = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        /* 分页对象 */
        IPage<AtUsersMess> paramPage = null;
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

        /* 查询发送者是自己 */
        AtUsersMess entity = paramQuery.getEntity();
        if (AtUsersMessQuery.FINAL_TRUE.equalsIgnoreCase(paramQuery.getFromUsersFlag())) {
            entity.setFromUsersId(usersDescSess.getId());
        }

        /* 查询接收者是自己 */
        if (AtUsersMessQuery.FINAL_TRUE.equalsIgnoreCase(paramQuery.getToUsersFlag())) {
            entity.setToUsersId(usersDescSess.getId());
        }

        /* 查询对象 */
        List<AtUsersMess> dataList = this.messService.findList(paramPage, paramQuery);
        paramQuery.removeLike();
        /* 列表数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_DATALIST, dataList);
        /* 分页数据 */
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PAGEINFOUTIL, paramPage);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ENUMSONE, new AtUsersMess());
        /* 查询数据 */
        return "/head/work/messList";
    }

    /**
     * 私信打开添加页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/messInsert")
    public String messInsert(AtUsersMessQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==私信添加==");
        AtUsersMess entity = paramQuery.getEntity();
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, entity);
        /* 查询数据 */
        return "/head/work/messInsert";
    }

    /**
     * 私信打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/messInsertSubmit")
    public String messInsertSubmit(AtUsersMessQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==私信添加提交==");
        AtUsersMess entity = paramQuery.getEntity();
        /* 从session中获取信息 */
        AtUsersDesc one = (AtUsersDesc) this.findObjFromSession(request, BasePojoEnum.SESS_USERS);
        if (Objects.nonNull(one)) {
            entity.setFromUsersId(one.getId());
        }
        boolean dbFlag = this.messService.save(entity);
        JSONObject resultJson = this.proccedResponseJson(dbFlag);
        model.addAttribute(ConstatFinalUtil.FINAL_RESPONSE, resultJson);
        /* 查询数据 */
        return this.messInsert(paramQuery, model, request);
    }

    /**
     * 私信打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/messUpdate")
    public String messUpdate(AtUsersMessQuery paramQuery, Model model) {
        log.info("==私信更新==");
        /* 根据id查询 */
        AtUsersMess one = this.messService.findOne(paramQuery);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, one);
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_PARAMQUERY, paramQuery);
        if (BaseQuery.FINAL_OPER_UPDATE.equalsIgnoreCase(paramQuery.getOperType())) {
            /* 查询数据 */
            return "/head/work/messUpdate";
        }
        /* 查询数据 */
        return "/head/work/messInfo";
    }

    /**
     * 私信打开更新页面
     * 更新和详情是同一个页面;
     *
     * @return
     */
    @RequestMapping("/messDelete")
    public String messDelete(AtUsersMessQuery paramQuery, Model model, HttpServletRequest request) {
        log.info("==私信删除==");
        AtUsersMess entity = paramQuery.getEntity();
        model.addAttribute(ConstatFinalUtil.FINAL_CODE_ONE, entity);
        int dbRes = this.messService.deleteBatch(paramQuery);
        log.info("==私信删除,id:{},删除条数:{}==", entity.getId(), dbRes);
        JSONObject resultJson = this.proccedResponseJson(dbRes > 0);
        /* 查询数据 */
        return this.messList(paramQuery, model, request);
    }
}