package com.nbclass.framework.jwt;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * JwtUser
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-12
 */
@Data
public class JwtUser implements Serializable {
    private static final long serialVersionUID = 8625710122020554179L;

    private String userId;
    private String username;
    private String avatar;
    private Integer isAdmin;
    private Date IssuedAt;
    private Date Expiration;

}
