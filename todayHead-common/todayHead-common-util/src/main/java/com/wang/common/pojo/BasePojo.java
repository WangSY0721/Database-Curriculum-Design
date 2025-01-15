package com.wang.common.pojo;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wang.common.util.ConstatFinalUtil;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * 所有Pojo的父类
 * @author ZjxMi
 */
@Data
public class BasePojo {
    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 状态:(0:禁用,1:启用)
     */
    @TableField(value = "status")
    private String status;

    /**
     * 逻辑删除(0:已删除,1:未删除)
     */
    @TableField(value = "delFlag")
    private String delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 发布时间(排序)
     */
    @TableField(value = "pubTime")
    private Date pubTime;

    /**
     * 状态:(0:禁用,1:启用)
     * 字符串描述
     */
    @TableField(exist = false)
    private String statusStr;
    /**
     * 逻辑删除(0:已删除,1:未删除)
     * 字符串描述
     */
    @TableField(exist = false)
    private String delFlagStr;

    /**
     * 存储了所有的枚举项
     */
    @TableField(exist = false)
    private JSONObject enumsJson = new JSONObject();

    /**
     * 获取所有的枚举项
     * key: 枚举的toString,值:
     * 格式:
     * <pre>
     {
         "STATUS":[
         {
         "enumsVal":"STATUS_DISABLE",
         "code":"0",
         "info":"禁用",
         },
         {
         "enumsVal":"STATUS_ENABLE",
         "code":"1",
         "info":"启用"
         },
         ],
         "DELFLAG":[
         {
         "enumsVal":"DELFLAG_YES",
         "code":"0",
         "info":"未删除",
         },
         {
         "enumsVal":"DELFLAG_NO",
         "code":"1",
         "info":"已删除"
         }
         ]
     }
     * </pre>
     * @return json对象
     */
    public JSONObject getEnumsJson() {
        enumsJson = new JSONObject();
        BasePojoEnum[] enumsArr = BasePojoEnum.values();
        for (BasePojoEnum enumsTemp : enumsArr) {
            String varName = enumsTemp.toString();
            String[] varNameArr = varName.split(ConstatFinalUtil.FINAL_JAVA_ENUMS_SPLIT);
            if (varNameArr.length == 2) {
                /* 条目 */
                JSONObject jsonEntryTemp = new JSONObject();
                jsonEntryTemp.put(ConstatFinalUtil.FINAL_ENUMSVAL, varName);
                jsonEntryTemp.put(ConstatFinalUtil.FINAL_CODE, enumsTemp.getCode());
                jsonEntryTemp.put(ConstatFinalUtil.FINAL_INFO, enumsTemp.getInfo());

                String jsonKey = varNameArr[0];
                /* 源数组 */
                JSONArray jsonArrVal = enumsJson.getJSONArray(jsonKey);
                if (Objects.isNull(jsonArrVal)) {
                    jsonArrVal = new JSONArray();
                }
                jsonArrVal.add(jsonEntryTemp);

                /* 存储最终结果 */
                enumsJson.put(jsonKey, jsonArrVal);
            }
        }
        return enumsJson;
    }

    /**
     * 枚举
     * 根据数据库中存储的code值变成info值
     * @return  状态描述信息
     */
    public String getStatusStr() {
        BasePojoEnum[] enumsArr = BasePojoEnum.values();
        for (BasePojoEnum enumsTemp : enumsArr) {
            String varName = enumsTemp.toString();
            if (varName.startsWith("STATUS_") && enumsTemp.getCode().equalsIgnoreCase(this.getStatus()) ) {
                this.statusStr = enumsTemp.getInfo();
                break;
            }
        }
        return statusStr;
    }

    public String getDelFlagStr() {
        BasePojoEnum[] enumsArr = BasePojoEnum.values();
        for (BasePojoEnum enumsTemp : enumsArr) {
            String varName = enumsTemp.toString();
            if (varName.startsWith("DELFLAG_") && enumsTemp.getCode().equalsIgnoreCase(this.getDelFlag()) ) {
                this.delFlagStr = enumsTemp.getInfo();
                break;
            }
        }
        return delFlagStr;
    }
}
