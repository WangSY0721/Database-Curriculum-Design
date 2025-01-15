package com.wang;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * SpringBoot的核心启动类
 * @author ZjxMi
 */
@Log4j2
@SpringBootApplication
public class HeadApplication {
    /**
     * 主方法运行
     * @param args  参数
     */
    public static void main(String[] args) {
        /* 启动SpringBoot */
        SpringApplication springApplication = new SpringApplication(HeadApplication.class);
        /* 获取运行对象 */
        ConfigurableApplicationContext cr = springApplication.run(args);

        printUrl(cr);
    }

    /**
     * 打印Url信息
     * @param cr    SpringBoot运行对象
     */
    private static void printUrl(ConfigurableApplicationContext cr) {
        /* 打印访问地址
         *   http://127.0.0.1:8080/项目名
         *  */
        ConfigurableEnvironment environment = cr.getEnvironment();
        String ip = "localhost";
        try {
            /* 获取服务端ip */
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取ip地址失败", e);
        }
        /* 获取端口: 端口是在SpringBoot的配置文件中存储 */
        String port = environment.getProperty("server.port");
        /* 项目名 */
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            contextPath = "";
        }
        /* 模板字符串 */
        String urlFormat = "http://%s:%s%s";
        String url = String.format(urlFormat, ip, port, contextPath);
        log.info("访问地址:{}", url);
    }
}
