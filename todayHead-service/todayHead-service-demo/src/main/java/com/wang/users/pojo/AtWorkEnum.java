package com.wang.users.pojo;

/**
 * 示例_朝代表(枚举)
 *
 * @author ZjxMi
 */
public enum AtWorkEnum {
    /**
     * 状态:(0:禁用,1:启用)
     */
    STATUS_ENABLE("1", "启用"),
    STATUS_DISABLE("0", "禁用"),

    ;
    private String code;
    private String info;

    private AtWorkEnum(String code, String info) {
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