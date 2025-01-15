package com.wang.users.pojo;

import com.wang.common.pojo.BaseQuery;
import lombok.Data;

/**
 * 朝代的查询对象
 *
 * @author ZjxMi
 */
@Data
public class AtUsersMessQuery extends BaseQuery {
    /**
     * 实体类
     */
    private AtUsersMess entity = new AtUsersMess();

    private String fromUsersFlag;
    private String toUsersFlag;

    public static final String FINAL_TRUE = "true";
}
