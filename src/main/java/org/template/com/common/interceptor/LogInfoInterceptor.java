package org.template.com.common.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.template.com.common.annotation.LogInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect  
@Component
public class LogInfoInterceptor {

    @Pointcut("@annotation(org.template.com.common.annotation.LogInfo)")  
//    @Pointcut("execution(* com.xjj.web.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")  
    public void logMethodPointcut(){}  
    
    @Around("logMethodPointcut()") 
    public Object Interceptor(ProceedingJoinPoint pjp){
    	
    	MethodSignature signature = (MethodSignature)pjp.getSignature();
    	Method method = signature.getMethod();
    	LogInfo logInfo = method.getAnnotation(LogInfo.class);
    	String value = logInfo.value();
    	System.out.println(value);
    	Object result = null;
    	try {
			result = pjp.proceed();
		} catch (Throwable e) {
			log.error("调用失败", e);
			e.printStackTrace();
		}
    	return result;
    }
    
    @Before("logMethodPointcut()")  
    public void doBeforeAdvice(JoinPoint joinPoint){  
        System.out.println("我是前置通知!!!");  
        //获取目标方法的参数信息  
        Object[] obj = joinPoint.getArgs();  
        //AOP代理类的信息  
        joinPoint.getThis();  
        //代理的目标对象  
        joinPoint.getTarget();  
        //用的最多 通知的签名  
        Signature signature = joinPoint.getSignature();  
        //代理的是哪一个方法  
        System.out.println(signature.getName());  
        //AOP代理类的名字  
        System.out.println(signature.getDeclaringTypeName());  
        //AOP代理类的类（class）信息  
        signature.getDeclaringType();  
        //获取RequestAttributes  
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
        //从获取RequestAttributes中获取HttpServletRequest的信息  
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);  
        //如果要获取Session信息的话，可以这样写：  
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);  
        Enumeration<String> enumeration = request.getParameterNames();  
        Map<String,String> parameterMap = new HashMap<String,String>();
        while (enumeration.hasMoreElements()){  
            String parameter = enumeration.nextElement();  
            parameterMap.put(parameter,request.getParameter(parameter));  
        }  
        
        ObjectMapper mapper = new ObjectMapper(); //转换器  	
      
        String str = null;
        try {
			str = mapper.writeValueAsString(parameterMap);
		} catch (JsonProcessingException e) {
			log.error("转化失败", e);
			e.printStackTrace();
		}
        if(obj.length > 0) {  
            System.out.println("请求的参数信息为："+str);  
        }  
    }  
    
    @After("logMethodPointcut()")
    public void afterMethod(JoinPoint joinpoint){
        //注意，JoinPoint来自org.aspectj.lang.JoinPoint，小心导错包
        //方法名
        String methodName = joinpoint.getSignature().getName();
        //方法参数
        List<Object> args = Arrays.asList(joinpoint.getArgs());
        System.out.println("method "+methodName+" end:"+args);
    }
}
