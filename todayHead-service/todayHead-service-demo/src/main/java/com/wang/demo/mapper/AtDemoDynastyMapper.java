package com.wang.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.demo.pojo.AtDemoDynasty;
import com.wang.demo.pojo.AtDemoDynastyQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Mapper
* @author ZjxMi
*/
@Mapper
public interface AtDemoDynastyMapper extends BaseMapper<AtDemoDynasty> {

    /**
     * 查询多条记录
     * @param page
     * @param query
     * @return  集合
     */
    List<AtDemoDynasty> findList(IPage<AtDemoDynasty> page, @Param("param") AtDemoDynastyQuery query);

    /**
     * 查询全部(不分页)
     * @param query
     * @return
     */
    List<AtDemoDynasty> findList(@Param("param") AtDemoDynastyQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回结果
     */
    AtDemoDynasty findOne(@Param("param") AtDemoDynastyQuery query);

    /**
     * 批量删除
     * @param query
     * @return
     */
    int deleteBatch(@Param("param") AtDemoDynastyQuery query);

    /**
     * 批量更新
     * @param query
     * @return
     */
    int updateBatch(@Param("param") AtDemoDynastyQuery query);
}