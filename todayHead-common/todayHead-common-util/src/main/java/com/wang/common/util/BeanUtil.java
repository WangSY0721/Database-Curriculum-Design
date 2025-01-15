package com.wang.common.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * bean的工具类
 *
 * @author Wangsh
 */
@Component("beanUtil")
public class BeanUtil {
    /**
     * 拷贝属性
     * 如果源数据的对象不为空,直接赋值到目标对象中
     * 源对象和目标对象是一个类型(10个字段)
     * 源对象有5个字段有值,目标对象是10个有值
     * 把源对象中的5个字段有值,赋值到目标对象中.(覆盖)
     *
     * @param souObj    源对象
     * @param tarObj    目标对象
     * @return
     */
    public Object copy(Object souObj, Object tarObj) {
        JSONObject souJson = (JSONObject) JSON.toJSON(souObj);
        JSONObject tarJson = (JSONObject) JSON.toJSON(tarObj);
        /* 循环的是源 */
        Set<Map.Entry<String, Object>> entrySet = souJson.entrySet();
        for (Map.Entry<String, Object> me : entrySet) {
            String key = me.getKey();
            Object val = me.getValue();
            if (Objects.nonNull(val)) {
                /* 最终赋值的是目标对象 */
                tarJson.put(key, val);
            }
        }

        /* 存储最终的数据 */
        tarObj = JSON.parseObject(tarJson.toJSONString(), tarObj.getClass());
        return tarObj;
    }
}
