package com.wang.common.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Http的工具类
 *
 * @author ZjxMi
 */
@Component
@Log4j2
public class HttpUtil {
    private static final String URL_WEN = "?";
    private static final String URL_AND = "&";
    private static final String URL_EQ = "=";

    /**
     * @param urlStr         请求的网址
     * @param headerMap   请求头
     * @param paramsMap   请求体
     * @param responseMap 响应头
     * @return 响应体
     */
    @SuppressWarnings("all")
    public String methodGet(String urlStr, Map<String, String> headerMap, Map<String, String[]> paramsMap,
                            Map<String, List<String>> responseMap) {
        StringBuilder resSb = new StringBuilder();
        log.info("==get请求;url:{},请求头:{},请求体:{}==", urlStr, headerMap, paramsMap);
        InputStream is = null;
        for (int i = 0 ; i < ConstatFinalUtil.RETRY ; i ++) {
            log.info("==重试次数:{}==", i);
            try {
                StringBuilder querySb = new StringBuilder();
                Set<Map.Entry<String, String[]>> paramsMapEntrySet = paramsMap.entrySet();
                for (Map.Entry<String, String[]> me : paramsMapEntrySet) {
                    String key = me.getKey();
                    String[] val = me.getValue();
                    if (Objects.isNull(val)) {
                        continue;
                    }
                    if (val.length == 1) {
                        querySb.append(key + URL_EQ + val[0] + URL_AND);
                    } else {
                        for (String valTemp : val) {
                            querySb.append(key + URL_EQ + valTemp + URL_AND);
                        }
                    }
                }
                log.info("==查询字符串:{}==", querySb);
                if (querySb.length() > 0) {
                    if (urlStr.contains(URL_WEN)) {
                        urlStr = urlStr + URL_AND;
                    } else {
                        urlStr = urlStr + URL_WEN;
                    }
                    urlStr = urlStr + querySb.toString();
                }
                log.info("==请求网址:{}==", urlStr);
                /* 创建一个网址对象 */
                URL url = new URL(urlStr);
                /* 获取管子 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                /* 请求超时 */
                connection.setConnectTimeout(ConstatFinalUtil.SECOND * 20);
                connection.setReadTimeout(ConstatFinalUtil.SECOND * 20);

                /* 设置请求头 */
                for (Map.Entry<String, String> me : headerMap.entrySet()) {
                    String key = me.getKey();
                    String val = me.getValue();
                    if (StringUtils.isNotEmpty(val)) {
                        connection.setRequestProperty(key, val);
                    }
                }

                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                log.info("==响应信息:响应码:{},描述:{}==", responseCode, responseMessage);
                responseMap.putAll(connection.getHeaderFields());

                /* 输入流 */
                is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8.name()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    resSb.append(line);
                }
                return resSb.toString();
            } catch (Exception e) {
                log.error("获取数据报错了;get请求;url:{},请求头:{},请求体:{}==", urlStr, headerMap, paramsMap, e);
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        return resSb.toString();
    }

    /**
     * @param urlStr         请求的网址
     * @param headerMap   请求头
     * @param paramsMap   请求体
     * @param responseMap 响应头
     * @return 响应体
     */
    @SuppressWarnings("all")
    public String methodPost(String urlStr, Map<String, String> headerMap, Map<String, String[]> urlParamMap,
                             Map<String, String[]> paramsMap,
                            Map<String, List<String>> responseMap) {
        StringBuilder resSb = new StringBuilder();
        OutputStream os = null;
        InputStream is = null;
        for (int i = 0 ; i < ConstatFinalUtil.RETRY ; i ++) {
            log.info("==post请求;重试次数:{},url:{},请求头:{},请求体:{}==", i, urlStr, headerMap, paramsMap);
            try {
                StringBuilder querySb = new StringBuilder();
                Set<Map.Entry<String, String[]>> paramsMapEntrySet = urlParamMap.entrySet();
                for (Map.Entry<String, String[]> me : paramsMapEntrySet) {
                    String key = me.getKey();
                    String[] val = me.getValue();
                    if (Objects.isNull(val)) {
                        continue;
                    }
                    if (val.length == 1) {
                        querySb.append(key + URL_EQ + val[0] + URL_AND);
                    } else {
                        for (String valTemp : val) {
                            querySb.append(key + URL_EQ + valTemp + URL_AND);
                        }
                    }
                }
                if (querySb.length() > 0) {
                    if (urlStr.contains(URL_WEN)) {
                        urlStr = urlStr + URL_AND;
                    } else {
                        urlStr = urlStr + URL_WEN;
                    }
                    urlStr = urlStr + querySb.toString();
                }
                log.info("==请求网址:{}==", urlStr);
                /* 创建一个网址对象 */
                URL url = new URL(urlStr);
                /* 获取管子 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                /* Post请求 */
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                /* 请求超时 */
                connection.setConnectTimeout(ConstatFinalUtil.SECOND * 5);
                connection.setReadTimeout(ConstatFinalUtil.SECOND * 5);
                /* 设置请求头 */
                for (Map.Entry<String, String> me : headerMap.entrySet()) {
                    String key = me.getKey();
                    String val = me.getValue();
                    if (StringUtils.isNotEmpty(val)) {
                        connection.setRequestProperty(key, val);
                    }
                }
                os = connection.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8.name()));
                StringBuilder paramSb = new StringBuilder();
                for (Map.Entry<String, String[]> me : paramsMap.entrySet()) {
                    String key = me.getKey();
                    String[] val = me.getValue();
                    if (Objects.isNull(val)) {
                        continue;
                    }
                    if (val.length == 1) {
                        paramSb.append(key + URL_EQ + val[0] + URL_AND);
                    } else {
                        for (String valTemp : val) {
                            paramSb.append(key + URL_EQ + valTemp + URL_AND);
                        }
                    }
                }
                log.info("==请求体字符串:{}==", paramSb);
                bw.write(paramSb.toString());
                bw.flush();

                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                log.info("==响应信息:响应码:{},描述:{}==", responseCode, responseMessage);
                responseMap.putAll(connection.getHeaderFields());
                /* 输入流 */
                is = connection.getInputStream();
                if (is == null) {
                    /* 获取错误信息 */
                    is = connection.getErrorStream();
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8.name()));
                String line = "";

                while ((line = br.readLine()) != null) {
                    resSb.append(line);
                }
                return resSb.toString();
            } catch (Exception e) {
                log.error("获取数据报错了;post请求;url:{},请求头:{},请求体:{}==", urlStr, headerMap, paramsMap, e);
            } finally {
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(os);
            }
        }
        return resSb.toString();
    }

    /**
     * @param urlStr         请求的网址
     * @param headerMap   请求头
     * @param paramsMap   请求体
     * @param responseMap 响应头
     * @return 响应体
     */
    public InputStream methodGetInputStream(String urlStr, Map<String, String> headerMap, Map<String, String[]> paramsMap,
                            Map<String, List<String>> responseMap) {
        StringBuilder resSb = new StringBuilder();
        log.info("==get请求;url:{},请求头:{},请求体:{}==", urlStr, headerMap, paramsMap);
        InputStream is = null;
        for (int i = 0 ; i < ConstatFinalUtil.RETRY ; i ++) {
            log.info("==重试次数:{}==", i);
            try {
                StringBuilder querySb = new StringBuilder();
                Set<Map.Entry<String, String[]>> paramsMapEntrySet = paramsMap.entrySet();
                for (Map.Entry<String, String[]> me : paramsMapEntrySet) {
                    String key = me.getKey();
                    String[] val = me.getValue();
                    if (Objects.isNull(val)) {
                        continue;
                    }
                    if (val.length == 1) {
                        querySb.append(key + URL_EQ + val[0] + URL_AND);
                    } else {
                        for (String valTemp : val) {
                            querySb.append(key + URL_EQ + valTemp + URL_AND);
                        }
                    }
                }
                log.info("==查询字符串:{}==", querySb);
                if (querySb.length() > 0) {
                    if (urlStr.contains(URL_WEN)) {
                        urlStr = urlStr + URL_AND;
                    } else {
                        urlStr = urlStr + URL_WEN;
                    }
                    urlStr = urlStr + querySb.toString();
                }
                log.info("==请求网址:{}==", urlStr);
                /* 创建一个网址对象 */
                URL url = new URL(urlStr);
                /* 获取管子 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                /* 请求超时 */
                connection.setConnectTimeout(ConstatFinalUtil.SECOND * 20);
                connection.setReadTimeout(ConstatFinalUtil.SECOND * 20);

                /* 设置请求头 */
                for (Map.Entry<String, String> me : headerMap.entrySet()) {
                    String key = me.getKey();
                    String val = me.getValue();
                    if (StringUtils.isNotEmpty(val)) {
                        connection.setRequestProperty(key, val);
                    }
                }

                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                log.info("==响应信息:响应码:{},描述:{}==", responseCode, responseMessage);
                responseMap.putAll(connection.getHeaderFields());

                /* 输入流 */
                is = connection.getInputStream();
                return is;
            } catch (Exception e) {
                log.error("获取数据报错了;get请求;url:{},请求头:{},请求体:{}==", urlStr, headerMap, paramsMap, e);
            }
        }
        return null;
    }
}
