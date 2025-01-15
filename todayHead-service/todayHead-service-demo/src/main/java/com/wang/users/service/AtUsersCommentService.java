package com.wang.users.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.users.pojo.AtUsersComment;
import com.wang.users.pojo.AtUsersCommentQuery;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Service
 * @author ZjxMi
 */
public interface AtUsersCommentService extends IService<AtUsersComment> {
    /**
     * 查询多条记录(分页)
     * @param page 分页对象
     * @param query 查询对象
     * @return  列表集合
     */
    List<AtUsersComment> findList(IPage<AtUsersComment> page, AtUsersCommentQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回一条记录
     */
    AtUsersComment findOne(AtUsersCommentQuery query);

    /**
     * 批量更新操作(还原)
     * @param query
     * @return
     */
    int updateBatch(AtUsersCommentQuery query);

    /**
     * 批量删除操作(物理删除)
     * @param query
     * @return
     */
    int deleteBatch(AtUsersCommentQuery query);
}
