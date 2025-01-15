package com.wang.common.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 爬虫:专门像js操作dom一样, 操作html;
 *
 * @author ZjxMi
 */
@Component
@Log4j2
public class JsoupUtil {
    @Autowired
    private IoUtil ioUtil;

    /**
     * https%3A%2F%2Fsearch.jd.com%2FSearch%3Fkeyword%3D5G%E6%89%8B%E6%9C%BA%26enc%3Dutf-8%26wq%3D5G%E6%89%8B%E6%9C%BA%26pvid%3Dc3a47b882caf4d61b89fefc0079a0f5f
     * 模板字符串
     * https%3A%2F%2Fsearch.jd.com%2FSearch%3Fkeyword%3D%24%7BcateName%7D%26enc%3Dutf-8%26wq%3D%24%7BcateName%7D%26pvid%3Dc3a47b882caf4d61b89fefc0079a0f5f%3B
     * @param cateName 5g手机
     * @return 商品列表;(要把京东的商品换成我们自己的商品)
     */
    public JSONArray spiderJdGoodsList(String cateName) {
        JSONArray resultArr = new JSONArray();
        String urlTemp = "https://search.jd.com/Search?keyword=${cateName}&enc=utf-8&wq=${cateName}&pvid=c3a47b882caf4d61b89fefc0079a0f5f";
        Map<String,String> paramsMap = new HashMap<>(1);
        try {
            paramsMap.put("cateName", URLEncoder.encode(cateName, StandardCharsets.UTF_8.name()));
            String url = this.ioUtil.replaceOperator(urlTemp, paramsMap);
            log.info("==请求网址:{}==", url);
            /* 向网页发送请求 */
            Connection connect = Jsoup.connect(url);
            /* 放请求头 */
            connect.header("cookie", "__jdv=76161171|direct|-|none|-|1702913014928; __jdu=1702913014926983431788; areaId=7; shshshfpa=eebbc0df-24a2-d95a-24bb-77b979629235-1702913016; shshshfpx=eebbc0df-24a2-d95a-24bb-77b979629235-1702913016; thor=113631B64B44D8D32DBFBE4A222BAF69FC6A79C9E5C2806F632F6E37AA7A8084D92C20F8B52E64F2810BE04B947B925F5FE9203498A69EF672B902A83699FCECF2A4CAC40A4E6631B6CEC630C2AFB875CA9DF45130DC4F5AF9EECAA81901530C01085B62BCC00B1F7C3704AEEF793AFBB008CB67CDD06783C8F97BF65D4C2EBDF914907FA48AB091031661DA07D3D5D1; flash=2_0vOh5rqRkukDGNNjKt74qGR5iVbo5qr8h7LF8SJkGyv9rUyiHZUKMJ4zr0G6CpLXjEAy0arW6eBE5SJq6Fcu3OQxpHMysiRIF4zJ_teqWFD*; pinId=b9_aR34o_EPZTlS-EiSWPA; pin=minjiaren; unick=%E4%B8%BA%E4%BA%86%E7%88%B1%E6%A2%A6%E4%B8%80%E7%94%9F150; _tp=yrv3h6KxEQn%2FtgG5j%2B1zgw%3D%3D; _pst=minjiaren; ipLoc-djd=7-412-416-47178; PCSYCityID=CN_410000_410100_0; jsavif=1; jsavif=1; rkv=1.0; avif=1; xapieid=jdd03KAWFTUB2BW7SHVTAU6RU2XJWYERZNSUY6WGINFULDK2LETGRQMAW4RKXPZWZB3T4LAB76P5I3M4J7VULNIKZPSZYSYAAAAMMVOH3MRYAAAAACR2IEKDYARJQSQX; qrsc=3; 3AB9D23F7A4B3C9B=KAWFTUB2BW7SHVTAU6RU2XJWYERZNSUY6WGINFULDK2LETGRQMAW4RKXPZWZB3T4LAB76P5I3M4J7VULNIKZPSZYSY; __jda=76161171.1702913014926983431788.1702913015.1703606132.1703685197.9; __jdb=76161171.6.1702913014926983431788|9.1703685197; __jdc=76161171; 3AB9D23F7A4B3CSS=jdd03KAWFTUB2BW7SHVTAU6RU2XJWYERZNSUY6WGINFULDK2LETGRQMAW4RKXPZWZB3T4LAB76P5I3M4J7VULNIKZPSZYSYAAAAMMVOPQZGAAAAAACBNTQTSJ2AFWU4X; _gia_d=1; shshshsID=055d4f1362dcad3fd5d313d2d9616a40_6_1703686376157; shshshfpb=AAqwQn6uMErvA3ySi2Voku3e5eWKSNRcCkTAWVgAAAAA");
            connect.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Core/1.94.218.400 QQBrowser/12.1.5497.400");
            /* 获取数据,返回值是docuement */
            Document document = connect.get();
//            log.info("==返回内容:{}==", document);
            /* 下面的代码和js操作dom一模一样 */
            Element goodsListDivEle = document.getElementById("J_goodsList");
            /* 获取商品列表 */
            Elements goodsUlEles = goodsListDivEle.getElementsByClass("gl-warp");
            Elements goodsLiEles = goodsUlEles.get(0).getElementsByClass("gl-item");
            log.info("==数量:{}==", goodsLiEles.size());
            for (Element dataEleTemp : goodsLiEles) {
                JSONObject goodsJson = new JSONObject();
                /* 商品id;获取属性 */
                String jdGoodsId = dataEleTemp.attr("data-sku");
                /* 商品名称;获取文本节点:开始标签和结束标签中间的内容 */
                Element goodsNameEle = dataEleTemp.getElementsByClass("p-name").get(0).getElementsByTag("em").get(0);
                String goodsNameStr = goodsNameEle.text().trim();
                /* 价格: */
                Element priceEle = dataEleTemp.getElementsByClass("p-price").get(0).getElementsByTag("i").get(0);
                String priceStr = priceEle.text().trim();
//                log.info("==京东的goodsId:{};名称:{};价格:{}==", jdGoodsId, goodsNameStr, priceStr);
                goodsJson.put("name", goodsNameStr);
                goodsJson.put("price", priceStr);
                goodsJson.put("sourceId", jdGoodsId);
                goodsJson.put("sourceType", "1");

                resultArr.add(goodsJson);
            }
        } catch (Exception e) {
            log.error("请求京东的商品列表报错;url:{},cateName:{}", urlTemp, cateName, e);
        }
        return resultArr;
    }
}
