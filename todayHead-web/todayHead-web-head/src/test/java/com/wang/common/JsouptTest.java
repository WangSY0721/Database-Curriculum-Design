package com.wang.common;

import com.alibaba.fastjson2.JSONArray;
import com.wang.common.util.JsoupUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试类(jsoup)
 * @author ZjxMi
 */
@SpringBootTest
@Log4j2
public class JsouptTest {
    @Autowired
    private JsoupUtil jsoupUtil;

    @Test
    public void spiderJdGoodsList(){
        String cateName = "5G手机";
        JSONArray resultArr = this.jsoupUtil.spiderJdGoodsList(cateName);
        log.info("==结果:{}==", resultArr.toJSONString());
    }
}
