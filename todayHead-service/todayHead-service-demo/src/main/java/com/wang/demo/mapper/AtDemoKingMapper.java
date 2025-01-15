package com.wang.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.demo.pojo.AtDemoKing;
import com.wang.demo.pojo.AtDemoKingQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【at_demo_king(示例_皇上表)】的数据库操作Mapper
 *
 * @author ZjxMi
 */
@Mapper
public interface AtDemoKingMapper extends BaseMapper<AtDemoKing> {

    /**
     * 查询多条记录
     * @param page
     * @param query
     * @return  集合
     */
    List<AtDemoKing> findList(IPage<AtDemoKing> page, @Param("param") AtDemoKingQuery query);

    /**
     * 批量删除
     * @param query
     * @return
     */
    int deleteBatch(@Param("param") AtDemoKingQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回结果
     */
    AtDemoKing findOne(@Param("param") AtDemoKingQuery query);

    /**
     * 批量更新
     * @param query
     * @return
     */
    int updateBatch(AtDemoKingQuery query);
}