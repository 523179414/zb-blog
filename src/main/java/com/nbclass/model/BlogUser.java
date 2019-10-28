package com.nbclass.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * User
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Data
public class BlogUser {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐值
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 状态：1有效; 0无效
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

    public BlogUser withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BlogUser withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public BlogUser withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public BlogUser withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public BlogUser withCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BlogUser withUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }


}