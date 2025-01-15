package com.wang.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.users.pojo.AtUsersDesc;
import com.wang.users.pojo.AtUsersDescQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Mapper
* @author ZjxMi
*/
@Mapper
public interface AtUsersDescMapper extends BaseMapper<AtUsersDesc> {

    /**
     * 查询多条记录
     * @param page
     * @param query
     * @return  集合
     */
    List<AtUsersDesc> findList(IPage<AtUsersDesc> page, @Param("param") AtUsersDescQuery query);

    /**
     * 查询全部(不分页)
     * @param query
     * @return
     */
    List<AtUsersDesc> findList(@Param("param") AtUsersDescQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回结果
     */
    AtUsersDesc findOne(@Param("param") AtUsersDescQuery query);

    /**
     * 批量删除
     * @param query
     * @return
     */
    int deleteBatch(@Param("param") AtUsersDescQuery query);

    /**
     * 批量更新
     * @param query
     * @return
     */
    int updateBatch(@Param("param") AtUsersDescQuery query);
}