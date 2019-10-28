package com.nbclass.mapper;

import com.nbclass.model.BlogUser;
import com.nbclass.framework.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Repository
public interface UserMapper extends MyMapper<BlogUser> {
    /**
     * 根据user参数查询用户列表
     * @param user
     * @return list
     */
    List<BlogUser> selectList(BlogUser user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return user
     */
    BlogUser selectByUsername(String username);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return user
     */
    BlogUser selectByUserId(String userId);

    /**
     * 更新最后登录时间
     * @param user
     */
    void updateLastLoginTime(BlogUser user);

    /**
     * 根据用户id更新用户信息
     * @param user
     * @return int
     */
    int updateByUserId(BlogUser user);

    /**
     * 根据角色id查询用户list
     * @param roleId
     * @return list
     */
    List<BlogUser> findByRoleId(String roleId);

}
