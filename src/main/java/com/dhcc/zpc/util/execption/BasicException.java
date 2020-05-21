package com.dhcc.zpc.util.execption;

import com.dhcc.zpc.util.execption.status.ExceptionStatusEnum;

/**
 * @ClassName BasicExecption
 * @Description 基本异常类
 * @Author 赵朋超
 * @Date 2020/3/4 21:55
 * @Version 1.0
 */
public class BasicException extends RuntimeException {
    private ExceptionStatusEnum exceptionStatusEnum;

    public BasicException(ExceptionStatusEnum exceptionStatusEnum){
        super(exceptionStatusEnum.getBusinessMsg());
        this.exceptionStatusEnum = exceptionStatusEnum;
    }

    public BasicException(ExceptionStatusEnum exceptionStatusEnum, String message){
        super(message);
        this.exceptionStatusEnum = exceptionStatusEnum;
    }

    public BasicException(ExceptionStatusEnum exceptionStatusEnum, Throwable cause){
        super(exceptionStatusEnum.getBusinessMsg(), cause);
        this.exceptionStatusEnum = exceptionStatusEnum;
    }

    public BasicException(ExceptionStatusEnum exceptionStatusEnum, String message, Throwable cause){
        super(message, cause);
        this.exceptionStatusEnum = exceptionStatusEnum;
    }
}
