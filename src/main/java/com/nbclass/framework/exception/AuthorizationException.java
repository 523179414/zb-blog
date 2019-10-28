package com.nbclass.framework.exception;


/**
 * 无权限异常
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
