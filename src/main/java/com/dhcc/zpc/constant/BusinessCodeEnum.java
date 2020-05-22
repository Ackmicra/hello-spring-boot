package com.dhcc.zpc.constant;

/**
 * @Author 赵朋超
 * @Description // 异常返回码
 * @Date 19:51 2020/5/20
 **/
public enum BusinessCodeEnum {

    //基础业务部分
    /**
     * 业务执行成功，统一返回码为0000
     */
    SUCCESS("0000","业务执行成功"),
    PART_SUCCESS("0001","部分执行成功"),
    /**
     * 系统异常，出现未捕捉的错误信息，统一返回码为1000
     */
    SYSTEM_ERROR("1000","系统执行中出现了未捕捉到的异常情况"),

    //用户授权部分
    /**
     * 用户登陆失败，返回码为1100
     */
    LOGIN_FAILURE("1100","用户登陆失败"),
    /**
     * 令牌
     */
    INVALID_TOKEN("2121","令牌已失效"),
    INVALID_REFRESH_TOKEN("2122","刷新令牌已失效"),
    /**
     * 用户鉴权失败
     */
    UNAUTHORIZED("2001","用户权限校验失败"),
    //业务码部分
    /**
     * 参数不合法
     */
    ILLEGAL_DATA("1001","参数数据不合法"),
    /**
     * 查询无此记录
     */
    NO_DATA("1002","查询无此记录"),

    //其他
    /**
     * 未执行业务方法,无需任何处理
     */
    NONE("00000","未执行业务方法,无需任何处理"),
    ;
    private String businessCode;
    private String businessMsg;

    public String getBusinessCode() {
        return businessCode;
    }

    public String getBusinessMsg() {
        return businessMsg;
    }

    private BusinessCodeEnum(String businessCode, String businessMsg){
        this.businessCode = businessCode;
        this.businessMsg = businessMsg;
    }
}
