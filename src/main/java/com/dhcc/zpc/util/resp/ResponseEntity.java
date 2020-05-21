package com.dhcc.zpc.util.resp;

import com.dhcc.zpc.util.resp.status.ResultCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

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
    private int code; // 返回状态码200成功

    @ApiModelProperty(value="响应信息",required = true,dataType = "string")
    private String msg; // 返回描述信息

    @JsonInclude(value=JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value="响应内容",required = true,dataType = "T")
    private T data; // 返回内容体

    public ResponseEntity error(String msg){
        return new ResponseEntity().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMsg(msg);
    }

    public <T> ResponseEntity<T> ok(String msg, T data){
        return new ResponseEntity<T>().setCode(HttpStatus.OK.value()).setMsg(msg).setData(data);
    }

    public <T> ResponseEntity<T> okAdd(T data){
        return ok("新增成功",data).setCode(HttpStatus.CREATED.value());
    }

    public <T> ResponseEntity<T> okPatch(T data){
        return ok("部分更新成功",data);
    }

    public <T> ResponseEntity<T> okPut(T data){
        return ok("更新成功",data);
    }

    public ResponseEntity okDel(){
        return ok("删除成功",null).setCode(HttpStatus.NO_CONTENT.value());
    }

    public <T> ResponseEntity<T> okUpdate(T data){
        return ok("更新成功",data);
    }

    public <T> ResponseEntity<T> okQuery(T data){
        return ok("查询成功",data);
    }

    public ResponseEntity<T> setCode(ResultCodeEnum retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }
    public ResponseEntity<T> setCode(int code) {
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
