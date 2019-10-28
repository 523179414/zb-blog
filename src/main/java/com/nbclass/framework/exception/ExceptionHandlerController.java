package com.nbclass.framework.exception;

import com.nbclass.enums.ResponseStatus;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常拦截控制器
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(ZbException.class)
    public String handleZb(Exception e, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", ResponseStatus.ERROR.getCode());
        Map<String,Object> map = new HashMap<>(2);
        map.put("status", ResponseStatus.ERROR.getCode());
        map.put("msg", StringUtils.isNotBlank(e.getMessage())? e.getMessage() : ResponseStatus.ERROR.getMessage());
        logger.error(e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleArticle(Exception e, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", ResponseStatus.NOT_FOUND.getCode());
        return "forward:/error";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthentication(Exception e, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", ResponseStatus.TOKEN_INVALID.getCode());
        return "forward:/error";
    }
}
