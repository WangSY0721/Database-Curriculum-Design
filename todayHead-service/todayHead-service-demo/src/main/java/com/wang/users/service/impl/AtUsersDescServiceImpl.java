package com.wang.users.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.common.util.BeanUtil;
import com.wang.common.util.ConstatFinalUtil;
import com.wang.users.mapper.AtUsersDescMapper;
import com.wang.users.pojo.AtUsersDesc;
import com.wang.users.pojo.AtUsersDescEnum;
import com.wang.users.pojo.AtUsersDescQuery;
import com.wang.users.service.AtUsersDescService;
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
public class AtUsersDescServiceImpl extends ServiceImpl<AtUsersDescMapper, AtUsersDesc>
        implements AtUsersDescService {

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
    public List<AtUsersDesc> findList(IPage<AtUsersDesc> page, AtUsersDescQuery query) {
        if (page != null) {
            return this.baseMapper.findList(page, query);
        }
        return this.baseMapper.findList(query);
    }

    @Override
    public AtUsersDesc findOne(AtUsersDescQuery query) {
        AtUsersDesc one = this.baseMapper.findOne(query);
        return one;
    }

    @Override
    public int updateBatch(AtUsersDescQuery query) {
        return this.baseMapper.updateBatch(query);
    }

    @Override
    public int deleteBatch(AtUsersDescQuery query) {
        return this.baseMapper.deleteBatch(query);
    }

    /**
     * 对数据额外处理(非用户填写的数据)
     * @param entity
     */
    private void proccedUpdateData(AtUsersDesc entity){
        if (Objects.isNull(entity.getCreateTime())) {
            entity.setCreateTime(new Date());
        }
        entity.setUpdateTime(new Date());
        /* 判断对象是否为空 */
        if (Objects.isNull(entity.getPubTime())) {
            entity.setPubTime(new Date());
        }

        /* 邮箱不能重复 */
        AtUsersDescQuery paramQuery = new AtUsersDescQuery();
        AtUsersDesc paramEntity = paramQuery.getEntity();
        paramEntity.setEmail(entity.getEmail());
        IPage<AtUsersDesc> paramPage = new Page<>(1, ConstatFinalUtil.BATCH_SIZE);
        List<AtUsersDesc> paramList = this.findList(paramPage, paramQuery);
        if (paramPage.getTotal() > 0) {
            AtUsersDesc dbRecord = paramList.get(0);
            if (Objects.isNull(entity.getId()) ||
                    !dbRecord.getId().equalsIgnoreCase(entity.getId())) {
                /* 邮箱已经存在 */
                throw new RuntimeException("邮箱已存在");
            }
        }
    }

    @Override
    public boolean save(AtUsersDesc entity) {
        this.proccedUpdateData(entity);
        /* 新增加的记录默认未删除 */
        entity.setDelFlag(BasePojoEnum.DELFLAG_YES.getCode());
        entity.setStatus(AtUsersDescEnum.STATUS_ENABLE.getCode());
        return super.save(entity);
    }

    @Override
    public boolean updateById(AtUsersDesc entity) {
        /* 先根据id查询数据库的内容; */
        AtUsersDescQuery paramQuery = new AtUsersDescQuery();
        AtUsersDesc entityParam = paramQuery.getEntity();
        entityParam.setId(entity.getId());
        /* 数据库中查询的结果 */
        AtUsersDesc oneDbResult = this.findOne(paramQuery);
        /*
        * 源对象和目标对象是一个类型(10个字段)
        * 源对象有5个字段有值,目标对象是10个有值
        * 把源对象中的5个字段有值,赋值到目标对象中.(覆盖)
        * */
        oneDbResult = (AtUsersDesc) this.beanUtil.copy(entity, oneDbResult);
        this.proccedUpdateData(oneDbResult);
        return super.updateById(oneDbResult);
    }
}