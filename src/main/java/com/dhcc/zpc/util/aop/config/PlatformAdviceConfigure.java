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
 *
 * Spring AOP异常执行顺序
 *  正常情况：
 *      1. @Around前（以joinPoint.proceed();执行目标方法作为分割）；
 *      2. @Before
 *      3. joinPoint.proceed();执行目标方法；
 *      4. @Around后
 *      5. @After
 *      6. @AfterReturning(value = "wsBaseDataPointCut()",returning = "result")
 *
 *  异常情况：
 *      1. @Around前（以joinPoint.proceed();执行目标方法作为分割）；
 *      2. @Before
 *      3. joinPoint.proceed();执行目标方法；发生异常
 *      4. @After
 *      5. @AfterThrowing(value = "wsBaseDataPointCut()",throwing = "ex")
 *
 *  JoinPoint里包含了如下几个常用的方法：
 *      1. Object[] getArgs：返回目标方法的参数
 *      2. Signature getSignature：返回目标方法的签名
 *      3. Object getTarget：返回被织入增强处理的目标对象
 *      4. Object getThis：返回AOP框架为目标对象生成的代理对象
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
//            Object result = joinPoint.proceed();
//
//        } catch (Throwable e) {
//            throw e;
//        }
//    }


    /**
     * @return void
     * @Author 赵朋超
     * @Description // 返回后通知：在调用目标方法结束后执行【出现异常，不执行】   result为返回值
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
     *
     * 注： 若为ex声明指定类型，则会限制目标方法必须抛出指定类型的异常，否则将不会拦截；
     *     1. 如果声明ex为BusinessServiceException类型，则只有抛出BusinessServiceException类型异常才会触发该方法；
     *     2. 如果声明ex为NullPointerException类型，则只有抛出NullPointerException类型异常才会触发该方法；
     *     3. 若声明ex为Throwable类型，则意味着对目标方法抛出的异常不加限制，只要出现异常即可触发该拦截方法；
     *
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
