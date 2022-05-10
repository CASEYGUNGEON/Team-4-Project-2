package dev.group4.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO Might be unnecessary given the login returns the user, Annotation might be redundant

@Component
@Aspect
public class SecurityAspect {

    @Around("securityJP()")
    public Object Authorize(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        String authorization = request.getHeader("Authorization");
        if(compareAuthentication(authorization)){
            return pjp.proceed();
        }
        else{
            response.sendError(401,"You are not authorized");//
            return null;
        }
    }

    private boolean compareAuthentication(String authorization){
        //TODO get authentication from database
        //TODO check authentication against header
        if(authorization!=null)
            return false;

        return true;
    }

    @Pointcut("@annotation(dev.group4.aspects.Secured)")
    private void securityJP(){}
}
