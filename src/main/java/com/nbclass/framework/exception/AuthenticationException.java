package com.nbclass.framework.exception;


/**
 * 认证异常，未登录
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
