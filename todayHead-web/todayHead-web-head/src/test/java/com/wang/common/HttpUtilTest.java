package com.wang.common;

import com.wang.common.util.HttpUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http的测试类
 * @author ZjxMi
 */
@Log4j2
@SpringBootTest
public class HttpUtilTest {
    @Autowired
    private HttpUtil httpUtil;

    /**
     * 测试一下get方法
     */
    @Test
    public void methodGet(){
        HttpUtil httpUtil = new HttpUtil();
        //String url = "http://192.168.31.54:10002/exec-proj-shop-web-back/noLogin/adminsLogin?a=1&b=2&c=3";
        String url = "http://192.168.31.54:10002/exec-proj-shop-web-back/noLogin/adminsLogin?a=100";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Core/1.94.218.400 QQBrowser/12.1.5497.400");

        Map<String, String[]> paramsMap = new HashMap<>();
        paramsMap.put("a", new String[]{"1"});
        paramsMap.put("b", new String[]{"2"});
        paramsMap.put("c", new String[]{"3"});
        Map<String, List<String>> responseMap = new HashMap<>();
        String response = httpUtil.methodGet(url, headerMap, paramsMap, responseMap);
        log.info("==response:{}==", response);
        log.info("==response头:{}==", responseMap);
    }

    /**
     * 测试一下get方法
     */
    @Test
    public void methodPost(){
        HttpUtil httpUtil = new HttpUtil();
        //String url = "http://192.168.31.54:10002/exec-proj-shop-web-back/noLogin/adminsLogin?a=1&b=2&c=3";
        String url = "http://192.168.31.54:10002/exec-proj---shop-web-back/noLogin/adminsLogin?a=100";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Core/1.94.218.400 QQBrowser/12.1.5497.400");

        Map<String, String[]> urlParamMap = new HashMap<>();
        urlParamMap.put("a", new String[]{"1"});
        urlParamMap.put("b", new String[]{"2"});
        urlParamMap.put("c", new String[]{"3"});

        Map<String, String[]> paramsMap = new HashMap<>();
        paramsMap.put("a1", new String[]{"1", "200", "3300"});
        paramsMap.put("b1", new String[]{"2"});
        paramsMap.put("c1", new String[]{"3"});
        Map<String, List<String>> responseMap = new HashMap<>();
        String response = httpUtil.methodPost(url, headerMap, urlParamMap, paramsMap, responseMap);
        log.info("==response:{}==", response);
        log.info("==response头:{}==", responseMap);
    }

    /**
     * 测试一下get方法
     */
    @Test
    public void methodGetBaidu(){
        HttpUtil httpUtil = new HttpUtil();
        //String url = "http://192.168.31.54:10002/exec-proj-shop-web-back/noLogin/adminsLogin?a=1&b=2&c=3";
        String url = "https://search.jd.com/Search?keyword=5G%E6%89%8B%E6%9C%BA&enc=utf-8&wq=5G%E6%89%8B%E6%9C%BA&pvid=c3a47b882caf4d61b89fefc0079a0f5f";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("cookie", "__jdv=76161171|direct|-|none|-|1702913014928; __jdu=1702913014926983431788; areaId=7; shshshfpa=eebbc0df-24a2-d95a-24bb-77b979629235-1702913016; shshshfpx=eebbc0df-24a2-d95a-24bb-77b979629235-1702913016; thor=113631B64B44D8D32DBFBE4A222BAF69FC6A79C9E5C2806F632F6E37AA7A8084D92C20F8B52E64F2810BE04B947B925F5FE9203498A69EF672B902A83699FCECF2A4CAC40A4E6631B6CEC630C2AFB875CA9DF45130DC4F5AF9EECAA81901530C01085B62BCC00B1F7C3704AEEF793AFBB008CB67CDD06783C8F97BF65D4C2EBDF914907FA48AB091031661DA07D3D5D1; flash=2_0vOh5rqRkukDGNNjKt74qGR5iVbo5qr8h7LF8SJkGyv9rUyiHZUKMJ4zr0G6CpLXjEAy0arW6eBE5SJq6Fcu3OQxpHMysiRIF4zJ_teqWFD*; pinId=b9_aR34o_EPZTlS-EiSWPA; pin=minjiaren; unick=%E4%B8%BA%E4%BA%86%E7%88%B1%E6%A2%A6%E4%B8%80%E7%94%9F150; _tp=yrv3h6KxEQn%2FtgG5j%2B1zgw%3D%3D; _pst=minjiaren; ipLoc-djd=7-412-416-47178; PCSYCityID=CN_410000_410100_0; jsavif=1; jsavif=1; rkv=1.0; avif=1; xapieid=jdd03KAWFTUB2BW7SHVTAU6RU2XJWYERZNSUY6WGINFULDK2LETGRQMAW4RKXPZWZB3T4LAB76P5I3M4J7VULNIKZPSZYSYAAAAMMVOH3MRYAAAAACR2IEKDYARJQSQX; qrsc=3; 3AB9D23F7A4B3C9B=KAWFTUB2BW7SHVTAU6RU2XJWYERZNSUY6WGINFULDK2LETGRQMAW4RKXPZWZB3T4LAB76P5I3M4J7VULNIKZPSZYSY; __jda=76161171.1702913014926983431788.1702913015.1703606132.1703685197.9; __jdb=76161171.6.1702913014926983431788|9.1703685197; __jdc=76161171; 3AB9D23F7A4B3CSS=jdd03KAWFTUB2BW7SHVTAU6RU2XJWYERZNSUY6WGINFULDK2LETGRQMAW4RKXPZWZB3T4LAB76P5I3M4J7VULNIKZPSZYSYAAAAMMVOPQZGAAAAAACBNTQTSJ2AFWU4X; _gia_d=1; shshshsID=055d4f1362dcad3fd5d313d2d9616a40_6_1703686376157; shshshfpb=AAqwQn6uMErvA3ySi2Voku3e5eWKSNRcCkTAWVgAAAAA");
        headerMap.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Core/1.94.218.400 QQBrowser/12.1.5497.400");
        Map<String, String[]> paramsMap = new HashMap<>();
        Map<String, List<String>> responseMap = new HashMap<>();
        String response = httpUtil.methodGet(url, headerMap, paramsMap, responseMap);
        log.info("==response:{}==", response);
        log.info("==response头:{}==", responseMap);
    }

    /**
     * 测试一下get方法
     */
    @Test
    public void methodPostJson(){
        HttpUtil httpUtil = new HttpUtil();
        String url = "http://192.168.31.54:10002/exec-proj-shop-web-back/back/goods/brandUpdateSubmit";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", "JSESSIONID=EC71C7FA3175E05658D0B78EBD736A14");

        Map<String, String[]> queryMap = new HashMap<>();
        Map<String, String[]> paramsMap = new HashMap<>();
        paramsMap.put("entity.id", new String[]{"1737501824686030850"});
        paramsMap.put("entity.name", new String[]{"华为"});
        paramsMap.put("entity.url", new String[]{"huawei"});
        //paramsMap.put("entity.pubTime", new String[]{"2023-12-20 23:54:51"});
        paramsMap.put("entity.status", new String[]{"1"});
        paramsMap.put("entity.content", new String[]{"<p>华为<br/></p>"});

        Map<String, List<String>> responseMap = new HashMap<>();
        String response = httpUtil.methodPost(url, headerMap, queryMap, paramsMap, responseMap);
        log.info("==response:{}==", response);
        log.info("==response头:{}==", responseMap);
    }

    /**
     * 测试一下get方法
     */
    @Test
    public void methodGetInputstream(){
        HttpUtil httpUtil = new HttpUtil();
        String url = "https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/topnav/newfanyi-da0cea8f7e.png";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Cookie", "JSESSIONID=EC71C7FA3175E05658D0B78EBD736A14");

        Map<String, String[]> queryMap = new HashMap<>();
        Map<String, String[]> paramsMap = new HashMap<>();
        paramsMap.put("entity.id", new String[]{"1737501824686030850"});
        paramsMap.put("entity.name", new String[]{"华为"});
        paramsMap.put("entity.url", new String[]{"huawei"});
        //paramsMap.put("entity.pubTime", new String[]{"2023-12-20 23:54:51"});
        paramsMap.put("entity.status", new String[]{"1"});
        paramsMap.put("entity.content", new String[]{"<p>华为<br/></p>"});

        Map<String, List<String>> responseMap = new HashMap<>();
        InputStream is = httpUtil.methodGetInputStream(url, headerMap, paramsMap, responseMap);
        log.info("==response头:{}==", responseMap);

        try {
            FileOutputStream fos = new FileOutputStream("d:/1.png");
            IOUtils.copy(is, fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
