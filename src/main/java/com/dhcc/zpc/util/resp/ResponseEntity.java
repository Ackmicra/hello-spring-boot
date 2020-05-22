package com.dhcc.zpc.util.resp;

import com.dhcc.zpc.constant.BusinessCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName ResponseResult
 * @Description 返回结果集封装
 * @Author 赵朋超
 * @Date 2020/2/27 22:53
 * @Version 1.0
 */
@ApiModel(value="公共响应实体")
public class ResponseEntity<T> implements Serializable {
    @ApiModelProperty(value="响应码",required = true,dataType = "int")
    private String code; // 返回状态码200成功

    @ApiModelProperty(value="响应信息",required = true,dataType = "string")
    private String msg; // 返回描述信息

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="响应内容",required = true,dataType = "T")
    private T data; // 返回内容体

    public ResponseEntity<T> setCode(BusinessCodeEnum retCode) {
        this.code = retCode.getBusinessCode();
        return this;
    }

    public String getCode() {
        return code;
    }
    public ResponseEntity<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }
    public ResponseEntity<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }
    public ResponseEntity<T> setData(T data) {
        this.data = data;
        return this;
    }
}
