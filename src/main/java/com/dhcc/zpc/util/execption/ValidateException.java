package com.dhcc.zpc.util.execption;

import com.dhcc.zpc.util.execption.status.ExceptionStatusEnum;

/**
 * @ClassName ValidateException
 * @Description 数据验证异常
 * @Author 赵朋超
 * @Date 2020/3/4 22:09
 * @Version 1.0
 */
public class ValidateException extends BasicException {

    public ValidateException(ExceptionStatusEnum exceptionStatusEnum, String message) {
        super(exceptionStatusEnum, message);
    }

    public ValidateException(ExceptionStatusEnum exceptionStatusEnum, String message, Throwable cause) {
        super(exceptionStatusEnum, message, cause);
    }

    public ValidateException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum);
    }

    public ValidateException(ExceptionStatusEnum exceptionStatusEnum, Throwable cause) {
        super(exceptionStatusEnum, cause);
    }
}
