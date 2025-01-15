package com.wang.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 加密和解密的工具类
 * 缺陷升级:
 * 随机数的长度加长:100位以上,
 * 加密的时候,随机不全用,只用中的20个,哪里20个,不告诉你
 *
 * @author Zjx
 */
@Component
public class EncryptUtil {
    /**
     * 加密算法
     */
    private String algorithmStr = "sha256";
    /**
     * 分隔符
     */
    private String encodeSplit = "$";

    /**
     * 随机数
     */
    @Autowired
    private RegexUtil regexUtil;

    private final int encodeLen = 3;

    /**
     * 加密
     * 按照以下规则:
     * 加密算法$随机字母+数字@算法(随机数+明文)
     * Sha256$随机字母$sha256(随机字母+明文)
     *
     * @param souStr 明文	我就传密文
     * @return 密文
     */
    public String encrypt(String souStr) {
        /* 生成64位的随机数 */
        String randString = this.regexUtil.randStr("2", encodeLen);
        /* 加密:明文;密文 */
        String encodeStr = DigestUtils.sha256Hex(randString + souStr);
        /* 拼装最终字符串 */
        return algorithmStr + encodeSplit + randString + encodeSplit + encodeStr;
    }

    /**
     * 检查字符串是否需要加密
     *
     * @return true:需要加密,false:已经是密文,不需要加密
     */
    public boolean checkStr(String souStr) {
        String[] sourceArr = souStr.split("\\$");
        if (sourceArr.length == encodeLen) {
            return false;
        }
        return true;
    }

    /**
     * 密文比对
     *
     * @param encodeStr 密文
     * @param souStr    明文
     * @return
     */
    public boolean match(String encodeStr, String souStr) {
        /* spit:其实传的是正则表达式,$是正则表达的含义 */
        String[] sourceArr = encodeStr.split("\\$");
        if (sourceArr.length != encodeLen) {
            return false;
        }
        /* 比对结果;
         * 明文我有,随机数,我有
         *
         * 拿着这些结果,重新再加密一次,比对密文就可以
         *  */
        /* 生成64位的随机数 */
        String randString = sourceArr[1];
        /* 加密:明文;密文 */
        String encodeStrTemp = DigestUtils.sha256Hex(randString + souStr);
        /* 拼装最终字符串 */
        String finaStr = sourceArr[0] + encodeSplit + randString + encodeSplit + encodeStrTemp;
        /* 比对重新加密后的密文和参数的密文是否一致 */
        return encodeStr.equalsIgnoreCase(finaStr);
    }
}
