package com.wang.common.pojo;

/**
 * 公共的枚举
 * @author ZjxMi
 */
public enum BasePojoEnum{
    /* 状态:(0:禁用,1:启用) */
    STATUS_DISABLE("0","禁用"),

    STATUS_ENABLE("1","启用"),

    /* 逻辑删除(0:未删除,1:已删除) */
    DELFLAG_YES("0","未删除"),

    DELFLAG_NO("1","已删除"),

    /* 逻辑删除(0:未删除,1:已删除) */
    SESS_USERS("usersSess","用户"),

    SESS_ADMINS("adminsSess","管理员"),

    /**
     * 操作类型: 0:删除(逻辑删除),1:还原(修改状态),2:清空(物理删除)
     */
    OPERTYPE_DELETE("0","删除"),

    OPERTYPE_RESTORE("1","还原"),
    OPERTYPE_EMPTY("2","清空"),
    OPERTYPE_UPDATESTATUS("3","批量修改状态"),

    EXECUTE_NO("0","未执行"),

    EXECUTE_YES("1","执行"),
    ;

    private String code;
    private String info;

    private BasePojoEnum(String code, String info) {
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
