package com.dhcc.zpc.util.execption;

import com.dhcc.zpc.util.execption.status.ExceptionStatusEnum;

/**
 * @ClassName JsonException
 * @Description Json转换异常
 * @Author 赵朋超
 * @Date 2020/3/4 22:01
 * @Version 1.0
 */
public class JsonException extends BasicException {

    public JsonException(ExceptionStatusEnum exceptionStatusEnum, String message) {
        super(exceptionStatusEnum, message);
    }

    public JsonException(ExceptionStatusEnum exceptionStatusEnum, String message, Throwable cause) {
        super(exceptionStatusEnum, message, cause);
    }

    public JsonException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum);
    }

    public JsonException(ExceptionStatusEnum exceptionStatusEnum, Throwable cause) {
        super(exceptionStatusEnum, cause);
    }
}
