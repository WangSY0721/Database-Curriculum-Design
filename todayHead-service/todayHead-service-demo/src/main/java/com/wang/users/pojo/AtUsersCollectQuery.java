package com.wang.users.pojo;

import com.wang.common.pojo.BaseQuery;
import lombok.Data;

/**
 * 朝代的查询对象
 *
 * @author ZjxMi
 */
@Data
public class AtUsersCollectQuery extends BaseQuery {
    /**
     * 实体类
     */
    private AtUsersCollect entity = new AtUsersCollect();

    private String joinJobTime;
    private String empTime;
}
