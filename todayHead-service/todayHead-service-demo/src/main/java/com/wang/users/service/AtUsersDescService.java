package com.wang.users.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.users.pojo.AtUsersDesc;
import com.wang.users.pojo.AtUsersDescQuery;

import java.util.List;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Service
 * @author ZjxMi
 */
public interface AtUsersDescService extends IService<AtUsersDesc> {
    /**
     * 查询多条记录(分页)
     * @param page 分页对象
     * @param query 查询对象
     * @return  列表集合
     */
    List<AtUsersDesc> findList(IPage<AtUsersDesc> page, AtUsersDescQuery query);

    /**
     * 查询一条记录
     * @param query 查询对象
     * @return  返回一条记录
     */
    AtUsersDesc findOne(AtUsersDescQuery query);

    /**
     * 批量更新操作(还原)
     * @param query
     * @return
     */
    int updateBatch(AtUsersDescQuery query);

    /**
     * 批量删除操作(物理删除)
     * @param query
     * @return
     */
    int deleteBatch(AtUsersDescQuery query);
}
