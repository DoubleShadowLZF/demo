package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 李卓锋
 * @version 1.0
 * @Description
 * @since 2018/8/12
 */
@Aspect
@Slf4j
@Component
public class TestAop{

    /* 切点表达式入参列表不支持其他非常用类型,Map和HttpSession类型将不被支持
     * com.example.demo.crud.controller.LoginController.login(String userName , String password , Map<String , String> result , HttpSession session)
     */
    @Pointcut("execution(public String com.example.demo.crud.controller.LoginController.login(..))")
    public void pointCut(){};

    @Pointcut("execution(public * com.example.demo.crud.controller.*.*(..))")
    public void webLog(){}

    @Before("pointCut()")
    public void doBefore(JoinPoint point){
        log.debug("doBefore...:");
        log.debug("DeclaringType:"+point.getSignature().getDeclaringType());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.debug("url:"+request.getRequestURI());
        log.debug("http method:"+request.getMethod());
        log.debug("ip:"+request.getRemoteAddr());
        log.debug("class method:"+point.getSignature().getDeclaringTypeName()+
                "."+point.getSignature().getName());
        log.debug("args:"+ Arrays.toString(point.getArgs()));
    }

    @AfterReturning(returning="ret",pointcut = "pointCut()")
    public void doReturn(Object ret)throws Throwable {
        log.debug("doReturn...:");
        log.debug("return : "+ret);
    }

    @AfterThrowing("pointCut()")
    public void doThorwing(JoinPoint point ){
        log.debug("doThrowing...");
    }

    @After("pointCut()")
    public void doAfter(){
        log.debug("doAfter...");
    }

    /**
     * @author Double
     * @Description
     * @param [ProceedingJoinPoint]
     * @return java.lang.Object 应该把AOP拦截到的返回信息一起返回，不然原本的方法返回会是一void 方法。
     * @Data 2018/8/14 16:04
     */
    @Around("pointCut()")
    public Object doArround(ProceedingJoinPoint pjp){
        log.debug("doBefore");
        Object proceed = null ;
        try {
            proceed = pjp.proceed();
            log.debug("doAfter");
        }catch(Throwable e){
            log.error(""+e);
            log.debug("doThrowing");
        }
        log.debug("doReturn");
        return proceed;
    }
}
