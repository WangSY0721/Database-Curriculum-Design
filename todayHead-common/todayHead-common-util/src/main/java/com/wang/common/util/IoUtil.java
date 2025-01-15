package com.wang.common.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Io常用的类
 * @author Tm
 */
@Component
public class IoUtil {
    /**
     * 匹配${}的正则,还有分组的概念
     */
    private Pattern escapresource = Pattern.compile("(\\$\\{)([\\w]+)(\\})");

    /**
     * 读取一个文件内容
     *
     * @param is 文件的输入流
     * @return 文件的内容
     */
    public String readFile(InputStream is) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8.name()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 专门用来处理点位符
     *
     * @return
     */
    public String replaceOperator(String source, Map<String, String> paramsMap) {
        if (paramsMap.isEmpty()) {
            return source;
        }

        StringBuilder sb = new StringBuilder();
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
