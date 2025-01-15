package com.wang.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.common.pojo.BasePojo;
import lombok.Data;

import java.io.Serializable;

/**
 * 示例_皇上表
 * @author ZjxMi
 */
@TableName(value ="at_demo_king")
@Data
public class AtDemoKing extends BasePojo implements Serializable {
    private static final long serialVersionUID = -138205056648860949L;

    /**
     * 朝代Id
     */
    @TableField(value = "dynastyId")
    private String dynastyId;

    /**
     * 庙号
     */
    @TableField(value = "miaoHao")
    private String miaoHao;

    /**
     * 年号
     */
    @TableField(value = "nianHao")
    private String nianHao;

    /**
     * 关联关系;
     * 皇上和朝代关系:多对一
     * 表示此属性在表里面找不到对应的字段
     */
    @TableField(exist = false)
    private AtDemoDynasty dynasty;
}