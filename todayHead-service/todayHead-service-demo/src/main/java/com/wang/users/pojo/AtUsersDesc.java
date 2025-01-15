package com.wang.users.pojo;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.common.pojo.BasePojo;
import com.wang.common.util.ConstatFinalUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 用户表
 *
 * @author ZjxMi
 */
@TableName(value = "at_users_desc")
@Data
public class AtUsersDesc extends BasePojo implements Serializable {
    private static final long serialVersionUID = -79734603532412079L;

    @TableField(value = "name")
    private String name;
    @TableField(value = "nickName")
    private String nickName;
    @TableField(value = "email")
    private String email;
    @TableField(value = "password")
    private String password;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "lastLoginTime")
    private Date lastLoginTime;
    @TableField(value = "sex")
    private String sex;

    /**
     * 状态
     */
    @TableField(exist = false)
    private String statusStr;
    @TableField(exist = false)
    private String sexStr;

    @Override
    public JSONObject getEnumsJson() {
        JSONObject enumsJson = super.getEnumsJson();
        AtUsersDescEnum[] enumsArr = AtUsersDescEnum.values();
        for (AtUsersDescEnum enumsTemp : enumsArr) {
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
        AtUsersDescEnum[] enumsArr = AtUsersDescEnum.values();
        for (AtUsersDescEnum enumsTemp : enumsArr) {
            String varName = enumsTemp.toString();
            if (varName.startsWith("STATUS_") && enumsTemp.getCode().equalsIgnoreCase(this.getStatus()) ) {
                this.statusStr = enumsTemp.getInfo();
                break;
            }
        }
        return statusStr;
    }

    public String getSexStr() {
        AtUsersDescEnum[] enumsArr = AtUsersDescEnum.values();
        for (AtUsersDescEnum enumsTemp : enumsArr) {
            String varName = enumsTemp.toString();
            if (varName.startsWith("SEX_") && enumsTemp.getCode().equalsIgnoreCase(this.getSex()) ) {
                this.sexStr = enumsTemp.getInfo();
                break;
            }
        }
        return sexStr;
    }
}