package com.wang.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.users.pojo.AtUsersCollect;
import com.wang.users.pojo.AtUsersCollectQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Mapper
* @author ZjxMi
*/
@Mapper
public interface AtUsersCollectMapper extends BaseMapper<AtUsersCollect> {

    /**
     * 查询多条记录
     * @param page
     * @param query
     * @return  集合
     */
    List<AtUsersCollect> findList(IPage<AtUsersCollect> page, @Param("param") AtUsersCollectQuery query);

    /**
     * 查询全部(不分页)
     * @param query
     * @return
     */
    List<AtUsersCollect> findList(@Param("param") AtUsersCollectQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回结果
     */
    AtUsersCollect findOne(@Param("param") AtUsersCollectQuery query);

    /**
     * 批量删除
     * @param query
     * @return
     */
    int deleteBatch(@Param("param") AtUsersCollectQuery query);

    /**
     * 批量更新
     * @param query
     * @return
     */
    int updateBatch(@Param("param") AtUsersCollectQuery query);
}