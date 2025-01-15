package com.wang.common;

import com.wang.common.pojo.BasePojo;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.BeanUtil;
import com.wang.demo.pojo.AtDemoDynasty;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * 测试公共模块的代码
 * @author ZjxMi
 */
@SpringBootTest
@Log4j2
public class CommonTest {
    @Autowired
    private BeanUtil beanUtil;

    @Test
    public void copyPro(){
        AtDemoDynasty source = new AtDemoDynasty();
        source.setCapital("杭州");
        source.setName("南宋");

        AtDemoDynasty target = new AtDemoDynasty();
        target.setName("北宋");
        target.setCapital("开封");
        target.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        target.setCreateTime(new Date());
        target.setUpdateTime(new Date());
        target.setPubTime(new Date());

        AtDemoDynasty proccObj = (AtDemoDynasty) beanUtil.copy(source, target);
        log.info("==目标值:{}==", target);
        log.info("==目标返回值:{}==", proccObj);
    }

    /**
     * 枚举相关测试
     */
    @Test
    public void enumTest(){
        log.info("==枚举的toString:{}==取值code:{},info:{}", BasePojoEnum.STATUS_ENABLE,
                BasePojoEnum.STATUS_ENABLE.getCode(), BasePojoEnum.STATUS_ENABLE.getInfo());
        BasePojoEnum[] enumsArr = BasePojoEnum.values();
        for (BasePojoEnum enumsTemp : enumsArr) {
            log.info("==循环:toString:{}==", enumsTemp);
        }
    }

    @Test
    public void basePojoTest(){
        BasePojo basePojo = new BasePojo();
        log.info("==父pojo的枚举信息:{}==", basePojo.getEnumsJson().toJSONString());
    }
}