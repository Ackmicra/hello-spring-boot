package com.dhcc.zpc.util.execption.handler;

import com.dhcc.zpc.constant.BusinessCodeEnum;
import com.dhcc.zpc.util.execption.BusinessServiceException;
import com.dhcc.zpc.util.execption.JsonParseException;
import com.dhcc.zpc.util.execption.ParamValidateException;
import com.dhcc.zpc.util.execption.handler.impl.PrintExceptionHandlerImpl;
import com.dhcc.zpc.util.resp.AppResultBuilder;
import com.dhcc.zpc.util.resp.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @RestControllerAdvice("club.mydlq.valid")   //指定异常处理的包名
 **/
@ControllerAdvice
public class PlatformExceptionHandler {

    @Autowired
    private PlatformExceptionLogHandler platformExceptionLogHandler;

    @Bean
    @ConditionalOnMissingBean(PlatformExceptionLogHandler.class)
    public PlatformExceptionLogHandler platformExceptionLogHandler(){
        return new PrintExceptionHandlerImpl();
    }
    /**
     * 普通业务级异常
     * @param exception
     * @param response 可以设置给前端返回的请求头等信息...
     * @param request  可以获取请求头、请求方法、参数等信息...
     * @return
     */
    @ExceptionHandler(value = BusinessServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleCustomServiceException(BusinessServiceException exception, HttpServletResponse response, HttpServletRequest request) {
        platformExceptionLogHandler.handleExceptionLog(exception);
        return AppResultBuilder.response(BusinessCodeEnum.SYSTEM_ERROR.getBusinessCode(), exception.getMessage());
    }

    /**
     * Json转换失败异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = JsonParseException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> handleJsonParseFailureException(JsonParseException exception, HttpServletResponse response) {
        platformExceptionLogHandler.handleExceptionLog(exception);
        return AppResultBuilder.response(exception.getBusinessCode().getBusinessCode(),"报文数据Json转换失败：" + exception.getMessage());
    }

    /**
     * 参数校验异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ParamValidateException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleParamInValidateException(ParamValidateException exception, HttpServletResponse response) {
        platformExceptionLogHandler.handleExceptionLog(exception);
        return AppResultBuilder.response(exception.getBusinessCode().getBusinessCode(),"参数格式校验失败：：" + exception.getMessage());
    }

    /**
     * 其他未捕捉异常
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception exception) {
        platformExceptionLogHandler.handleExceptionLog(exception);
        return AppResultBuilder.response(BusinessCodeEnum.SYSTEM_ERROR.getBusinessCode(), exception.getMessage());
    }

}
