package com.dhcc.zpc.util.execption;


import com.dhcc.zpc.constant.BusinessCodeEnum;

import java.util.List;

/**
 * 参数校验失败异常
 * 当参数校验失败时，建议抛出此异常。
 */
public class ParamValidateException extends BusinessServiceException{

	public ParamValidateException(BusinessCodeEnum businessCode, String message) {
		super(businessCode, message);
	}

	public ParamValidateException(BusinessCodeEnum businessCode, String message, Throwable cause) {
		super(businessCode, message, cause);
	}

	public ParamValidateException(BusinessCodeEnum businessCode, List<String> errorMessList) {
		this(businessCode, String.join(",", errorMessList));
	}
}
