package com.nbclass.framework.exception;


/**
 * ZbException
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public class ZbException extends RuntimeException {

    public ZbException() {
        super();
    }

    public ZbException(String message) {
        super(message);
    }

    public ZbException(String message, Throwable cause) {
        super(message, cause);
    }
}
