package com.wang.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.demo.mapper.AtDemoDynastyMapper;
import com.wang.demo.pojo.AtDemoDynasty;
import com.wang.demo.pojo.AtDemoDynastyQuery;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.BeanUtil;
import com.wang.demo.service.AtDemoDynastyService;
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
public class AtDemoDynastyServiceImpl extends ServiceImpl<AtDemoDynastyMapper, AtDemoDynasty>
        implements AtDemoDynastyService {

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
    public List<AtDemoDynasty> findList(IPage<AtDemoDynasty> page, AtDemoDynastyQuery query) {
        if (page != null) {
            return this.baseMapper.findList(page, query);
        }
        return this.baseMapper.findList(query);
    }

    @Override
    public AtDemoDynasty findOne(AtDemoDynastyQuery query) {
        return this.baseMapper.findOne(query);
    }

    @Override
    public int updateBatch(AtDemoDynastyQuery query) {
        return this.baseMapper.updateBatch(query);
    }

    @Override
    public int deleteBatch(AtDemoDynastyQuery query) {
        return this.baseMapper.deleteBatch(query);
    }

    /**
     * 对数据额外处理(非用户填写的数据)
     * @param entity
     */
    private void proccedUpdateData(AtDemoDynasty entity){
        if (Objects.isNull(entity.getCreateTime())) {
            entity.setCreateTime(new Date());
        }
        entity.setUpdateTime(new Date());
        /* 判断对象是否为空 */
        if (Objects.isNull(entity.getPubTime())) {
            entity.setPubTime(new Date());
        }
        if (Objects.nonNull(entity.getStYear()) && Objects.nonNull(entity.getEdYear())) {
            /* 如果开国时间和亡国时间没有设置,就报错了 */
            entity.setAge(entity.getEdYear() - entity.getStYear());
        }
    }

    @Override
    public boolean save(AtDemoDynasty entity) {
        this.proccedUpdateData(entity);
        /* 新增加的记录默认未删除 */
        entity.setDelFlag(BasePojoEnum.DELFLAG_YES.getCode());
        return super.save(entity);
    }

    @Override
    public boolean updateById(AtDemoDynasty entity) {
        /* 先根据id查询数据库的内容; */
        AtDemoDynastyQuery paramQuery = new AtDemoDynastyQuery();
        AtDemoDynasty entityParam = paramQuery.getEntity();
        entityParam.setId(entity.getId());
        /* 数据库中查询的结果 */
        AtDemoDynasty oneDbResult = this.findOne(paramQuery);
        /*
        * 源对象和目标对象是一个类型(10个字段)
        * 源对象有5个字段有值,目标对象是10个有值
        * 把源对象中的5个字段有值,赋值到目标对象中.(覆盖)
        * */
        oneDbResult = (AtDemoDynasty) this.beanUtil.copy(entity, oneDbResult);

        this.proccedUpdateData(oneDbResult);
        return super.updateById(oneDbResult);
    }
}