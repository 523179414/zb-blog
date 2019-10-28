package com.nbclass.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * ArticleTag
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Data
public class BlogArticleTag implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 标签表主键
    */
    private Integer tagId;

    /**
    * 文章id
    */
    private Integer articleId;

    /**
    * status
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