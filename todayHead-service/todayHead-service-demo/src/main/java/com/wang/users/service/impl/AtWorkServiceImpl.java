package com.wang.users.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.BeanUtil;
import com.wang.users.mapper.AtWorkMapper;
import com.wang.users.pojo.AtWork;
import com.wang.users.pojo.AtWorkEnum;
import com.wang.users.pojo.AtWorkQuery;
import com.wang.users.service.AtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 针对表【at_demo_dynasty(示例_朝代表)】的数据库操作Service实现
 *
 * @author ZjxMi
 */
@Service
public class AtWorkServiceImpl extends ServiceImpl<AtWorkMapper, AtWork>
        implements AtWorkService {

    @Autowired
    private BeanUtil beanUtil;

    /**
     * model2: jsp --> servlet -->service --> dao --> db, 我们自己封装了一个 PageInfoUtil;
     * MybatisPlus: 不再需要调用 PageInfoUtil,老代码改造的时候是为了整合代码,如果没有老代码,直接写的新代码,采用下面的方式来搞
     *
     * @param page
     * @param query 查询对象
     * @return
     */
    @Override
    public List<AtWork> findList(IPage<AtWork> page, AtWorkQuery query) {
        if (page != null) {
            return this.baseMapper.findList(page, query);
        }
        return this.baseMapper.findList(query);
    }

    @Override
    public AtWork findOne(AtWorkQuery query) {
        AtWork one = this.baseMapper.findOne(query);
        return one;
    }

    @Override
    public int updateBatch(AtWorkQuery query) {
        return this.baseMapper.updateBatch(query);
    }

    @Override
    public int deleteBatch(AtWorkQuery query) {
        return this.baseMapper.deleteBatch(query);
    }

    /**
     * 对数据额外处理(非用户填写的数据)
     * @param entity
     */
    private void proccedUpdateData(AtWork entity){
        if (Objects.isNull(entity.getCreateTime())) {
            entity.setCreateTime(new Date());
        }
        entity.setUpdateTime(new Date());
        /* 判断对象是否为空 */
        if (Objects.isNull(entity.getPubTime())) {
            entity.setPubTime(new Date());
        }
    }

    @Override
    public boolean save(AtWork entity) {
        this.proccedUpdateData(entity);
        /* 新增加的记录默认未删除 */
        entity.setDelFlag(BasePojoEnum.DELFLAG_YES.getCode());
        entity.setStatus(AtWorkEnum.STATUS_ENABLE.getCode());
        return super.save(entity);
    }

    @Override
    public boolean updateById(AtWork entity) {
        /* 先根据id查询数据库的内容; */
        AtWorkQuery paramQuery = new AtWorkQuery();
        AtWork entityParam = paramQuery.getEntity();
        entityParam.setId(entity.getId());
        /* 数据库中查询的结果 */
        AtWork oneDbResult = this.findOne(paramQuery);
        entity.setUsers(null);
        oneDbResult.setUsers(null);
        /*
        * 源对象和目标对象是一个类型(10个字段)
        * 源对象有5个字段有值,目标对象是10个有值
        * 把源对象中的5个字段有值,赋值到目标对象中.(覆盖)
        * */
        oneDbResult = (AtWork) this.beanUtil.copy(entity, oneDbResult);
        this.proccedUpdateData(oneDbResult);
        return super.updateById(oneDbResult);
    }
}