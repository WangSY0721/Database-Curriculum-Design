package com.wang.common.web.controller;

import com.alibaba.fastjson2.JSONObject;
import com.wang.common.util.ConstatFinalUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理全局的异常
 *
 * @author ZjxMi
 */
@ControllerAdvice
@Log4j2
public class GloableAdvice extends BaseController {

    /**
     * 处理全局异常
     *
     * @param e
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String proccedException(Exception e) {
        log.error("==处理的异常:{}==", e.getMessage(), e);
        Map<String,String> paramMap = new HashMap<>(1);
        paramMap.put(ConstatFinalUtil.FINAL_CONFIG_INFO_SUCCESS_PARAM1, e.getMessage());
        JSONObject resultJson = this.proccedResponseJson(false, paramMap);
        return resultJson.toJSONString();
    }
}
