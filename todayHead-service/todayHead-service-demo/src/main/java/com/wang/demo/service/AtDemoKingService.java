package com.wang.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.demo.pojo.AtDemoKing;
import com.wang.demo.pojo.AtDemoKingQuery;

import java.util.List;

/**
 * 针对表【at_demo_king(示例_皇上表)】的数据库操作Service
 *
 * @author ZjxMi
 */
public interface AtDemoKingService extends IService<AtDemoKing> {
    /**
     * 查询多条记录(分页)
     * @param page 分页对象
     * @param query 查询对象
     * @return  列表集合
     */
    List<AtDemoKing> findList(IPage<AtDemoKing> page, AtDemoKingQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回一条记录
     */
    AtDemoKing findOne(AtDemoKingQuery query);

    /**
     * 批量更新操作(还原)
     * @param query
     * @return
     */
    int updateBatch(AtDemoKingQuery query);

    /**
     * 批量删除操作(物理删除)
     * @param query
     * @return
     */
    int deleteBatch(AtDemoKingQuery query);
}
