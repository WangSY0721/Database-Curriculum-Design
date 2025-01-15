package com.wang.users.pojo;

import com.wang.common.pojo.BaseQuery;
import lombok.Data;

/**
 * 朝代的查询对象
 *
 * @author ZjxMi
 */
@Data
public class AtWorkQuery extends BaseQuery {
    /**
     * 实体类
     */
    private AtWork entity = new AtWork();

    private String selfObj;
    private String empTime;

    /**
     * 是否查询自己的标识
     */
    public static final String FINAL_SELF_OBJ_TRUE = "true";
}
