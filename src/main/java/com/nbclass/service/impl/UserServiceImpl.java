package com.nbclass.service.impl;

import com.nbclass.enums.TemplateType;
import com.nbclass.framework.jwt.JwtUtil;
import com.nbclass.mapper.UserMapper;
import com.nbclass.model.BlogUser;
import com.nbclass.service.MailService;
import com.nbclass.service.UserService;
import com.nbclass.framework.util.*;
import com.nbclass.vo.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MailService mailService;

    @Resource
    private  UserMapper userMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public ResponseVo add(BlogUser user) {
        if(userMapper.selectByUsername(user.getUsername())!=null ){
            return ResponseUtil.error("用户已存在!");
        }
        user.setCreateTime(new Date());
        user.withUserId(UUIDUtil.getUniqueIdByUUId())
            .withSalt(UUIDUtil.uuid())
            .withNickname(user.getUsername())
            .withStatus(CoreConst.STATUS_VALID)
            .withCreateTime(new Date())
        ;
        PasswordHelper.encryptPassword(user);
        userMapper.insertSelective(user);
        if(StringUtils.isNotBlank(user.getEmail()) && ValidatorUtil.isEmail(user.getEmail())){
            Map<String, Object> map = new HashMap<>();
            map.put("username",user.getUsername());
            map.put("dateNow",new Date());
            mailService.sendTemplateMail(TemplateType.RegisterSuccess, user.getEmail(), TemplateType.RegisterSuccess.getDesc(), map);
        }
        return ResponseUtil.success("添加成功");
    }

    @Override
    public ResponseVo login(BlogUser login) {
        BlogUser user = userMapper.selectByUsername(login.getUsername());
        if(user==null || !PasswordHelper.verify(login.getPassword(),user)){
            return ResponseUtil.error("用户名或者密码错误");
        }
        String token = jwtUtil.genToken(user);
        return ResponseUtil.success("登录成功",token);
    }

}
