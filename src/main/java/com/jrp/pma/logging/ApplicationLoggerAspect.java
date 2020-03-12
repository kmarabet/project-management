package com.jrp.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.jrp.pma.controllers..*)"
    +" || within(com.jrp.pma.dao..*)")
    public void definePackagePointcuts(){
        // empty method just to name the location specified in the pointcut
    }

    @Around("definePackagePointcuts()")
    public Object aroundLog(ProceedingJoinPoint jp){
        log.debug(" \n \n \n");
        log.debug("*********** Before Method Execution ************** \n {}.{}() with argument[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));

        Object proceed=null;
        try {
            proceed = jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        log.debug(" \n \n \n");
        log.debug("*********** After Method Execution ************** \n {}.{}() with argument[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));

        return proceed;
    }

//    @Before("definePackagePointcuts()")
//    public void beforeLog(JoinPoint jp){
//        log.debug("*********** Before Method Execution ************** \n {}.{}() with argument[s] = {}",
//                jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//    }
//
//    @After("definePackagePointcuts()")
//    public void afterLog(JoinPoint jp){
//        log.debug(" \n \n \n");
//        log.debug("*********** After Method Execution ************** \n {}.{}() with argument[s] = {}",
//                jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//    }

}
