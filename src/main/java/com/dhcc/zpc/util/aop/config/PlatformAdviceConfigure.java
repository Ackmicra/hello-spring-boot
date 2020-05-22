package com.dhcc.zpc.util.aop.config;

import com.dhcc.zpc.util.aop.handler.WsBaseAdviceHandler;
import com.dhcc.zpc.util.execption.BusinessServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ackmicra
 */
@Aspect
@Component
@Slf4j
public class PlatformAdviceConfigure {
    @Autowired
    private WsBaseAdviceHandler wsBaseAdviceHandler;

    @Pointcut("execution(public * com.dhcc.zpc.business.*.service..*(..))")
    public void oldServicePointCut(){}

    @Pointcut("@annotation(com.dhcc.zpc.util.aop.annotation.WsBaseAdvice)")
    public void wsBaseDataPointCut(){}


    /**
     * wsBaseAdviceAround 和 wsBaseAdviceReturn 使用任意一个即可满足需求
     *
     * wsBaseAdviceException 为发生异常执行的方法
     */

    // 环绕通知
//    @Around("wsBaseDataPointCut()")
//    public void wsBaseAdviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            /**
//             获取方法的参数
//             */
//            Object[] arguments = joinPoint.getArgs();
//            // 执行使用注解的方法，可以返回  返回值
//            joinPoint.proceed();
//
//        } catch (Throwable e) {
//            throw e;
//        }
//    }


    /**
     * @return void
     * @Author 赵朋超
     * @Description // 方法拦截处理   result为返回值
     * @Date 19:58 2020/5/22
     * @Param [joinPoint, result 返回值]
     **/
    @AfterReturning(value = "wsBaseDataPointCut()",returning = "result")
    public void wsBaseAdviceReturn(JoinPoint joinPoint, Object result){
        /**
            获取方法的参数
         */
        Object[] arguments = joinPoint.getArgs();

        wsBaseAdviceHandler.insertWsBase();
        System.out.println(result);
        System.out.println(joinPoint.getKind());
        System.out.println(joinPoint.getTarget());
    }
    /**
     * @return void
     * @Author 赵朋超
     * @Description //异常拦截之后触发的方法
     * @Date 19:48 2020/5/22
     * @Param [joinPoint, ex]
     **/
    @AfterThrowing(value = "wsBaseDataPointCut()",throwing = "ex")
    public void wsBaseAdviceException(JoinPoint joinPoint, BusinessServiceException ex){
        /**
            获取方法的参数
         */
        Object[] arguments = joinPoint.getArgs();

        wsBaseAdviceHandler.insertWsBase();
        System.out.println(ex.getMessage() + "::" + ex.getBusinessCode().getBusinessCode());
        System.out.println(joinPoint.getKind());
        System.out.println(joinPoint.getTarget());
    }
}
