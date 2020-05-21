package com.dhcc.zpc.business.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 响应实体
 * @Author 赵朋超
 * @Date 2020/5/21 10:00
 * @Version 1.0
 */
@ApiModel(value="响应实体")
@Data
public class MfsTaskDto {
    @ApiModelProperty(value="身份证号",required = true,dataType = "string")
    private String idNo;

    @ApiModelProperty(value="用户姓名",required = true,dataType = "string")
    private String cifName;

    @ApiModelProperty(value="联系方式",required = true,dataType = "string")
    private String phoneNo;
}
