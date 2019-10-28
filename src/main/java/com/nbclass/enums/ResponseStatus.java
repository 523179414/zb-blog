package com.nbclass.enums;

/**
 * ResponseStatus
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
public enum ResponseStatus {

    SUCCESS(200, "操作成功！"),
    TOKEN_INVALID(401, "Token错误"),
    FORBIDDEN(403, "您没有权限访问！"),
    NOT_FOUND(404, "资源不存在！"),
    ERROR(500, "服务器内部错误！");

    private Integer code;
    private String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
