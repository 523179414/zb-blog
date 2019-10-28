package com.nbclass.framework.exception;


/**
 * 文章404 Exception
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
