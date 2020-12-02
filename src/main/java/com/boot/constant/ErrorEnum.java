package com.boot.constant;

/**
 * desc 记录 异常枚举
 * Author joe
 * Date 2020/2/17
 **/
public enum ErrorEnum {
    SUCCESS(200,"success","成功"),
    FAIL(400,"fail","失败"),
    BadRequest(400, "BadRequest", "Bad Request Parameter."),
    Forbidden(403, "Forbidden", "The resources is forbidden to visit."),
    NotFound(404, "NotFound", "The specified resource does not exist."),
    InternalSystemError(500, "InternalError", "We encountered an internal error. Please try again."),
    NoAuth(401, "noAuth", "The request requires user authentication"),
    MissingContentLength(400, "MissingContentLength", "You must provide the Content-Length HTTP header."),
    MalformedJson(400, "MalformedJson", "The JSON you provided was not well-formed or did not validate against our published schema."),
    EntityTooLarge(400, "EntityTooLarge", "Your proposed content exceeds the maximum allowed size."),
    RequestTimeout(400, "RequestTimeout", "Your socket connection to the server was not read from or written to within the timeout period."),
    NotMatchRule(400, "NotMatchRule", "Coupon rule not match."),
    InvalidArgument(400, "InvalidArgument", "One of your provided argument is malformed or otherwise invalid."),
    MethodNotAllowed(405, "MethodNotAllowed", "The specified method is not allowed against this resource."),
    MissingParameter(400, "MissingParameter", "The request you input is missing some required parameters."),
    OperationAborted(409, "OperationAborted", "A conflicting conditional operation is currently in progress against this resource."),
    ExecuteFailed(417, "ExecuteFailed", "Failed to execute"),
    PARAM_ERROR(400,"param error","参数异常");
    public int httpCode;
    public String errCode;
    public String errMsg;

    ErrorEnum(int httpCode, String code, String message) {
        this.httpCode = httpCode;
        this.errCode = code;
        this.errMsg = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}

