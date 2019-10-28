package com.nbclass.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Category
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Data
public class BlogCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 分类名称
    */
    private String name;

    /**
    * 分类别名
    */
    private String aliasName;

    /**
    * 分类描述
    */
    private String description;

    /**
    * pid
    */
    private Integer pid;

    /**
    * sort
    */
    private Boolean sort;

    /**
    * 1-栏目，2-页面
    */
    private Boolean type;

    /**
    * 模板名称
    */
    private String template;

    /**
    * 用户状态：1有效; 0删除
    */
    private Boolean status;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
     * 父节点
     */
    @Transient
    private BlogCategory parent;

    /**
     * 子节点
     */
    @Transient
    private List<BlogCategory> nodes;


}