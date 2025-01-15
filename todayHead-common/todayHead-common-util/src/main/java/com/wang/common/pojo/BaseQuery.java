package com.wang.common.pojo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 所有查询对象Query的父类
 * @author ZjxMi
 */
@Data
public class BaseQuery {
    /**
     * 当前页
     */
    private Integer current = 1;
    /**
     * 每页条数
     */
    private Integer size = 20;
    /**
     * 操作类型
     */
    private String operType;
    /**
     * 批量操作
     */
    private String[] ids;
    /**
     * 查询关键字
     */
    private String keyword;
    /**
     * 开始时间
     */
    private String stDate;
    /**
     * 结束时间
     */
    private String edDate;
    /**
     * 专门为了接收发布时间(排序)(字符串类型)
     */
    private String pubTime;
    /**
     * 发布时间范围
     */
    private Date pubTimeSt;
    private Date pubTimeEd;

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 以下是常量,所有的一级节点常量
     */
    public static final String PARENT_ROOT_FINA = "root";

    /**
     * 以下是常量
     */
    public static final String OPERTYPE_FINA = "operType";

    /**
     * 以下是常量
     */
    public static final String KEYWORD_FINA = "keyword";

    /**
     * sql语句变量
     */
    public static final String SQL_LIKE = "%";
    /**
     * 操作类型变量
     */
    public static final String FINAL_OPER_UPDATE = "updateInfo";
    /**
     * 操作类型变量
     */
    public static final String FINAL_OPER_UPDATE_FILE = "updateFile";
    /**
     * 操作类型变量
     */
    public static final String FINAL_OPER_UPDATE_Audit = "updateAudit";
    /**
     * 操作类型变量
     */
    public static final String FINAL_OPER_UPDATE_RESETPASS = "updateResetPass";

    /**
     * 添加%
     */
    public void addLike(){
        if (StringUtils.isEmpty(this.keyword)) {
            return;
        }
        if (StringUtils.isNotEmpty(this.keyword)) {
            this.keyword = BaseQuery.SQL_LIKE + this.keyword + BaseQuery.SQL_LIKE;
        }
    }

    /**
     * 移除%
     */
    public void removeLike(){
        if (StringUtils.isEmpty(this.keyword)) {
            return;
        }
        if (this.keyword.startsWith(SQL_LIKE)) {
            this.keyword = this.keyword.substring(1);
        }

        if (this.keyword.endsWith(SQL_LIKE)) {
            this.keyword = this.keyword.substring(0, this.keyword.length() - 1);
        }
    }
}
