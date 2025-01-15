package com.wang.common;


import com.wang.common.util.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Redis的相关测试类
 * @author ZjxMi
 */
@SpringBootTest
@Log4j2
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void set(){
        boolean setRes = this.redisUtil.set("test:a:b", "100");
        log.info("==set结果:{}==", setRes);
    }

    @Test
    public void get(){
        String setRes = this.redisUtil.get("a");
        log.info("==get 结果:{}==", setRes);
    }
}
