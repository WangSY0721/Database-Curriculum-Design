package com.wang.common.util;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达工式相关的工具类
 *
 * @author Zjx
 */
@Component
public class RegexUtil {
    private String strOne = "1";
    private String strTwo = "2";

    /**
     * 匹配${}的正则,还有分组的概念
     */
    private Pattern escapresource = Pattern.compile("(\\$\\{)([\\w]+)(\\})");

    /**
     * 随机生成字符串
     *
     * @param type: 生成的类型:0:纯数字,1:纯字母;2:字母+数字
     * @param num:  表达生成的位数
     * @return 按照要求的随机数;
     */
    public String randStr(String type, int num) {
        StringBuilder sb = new StringBuilder();
        /* 原始字符串 */
        String sourceString = ConstatFinalUtil.ALLSTR_NUM;
        if (strOne.equalsIgnoreCase(type)) {
            sourceString = ConstatFinalUtil.ALLSTR_CHAR;
        } else if (strTwo.equalsIgnoreCase(type)) {
            sourceString = ConstatFinalUtil.ALLSTR_CHAR_NUM;
        }

        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int randInt = random.nextInt(sourceString.length());
            /* 返回指定字符串指定位置上的char字符 */
            char chTemp = sourceString.charAt(randInt);
            sb.append(chTemp);
        }
        return sb.toString();
    }

    /**
     * 专门用来处理点位符
     * 示例: ${userName}在${year}年第${num}学期,获取${jiang}等将
     * Map:{userName=张三,year=2023,num=上,jiang:二}
     *
     * @param source 源字符串,模板字符串
     * @param paramsMap 变量;
     * @return  处理后的字符串
     */
    public String replaceOperator(String source, Map<String, String> paramsMap) {
        if (paramsMap.size() == 0) {
            return source;
        }

        StringBuffer sb = new StringBuffer();
        /*将${wangsh}的值替换掉*/
        Matcher matcher = this.escapresource.matcher(source);
        while (matcher.find()) {
            if (paramsMap.get(matcher.group(2)) != null) {
                matcher.appendReplacement(sb, paramsMap.get(matcher.group(2)));
            }
        }

        /* 将尾巴加上去 */
        matcher.appendTail(sb);
        return sb.toString();
    }
}
