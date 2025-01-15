package com.wang.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.users.pojo.AtUsersMess;
import com.wang.users.pojo.AtUsersMessQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Mapper
 * @author ZjxMi
 */
@Mapper
public interface AtUsersMessMapper extends BaseMapper<AtUsersMess> {

    /**
     * 查询多条记录
     * @param page
     * @param query
     * @return  集合
     */
    List<AtUsersMess> findList(IPage<AtUsersMess> page, @Param("param") AtUsersMessQuery query);

    /**
     * 查询全部(不分页)
     * @param query
     * @return
     */
    List<AtUsersMess> findList(@Param("param") AtUsersMessQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回结果
     */
    AtUsersMess findOne(@Param("param") AtUsersMessQuery query);

    /**
     * 批量删除
     * @param query
     * @return
     */
    int deleteBatch(@Param("param") AtUsersMessQuery query);

    /**
     * 批量更新
     * @param query
     * @return
     */
    int updateBatch(@Param("param") AtUsersMessQuery query);
}