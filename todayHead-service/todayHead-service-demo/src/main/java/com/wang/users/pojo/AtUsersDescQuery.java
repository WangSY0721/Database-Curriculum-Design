package com.wang.users.pojo;

import com.wang.common.pojo.BaseQuery;
import lombok.Data;

/**
 * 朝代的查询对象
 *
 * @author ZjxMi
 */
@Data
public class AtUsersDescQuery extends BaseQuery {
    /**
     * 实体类
     */
    private AtUsersDesc entity = new AtUsersDesc();

    private String joinJobTime;
    private String empTime;
}
