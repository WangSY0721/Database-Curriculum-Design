package com.wang.common.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Redis相关的工具类
 * @author ZjxMi
 */
@Component
@Log4j2
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 系统配置的Redis常量
     */
    public static final String REDIS_KEY = "system:configMap";

    /**
     * ==Redis的set命令
     *
     * @param key
     * @param value
     */
    public boolean set(String key, String value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, value, e);
        }
        return false;
    }

    /**
     * ==Redis的set命令
     *
     * @param key
     * @param value
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     */
    public boolean set(String key, String value, long timeout, TimeUnit timeUnit) {
        try {
            this.set(key, value);
            return Boolean.TRUE.equals(this.redisTemplate.expire(key, timeout, timeUnit));
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, value, e);
        }
        return false;
    }

    /**
     * ==Redis的set命令
     *
     * @param key
     * @param value
     * @param expireDate  指定时间
     */
    public boolean set(String key, String value, Date expireDate) {
        try {
            this.set(key, value);
            return Boolean.TRUE.equals(this.redisTemplate.expireAt(key, expireDate));
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, value, e);
        }
        return false;
    }

    /**
     * ==Redis的get命令
     *
     * @param key
     */
    public String get(String key) {
        try {
           return this.redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, e);
        }
        return null;
    }

    /**
     * ==Redis的get命令
     *
     * @param key
     */
    public Boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, e);
        }
        return false;
    }

    /**
     * ==Redis的hset命令
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public Boolean hashPut(String key, String hashKey, String value) {
        try {
            this.redisTemplate.opsForHash().put(key, hashKey, value);
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, e);
        }
        return false;
    }

    /**
     * ==Redis的hset命令
     *
     * @param key
     * @param hashKey
     */
    public Object hashGet(String key, String hashKey) {
        try {
            return this.redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, e);
        }
        return null;
    }

    /**
     * ==Redis的hset命令
     *
     * @param key
     * @param hashKey
     */
    public Long hashDelete(String key, String hashKey) {
        try {
            return this.redisTemplate.opsForHash().delete(key, hashKey);
        } catch (Exception e) {
            log.error("保存失败,key:{},value:{}", key, e);
        }
        return null;
    }
}
