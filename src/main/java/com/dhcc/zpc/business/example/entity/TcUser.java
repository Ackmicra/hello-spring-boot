package com.dhcc.zpc.business.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description TODO
 * @Author 赵朋超
 * @Date 2020/5/19 17:34
 * @Version 1.0
 */
@Data
@ApiModel(value="用户表")
public class TcUser {

    @NotNull(message = "身份证号不能为空")
    @Size(min = 18, max = 18, message = "身份证号码长度必须是18个字符")
    @ApiModelProperty(value="身份证号",required = true,dataType = "string")
    private String idNo;

    @NotNull(message = "用户姓名不能为空")
    @ApiModelProperty(value="用户姓名",required = true,dataType = "string")
    private String cifName;

    @NotNull(message = "联系方式不能为空")
    @ApiModelProperty(value="联系方式",required = true,dataType = "string")
    private String phoneNo;
}
