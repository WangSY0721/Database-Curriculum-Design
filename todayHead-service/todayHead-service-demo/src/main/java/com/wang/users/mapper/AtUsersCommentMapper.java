package com.wang.users.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wang.users.pojo.AtUsersComment;
import com.wang.users.pojo.AtUsersCommentQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Mapper
* @author ZjxMi
*/
@Mapper
public interface AtUsersCommentMapper extends BaseMapper<AtUsersComment> {

    /**
     * 查询多条记录
     * @param page
     * @param query
     * @return  集合
     */
    List<AtUsersComment> findList(IPage<AtUsersComment> page, @Param("param") AtUsersCommentQuery query);

    /**
     * 查询全部(不分页)
     * @param query
     * @return
     */
    List<AtUsersComment> findList(@Param("param") AtUsersCommentQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回结果
     */
    AtUsersComment findOne(@Param("param") AtUsersCommentQuery query);

    /**
     * 批量删除
     * @param query
     * @return
     */
    int deleteBatch(@Param("param") AtUsersCommentQuery query);

    /**
     * 批量更新
     * @param query
     * @return
     */
    int updateBatch(@Param("param") AtUsersCommentQuery query);
}