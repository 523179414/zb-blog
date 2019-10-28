package com.nbclass.model;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
*  系统配置信息表
* @author nbclass 2019-10-18
*/
@Data
public class BlogConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;

    /**
    * key
    */
    private String sysKey;

    /**
    * value
    */
    private String sysValue;

    /**
    * 备注
    */
    private String remark;

    /**
    * 状态  1-有效，0-无效
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


}