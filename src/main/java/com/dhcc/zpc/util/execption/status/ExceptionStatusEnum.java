package com.dhcc.zpc.util.execption.status;

/**
 * @Author 赵朋超
 * @Description // 异常返回码
 * @Date 19:51 2020/5/20
 **/
public enum ExceptionStatusEnum {
    NOT_FOUND("404","系统异常"),
    SUCCESS("200","成功"),

    ;
    private String businessCode;
    private String businessMsg;

    public String getBusinessCode() {
        return businessCode;
    }

    public String getBusinessMsg() {
        return businessMsg;
    }

    private ExceptionStatusEnum(String businessCode, String businessMsg){
        this.businessCode = businessCode;
        this.businessMsg = businessMsg;
    }
}
