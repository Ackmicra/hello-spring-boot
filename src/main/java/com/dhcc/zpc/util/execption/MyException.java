package com.dhcc.zpc.util.execption;

import lombok.Data;

/**
 * @ClassName MyExecption
 * @Description 异常类
 * @Author 赵朋超
 * @Date 2020/2/28 21:38
 * @Version 1.0
 */
@Data
public class MyException extends RuntimeException {

    private int code;

    public MyException(int code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }
    public MyException(int code, String message){
        super(message);
        this.code = code;
    }
}
