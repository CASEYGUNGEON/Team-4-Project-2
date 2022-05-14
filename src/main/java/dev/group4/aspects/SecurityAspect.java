package dev.group4.aspects;

import dev.group4.entities.User;
import dev.group4.services.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Component
@Aspect
public class SecurityAspect {

    @Autowired
    private UserService userService;

    @Around("securityJP()")
    public Object Authorize(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        String authorization = request.getHeader("Authorization");
        authorization = new String(Base64.getDecoder().decode(authorization.substring(authorization.indexOf(' ')+1)));

        if(compareAuthentication(authorization)){
            return pjp.proceed();
        }
        else{
            response.sendError(401,"You are not authorized");//
            return null;
        }
    }

    private boolean compareAuthentication(String authorization){
        String username = authorization.substring(0,authorization.indexOf(':'));
        String pass = authorization.substring(authorization.indexOf(':')+1);
        User user = new User(username,pass);
        //TODO get authentication from database or Local Storage?
        try {
            if(userService.login(user)!=null){
                System.out.println("Secured method" +user + pass);
                return true;
            }
        } catch (InvalidCredentialException e) {
            System.out.println("Couldn't login with secured method" + username + pass);
            return false;
        }
        return true;
    }

    @Pointcut("@annotation(dev.group4.aspects.Secured)")
    private void securityJP(){}
}
