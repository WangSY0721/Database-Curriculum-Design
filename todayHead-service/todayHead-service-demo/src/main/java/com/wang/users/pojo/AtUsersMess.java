package com.wang.users.pojo;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.common.pojo.BasePojo;
import com.wang.common.util.ConstatFinalUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户表
 *
 * @author ZjxMi
 */
@TableName(value = "at_users_mess")
@Data
public class AtUsersMess extends BasePojo implements Serializable {
    private static final long serialVersionUID = -3964561249800777408L;

    @TableField(value = "fromUsersId")
    private String fromUsersId;
    @TableField(value = "toUsersId")
    private String toUsersId;

    /**
     * 状态
     */
    @TableField(exist = false)
    private String statusStr;

    /**
     * 关联对象引用
     */
    private AtUsersDesc fromUsers;
    private AtUsersDesc toUsers;

    @Override
    public JSONObject getEnumsJson() {
        JSONObject enumsJson = super.getEnumsJson();
        AtUsersMessEnum[] enumsArr = AtUsersMessEnum.values();
        for (AtUsersMessEnum enumsTemp : enumsArr) {
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
        AtUsersMessEnum[] enumsArr = AtUsersMessEnum.values();
        for (AtUsersMessEnum enumsTemp : enumsArr) {
            String varName = enumsTemp.toString();
            if (varName.startsWith("STATUS_") && enumsTemp.getCode().equalsIgnoreCase(this.getStatus()) ) {
                this.statusStr = enumsTemp.getInfo();
                break;
            }
        }
        return statusStr;
    }
}