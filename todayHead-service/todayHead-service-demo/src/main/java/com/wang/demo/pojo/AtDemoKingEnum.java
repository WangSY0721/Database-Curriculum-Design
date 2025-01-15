package com.wang.demo.pojo;

/**
 * 示例_朝代表(枚举)
 *
 * @author ZjxMi
 */
public enum AtDemoKingEnum {
    ;
    private String code;
    private String info;

    private AtDemoKingEnum(String code, String info) {
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