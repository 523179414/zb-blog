package com.nbclass.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
*  zb_slider
* @author nbclass 2019-10-18
*/
@Data
public class BlogSlider implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 内容
    */
    private String title;

    /**
    * img
    */
    private String img;

    /**
    * 是否有链接
    */
    private String url;

    /**
    * 1-有效，0-无效
    */
    private Boolean status;

    /**
    * 1-系统公告，2-轮播
    */
    private Boolean type;

    /**
    * create_time
    */
    private Date createTime;

    /**
    * update_time
    */
    private Date updateTime;


}