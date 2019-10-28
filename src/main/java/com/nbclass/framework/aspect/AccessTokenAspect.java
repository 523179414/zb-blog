package com.nbclass.framework.aspect;

import com.nbclass.framework.exception.AuthenticationException;
import com.nbclass.framework.jwt.JwtUtil;
import com.nbclass.framework.util.CookieUtil;
import com.nbclass.framework.util.CoreConst;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * RedisCacheAspect
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Aspect
@Component
public class AccessTokenAspect {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenAspect.class);


    @Autowired
    private JwtUtil jwtUtil;

    /**
     * RedisCache handle
     *
     * @param point
     *
     */
    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            throw new IllegalStateException("当前线程中不存在 Request 上下文");
        }
        HttpServletRequest request = attrs.getRequest();
        String access_token = Optional.ofNullable(CookieUtil.getCookieByName(request, CoreConst.ACCESS_TOKEN)).orElse(request.getHeader(CoreConst.ACCESS_TOKEN));
        try{
            if(StringUtils.isEmpty(access_token) || jwtUtil.verifyToken(access_token)==null){
                throw new AuthenticationException("jwt认证失败");
            }
        }catch (Exception e){
            throw new AuthenticationException("jwt认证失败", e);
        }
        return point.proceed();
    }

    /**
     * pointcut
     *
     */
    @Pointcut(value = "@annotation(com.nbclass.framework.annotation.AccessToken)")
    public void pointcut() {}
}
