package com.dhcc.zpc.util.execption;

import com.dhcc.zpc.constant.BusinessCodeEnum;

/**
 * Json转换异常
 * 项目中Json转换过程中出现错误，则建议抛出此异常
 */
public class JsonParseException extends BusinessServiceException{

    public JsonParseException(BusinessCodeEnum businessCode, String message) {
        super(businessCode, message);
    }

    public JsonParseException(BusinessCodeEnum businessCode, String message, Throwable cause) {
        super(businessCode, message, cause);
    }
}
