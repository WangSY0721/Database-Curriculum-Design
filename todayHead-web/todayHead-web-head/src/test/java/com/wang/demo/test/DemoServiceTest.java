package com.wang.demo.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.demo.pojo.AtDemoDynastyQuery;
import com.wang.demo.pojo.AtDemoKing;
import com.wang.demo.pojo.AtDemoKingQuery;
import com.wang.common.pojo.BasePojoEnum;
import com.wang.demo.pojo.AtDemoDynasty;
import com.wang.demo.service.AtDemoDynastyService;
import com.wang.demo.service.AtDemoKingService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 示例模块的测试类
 */
@Log4j2
@SpringBootTest
public class DemoServiceTest {
    @Autowired
    private AtDemoDynastyService demoDynastyService;
    @Autowired
    private AtDemoKingService demoKingService;

    /**
     * 查询朝代列表
     */
    @Test
    public void findDynastyOtherList(){
        List<AtDemoDynasty> dataList = this.demoDynastyService.list();
        /* 查询数据 */
        for (AtDemoDynasty dataTemp : dataList) {
            log.info("==记录:{}==", dataTemp);
        }
    }

    /**
     * 查询多条记录(朝代);
     * <pre>
     *     1.查询多条记录不分页
     *     2.查询多条记录分页
     *     3. 查询多条记录搜索(分页和不分页)
     * </pre>
     */
    @Test
    public void findDynastyList(){
        /* 分页对象 */
        IPage<AtDemoDynasty> paramPage = new Page<>(1, 20);
        /* 查询对象 */
        AtDemoDynastyQuery paramQuery = new AtDemoDynastyQuery();
        paramQuery.setKeyword("1");
        AtDemoDynasty entity = paramQuery.getEntity();
        entity.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        entity.setDelFlag(BasePojoEnum.DELFLAG_YES.getCode());
        List<AtDemoDynasty> dataList = this.demoDynastyService.findList(null, paramQuery);
        /* 查询数据 */
        for (AtDemoDynasty dataTemp : dataList) {
            log.info("==记录:{}==", dataTemp);
        }
        log.info("分页信息:总条数:{},总页数:{},当前页:{},每页条数:{}",
                paramPage.getTotal(), paramPage.getPages(), paramPage.getCurrent(), paramPage.getSize());
    }

    /**
     * 查询一条记录(朝代);
     */
    @Test
    public void getDynastyById(){
        AtDemoDynasty data = this.demoDynastyService.getById(1);
        log.info("==记录:{}==", data);
    }

    /**
     * 保存一条记录(朝代);
     */
    @Test
    public void saveOneDynasty(){
        AtDemoDynasty demoDynasty = new AtDemoDynasty();
        demoDynasty.setName("北宋");
//        demoDynasty.setStYear(960);
//        demoDynasty.setEdYear(1127);
        demoDynasty.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        demoDynasty.setCapital("开封");
        boolean dbFlag = this.demoDynastyService.save(demoDynasty);
        log.info("==dbFlag结果:{},id:{}==", dbFlag, demoDynasty.getId());
    }

    /**
     * 更新一条记录(朝代);
     */
    @Test
    public void updateOneDynasty(){
        AtDemoDynasty demoDynasty = this.demoDynastyService.getById("1733769193788035073");
        demoDynasty.setName("南宋");
        demoDynasty.setStYear(1127);
        demoDynasty.setEdYear(1279);
        demoDynasty.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        demoDynasty.setCapital("杭州");
        boolean dbFlag = this.demoDynastyService.updateById(demoDynasty);
        log.info("==dbFlag结果:{},id:{}==", dbFlag, demoDynasty.getId());
    }

    /**
     * 删除一条记录(朝代);
     */
    @Test
    public void deleteOneDynasty(){
        boolean dbFlag = this.demoDynastyService.removeById("1733769193788035073");
        log.info("==dbFlag结果:{}", dbFlag);
    }

    /**
     * 删除一条记录(朝代);
     */
    @Test
    public void deleteBatchDynasty(){
        AtDemoDynastyQuery query = new AtDemoDynastyQuery();
        AtDemoDynasty entity = query.getEntity();
        entity.setId("1733769193788035073");
        int dbFlag = this.demoDynastyService.deleteBatch(query);
        log.info("==dbFlag结果:{}", dbFlag);
    }

    /**
     * 查询朝代列表
     */
    @Test
    public void findKingOtherList(){
        List<AtDemoKing> dataList = this.demoKingService.list();
        /* 查询数据 */
        for (AtDemoKing dataTemp : dataList) {
            log.info("==记录:{}==", dataTemp);
        }
    }

    /**
     * 查询多条记录(朝代);
     * <pre>
     *     1.查询多条记录不分页
     *     2.查询多条记录分页
     *     3. 查询多条记录搜索(分页和不分页)
     * </pre>
     */
    @Test
    public void findKingList(){
        /* 分页对象 */
        IPage<AtDemoKing> paramPage = new Page<>(1, 20);
        /* 查询对象 */
        AtDemoKingQuery paramQuery = new AtDemoKingQuery();
        paramQuery.setKeyword("1");
        AtDemoKing entity = paramQuery.getEntity();
        entity.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        entity.setDelFlag(BasePojoEnum.DELFLAG_YES.getCode());
        List<AtDemoKing> dataList = this.demoKingService.findList(null, paramQuery);
        /* 查询数据 */
        for (AtDemoKing dataTemp : dataList) {
            log.info("==记录:{}==", dataTemp);
        }
        log.info("分页信息:总条数:{},总页数:{},当前页:{},每页条数:{}",
                paramPage.getTotal(), paramPage.getPages(), paramPage.getCurrent(), paramPage.getSize());
    }

    /**
     * 查询一条记录(朝代);
     */
    @Test
    public void findOneKing(){
        AtDemoKingQuery query = new AtDemoKingQuery();
        AtDemoKing entity = query.getEntity();
        entity.setId("1734224865193476098");
        AtDemoKing data = this.demoKingService.findOne(query);
        log.info("==记录:{}==", data.getName());
        AtDemoDynasty dynasty = data.getDynasty();
        log.info("==朝代名称:{}==", dynasty.getName());
    }

    @Test
    public void getById(){
        AtDemoKing data = this.demoKingService.getById("1734224865193476098");
        log.info("==记录:{}==", data.getName());
        AtDemoDynasty dynasty = data.getDynasty();
        log.info("==朝代名称:{}==", dynasty.getName());
    }

    /**
     * 保存一条记录(朝代);
     */
    @Test
    public void saveOneKing(){
        AtDemoKing demoKing = new AtDemoKing();
        demoKing.setName("赵匡胤");
//        demoKing.setStYear(960);
//        demoKing.setEdYear(1127);
        demoKing.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        boolean dbFlag = this.demoKingService.save(demoKing);
        log.info("==dbFlag结果:{},id:{}==", dbFlag, demoKing.getId());
    }

    /**
     * 更新一条记录(朝代);
     */
    @Test
    public void updateOneKing(){
        AtDemoKing demoKing = this.demoKingService.getById("1734222821816549378");
        demoKing.setName("南宋");
        demoKing.setStatus(BasePojoEnum.STATUS_ENABLE.getCode());
        boolean dbFlag = this.demoKingService.updateById(demoKing);
        log.info("==dbFlag结果:{},id:{}==", dbFlag, demoKing.getId());
    }

    /**
     * 删除一条记录(朝代);
     */
    @Test
    public void deleteOneKing(){
        boolean dbFlag = this.demoKingService.removeById("1734222821816549378");
        log.info("==dbFlag结果:{}", dbFlag);
    }

    /**
     * 删除一条记录(朝代);
     */
    @Test
    public void deleteBatchKing(){
        AtDemoKingQuery query = new AtDemoKingQuery();
        AtDemoKing entity = query.getEntity();
        entity.setId("1734222821816549378");
        int dbFlag = this.demoKingService.deleteBatch(query);
        log.info("==dbFlag结果:{}", dbFlag);
    }
}
