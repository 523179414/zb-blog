package com.nbclass.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
*  zb_comment
* @author nbclass 2019-10-18
*/
@Data
public class BlogComment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 被评论的文章或者页面的id(-1:留言板)
    */
    private Integer sid;

    /**
     * 主评论id
     */
    private Integer mid;

    /**
    * 父级评论的id
    */
    private Integer parentId;

    /**
     * 父级评论的昵称 （冗余）
     */
    private String parentNickname;

    /**
    * 评论人的qq（未登录用户）
    */
    private String qq;

    /**
    * 评论人的昵称（未登录用户）
    */
    private String nickname;

    /**
    * 评论人的头像地址
    */
    private String avatar;

    /**
    * 评论人的邮箱地址（未登录用户）
    */
    private String email;

    /**
    * 评论时的ip
    */
    private String ip;

    /**
    * 评论时的系统类型
    */
    private String os;

    /**
    * 评论时的浏览器类型
    */
    private String browser;

    /**
    * 评论的内容
    */
    private String content;

    /**
     * 楼层
     */
    private Integer floor;
    /**
    * 支持（赞）数
    */
    private Integer supportNum;

    /**
    * 反对（踩）
    */
    private Integer opposeNum;

    /**
    * 0-待审核，1-通过，2-驳回
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

    @Transient
    BlogComment parent;

    @Transient
    List<BlogComment> nodes;

    @Transient
    BlogArticle article;
}