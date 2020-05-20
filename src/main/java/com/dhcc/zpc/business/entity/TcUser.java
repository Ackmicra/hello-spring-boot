package com.dhcc.zpc.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author 赵朋超
 * @Date 2020/5/19 17:34
 * @Version 1.0
 */
@Data
@ApiModel(value="用户表")
public class TcUser {
    @ApiModelProperty(value="身份证号",required = true,dataType = "string")
    private String idNo;
    @ApiModelProperty(value="客户姓名",required = true,dataType = "string")
    private String cifName;
    @ApiModelProperty(value="联系方式",required = true,dataType = "string")
    private String phoneNo;
}
