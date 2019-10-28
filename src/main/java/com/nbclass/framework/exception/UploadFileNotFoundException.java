package com.nbclass.framework.exception;

/**
 * 上传文件异常
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public class UploadFileNotFoundException extends RuntimeException {
    
    public UploadFileNotFoundException() {
    }
    
    public UploadFileNotFoundException(String message) {
        super(message);
    }

    public UploadFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
