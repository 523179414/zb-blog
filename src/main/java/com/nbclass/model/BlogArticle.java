package com.nbclass.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Article
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Data
public class BlogArticle implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 分类名称
    */
    private String title;

    /**
    * intro
    */
    private String intro;

    /**
    * 封面图
    */
    private String cover;

    /**
    * 文章内容
    */
    private String content;

    /**
    * markdown版的文章内容
    */
    private String contentMd;

    /**
    * 1-富文本，2-markdown
    */
    private Boolean editorType;

    /**
    * 路径别名
    */
    private String aliasName;

    /**
    * 分类id
    */
    private String categoryId;

    /**
    * 是否置顶，1-是，0-否
    */
    private Boolean isTop;

    /**
    * 是否推荐，1-是，0-否
    */
    private Boolean isRec;

    /**
    * 1-公开，2-私密
    */
    private Boolean isPublic;

    /**
    * tinyint(1)
    */
    private Boolean isComment;

    /**
    * 文章类型，1-图文，2-视频
    */
    private Boolean type;

    /**
    * 视频地址
    */
    private String video;

    /**
    * 1-原创，2-转载
    */
    private Boolean origin;

    /**
    * 阅读数
    */
    private Integer lookNum;

    /**
    * 支持（赞）数
    */
    private Integer supportNum;

    /**
    * 反对（踩）
    */
    private Integer opposeNum;

    /**
    * _num int(10)
    */
    private Integer commentNum;

    /**
     * 文章模板
     */
    private String template;

    /**
    * seo_key
    */
    private String seoKey;

    /**
    * seo_desc
    */
    private String seoDesc;

    /**
    * 用户状态：1有效; 0-草稿
    */
    private Integer status;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    @Transient
    private BlogCategory category;

    @Transient
    private List<BlogTag> tags;
}