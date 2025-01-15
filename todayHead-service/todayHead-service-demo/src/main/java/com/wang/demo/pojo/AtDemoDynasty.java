package com.wang.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wang.common.pojo.BasePojo;
import lombok.Data;

import java.io.Serializable;

/**
 * 示例_朝代表
 *
 * @author ZjxMi
 */
@TableName(value = "at_demo_dynasty")
@Data
public class AtDemoDynasty extends BasePojo implements Serializable {
    private static final long serialVersionUID = -5781257487230051345L;

    /**
     * 首都
     */
    @TableField(value = "capital")
    private String capital;

    /**
     * 开国时间
     */
    @TableField(value = "stYear")
    private Integer stYear;

    /**
     * 亡国时间
     */
    @TableField(value = "edYear")
    private Integer edYear;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;
}