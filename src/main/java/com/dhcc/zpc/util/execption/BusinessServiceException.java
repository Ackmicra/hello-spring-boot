package com.dhcc.zpc.util.execption;

import com.dhcc.zpc.constant.BusinessCodeEnum;
import com.dhcc.zpc.util.date.DateFormateUtil;
import lombok.Getter;

@Getter
public class BusinessServiceException extends RuntimeException {
	private BusinessCodeEnum businessCode;
	
	public BusinessServiceException(BusinessCodeEnum businessCode, String message) {
		super(message);
		this.businessCode = businessCode;
	}

	public BusinessServiceException(BusinessCodeEnum businessCode, String message, Throwable cause) {
		super(message, cause);
		this.businessCode = businessCode;
	}

	public String printLog(boolean printStackTrace) {
		String say = String.format("%s 发生异常【错误码为：%s,错误信息为：%s】",
				DateFormateUtil.datetime("yyyy-MM-dd HH:mm:ss")
		,businessCode.getBusinessCode(),this.getMessage());
		if(printStackTrace){
			this.printStackTrace();
		}
		return say;
	}
}
