package com.wang.demo.pojo;

import com.wang.common.pojo.BaseQuery;
import lombok.Data;

/**
 * 朝代的查询对象
 *
 * @author ZjxMi
 */
@Data
public class AtDemoKingQuery extends BaseQuery {
    /**
     * 实体类
     */
    private AtDemoKing entity = new AtDemoKing();
}
