package com.bird.app.aop;

import com.alibaba.fastjson.JSON;
import com.bird.app.service.ArticleActionService;
import com.bird.common.entity.ArticleAction;
import com.bird.common.enums.OperationType;
import com.bird.common.utils.HttpContextUtils;
import com.bird.common.utils.IPUtils;
import com.bird.common.utils.SecurityUtil;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
public class ArticlesPublicCatchAspect {
    private final Logger logger = LoggerFactory.getLogger(ArticlesPublicCatchAspect.class);


    @Autowired
    private ArticleActionService articleActionService;


    @Pointcut("execution( * com.bird.app.controller.WebArticleController*.*(..))")
    public void pointcutAOP() {
        System.out.println("aspect");
    }


    @Before("pointcutAOP()")
    public void beforePointcutAOP() {
        System.out.println("before");
    }


    // TODO
    @SneakyThrows
    @AfterReturning("pointcutAOP()")
    public void afterReturnArticlesAOP(JoinPoint joinPoint){
        ArticleAction articleAction = new ArticleAction();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();

        if("getArticleAndAllDetails".equals(methodName)){
            articleAction.setOperationType(OperationType.INSERT);
        }
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        String userName=  SecurityUtil.getCurrentUserLogin();
        Long userId=  SecurityUtil.getCurrentUserId();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        articleAction.setCreatedBy(userName);
        articleAction.setUserId(userId);
        articleAction.setIp(IPUtils.getIpAddr(request));
        articleAction.setMethod(methodName);
        articleAction.setParams(params);

        articleActionService.createWikiAction(articleAction);
    }


}
