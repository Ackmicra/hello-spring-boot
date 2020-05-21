package com.dhcc.zpc.util.resp;

import com.dhcc.zpc.util.resp.status.ResultCodeEnum;

/**
 * @ClassName Response
 * @Description 将结果转换为封装后的对象
 * @Author 赵朋超
 * @Date 2020/2/27 22:55
 * @Version 1.0
 */
public class AppResultBuilder {

    private final static String SUCCESS = "success";

    private final static String FAIL = "fail";

    public static <T> ResponseEntity<T> makeOKRsp() {
        return new ResponseEntity<T>().setCode(ResultCodeEnum.SUCCESS).setMsg(SUCCESS);
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
     * @Description 返回响应码成功，响应信息自定义的信息
     * @Date 9:02 2020/2/28
     * @Param [message]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> makeOKRsp(String message) {
        return new ResponseEntity<T>().setCode(ResultCodeEnum.SUCCESS).setMsg(message);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码成功，响应信息success，响应数据自定义的信息
     * @Date 9:02 2020/2/28
     * @Param [data]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> makeOKRsp(T data) {
        return new ResponseEntity<T>().setCode(ResultCodeEnum.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码为服务内部错误，响应信息自定义的信息
     * @Date 9:03 2020/2/28
     * @Param [message]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> makeErrRsp(String message) {
        return new ResponseEntity<T>().setCode(ResultCodeEnum.INTERNAL_SERVER_ERROR).setMsg(message);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码自定义，响应信息自定义的信息
     * @Date 9:04 2020/2/28
     * @Param [code, msg]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> makeRsp(int code, String msg) {
        return new ResponseEntity<T>().setCode(code).setMsg(msg);
    }

    /**
     * @Author 赵朋超
     * @Description 返回响应码自定义，响应信息自定义，响应数据自定义的信息
     * @Date 9:04 2020/2/28
     * @Param [code, msg, data]
     * @return com.uranus.platform.business.pub.resp.ResponseResult<T>
     **/
    public static <T> ResponseEntity<T> makeRsp(int code, String msg, T data) {
        return new ResponseEntity<T>().setCode(code).setMsg(msg).setData(data);
    }
}
