package com.wang.users.pojo;

/**
 * 示例_朝代表(枚举)
 *
 * @author ZjxMi
 */
public enum AtUsersMessEnum {
    /**
     * 状态:(0:禁用,1:启用)
     */
    STATUS_ENABLE("0", "禁用"),
    STATUS_DISABLE("1", "启用"),

    /**
     * 性别:0:未知,1:男,1:女
     */
    SEX_UNKNOW("0", "未知"),
    SEX_MALE("1", "男"),
    SEX_FEMALE("2", "女"),
    ;
    private String code;
    private String info;

    private AtUsersMessEnum(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}