package com.dhcc.zpc.util.resp;

import com.dhcc.zpc.constant.BusinessCodeEnum;

/**
 * @ClassName Response
 * @Description 将结果转换为封装后的对象
 * @Author 赵朋超
 * @Date 2020/2/27 22:55
 * @Version 1.0
 */
public class AppResultBuilder {

    private final static String SUCCESS = "SUCCESS";

    private final static String FAIL = "FAIL";

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<T>().setCode(BusinessCodeEnum.SUCCESS).setMsg(SUCCESS);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码成功，响应信息自定义的信息
     * @Date 9:02 2020/2/28
     * @Param [message]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> success(String message) {
        return new ResponseEntity<T>().setCode(BusinessCodeEnum.SUCCESS).setMsg(message);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码成功，响应信息success，响应数据自定义的信息
     * @Date 9:02 2020/2/28
     * @Param [data]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<T>().setCode(BusinessCodeEnum.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> ResponseEntity<T> fail() {
        return new ResponseEntity<T>().setCode(BusinessCodeEnum.SYSTEM_ERROR).setMsg(FAIL);
    }
    /**
     * @Author 赵朋超
     * @Description 返回响应码为服务内部错误，响应信息自定义的信息
     * @Date 9:03 2020/2/28
     * @Param [message]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> fail(String message) {
        return new ResponseEntity<T>().setCode(BusinessCodeEnum.SYSTEM_ERROR).setMsg(message);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码成功，响应信息success
     * @Date 9:00 2020/2/28
     * @Param []
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> response() {
        return new ResponseEntity<T>();
    }
    /**
     * @Author 赵朋超
     * @Description 返回响应码自定义，响应信息自定义的信息
     * @Date 9:04 2020/2/28
     * @Param [code, msg]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> response(String code, String msg) {
        return new ResponseEntity<T>().setCode(code).setMsg(msg);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码自定义，响应信息自定义，响应数据自定义的信息
     * @Date 9:04 2020/2/28
     * @Param [code, msg, data]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> response(String code, String msg, T data) {
        return new ResponseEntity<T>().setCode(code).setMsg(msg).setData(data);
    }
}
