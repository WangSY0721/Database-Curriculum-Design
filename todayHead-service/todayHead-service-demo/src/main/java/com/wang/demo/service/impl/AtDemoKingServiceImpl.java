package com.wang.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.demo.mapper.AtDemoKingMapper;
import com.wang.demo.pojo.AtDemoKing;
import com.wang.demo.pojo.AtDemoKingQuery;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.demo.service.AtDemoKingService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 针对表【at_demo_king(示例_皇上表)】的数据库操作Service实现
 *
 * @author ZjxMi
 */
@Service
public class AtDemoKingServiceImpl extends ServiceImpl<AtDemoKingMapper, AtDemoKing>
        implements AtDemoKingService {

    /**
     * model2: jsp --> servlet -->service --> dao --> db, 我们自己封装了一个 PageInfoUtil;
     * MybatisPlus: 不再需要调用 PageInfoUtil,老代码改造的时候是为了整合代码,如果没有老代码,直接写的新代码,采用下面的方式来搞
     *
     * @param page
     * @param query 查询对象
     * @return
     */
    @Override
    public List<AtDemoKing> findList(IPage<AtDemoKing> page, AtDemoKingQuery query) {
        return this.baseMapper.findList(page, query);
    }

    @Override
    public AtDemoKing findOne(AtDemoKingQuery query) {
        return this.baseMapper.findOne(query);
    }

    @Override
    public int updateBatch(AtDemoKingQuery query) {
        return this.baseMapper.updateBatch(query);
    }

    @Override
    public int deleteBatch(AtDemoKingQuery query) {
        return this.baseMapper.deleteBatch(query);
    }

    /**
     * 对数据额外处理(非用户填写的数据)
     * @param entity
     */
    private void proccedUpdateData(AtDemoKing entity){
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
    public boolean save(AtDemoKing entity) {
        this.proccedUpdateData(entity);
        /* 新增加的记录默认未删除 */
        entity.setDelFlag(BasePojoEnum.DELFLAG_YES.getCode());
        return super.save(entity);
    }

    @Override
    public boolean updateById(AtDemoKing entity) {
        this.proccedUpdateData(entity);
        return super.updateById(entity);
    }
}




