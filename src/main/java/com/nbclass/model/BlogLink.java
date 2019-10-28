package com.nbclass.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
*  zb_link
* @author nbclass 2019-10-18
*/
@Data
public class BlogLink implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 链接名
    */
    private String name;

    /**
    * 链接地址
    */
    private String url;

    /**
    * 链接介绍
    */
    private String description;

    /**
    * 友链图片地址
    */
    private String img;

    /**
    * 友链站长邮箱
    */
    private String email;

    /**
    * 友链站长qq
    */
    private String qq;

    /**
    * 1-管理员添加 2-自助申请
    */
    private Boolean origin;

    /**
    * 状态
    */
    private Boolean status;

    /**
    * 添加时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;


}