package com.bird.app.aop;

import com.bird.app.service.ArticleActionService;
import com.bird.common.entity.ArticleAction;
import com.bird.common.enums.OperationType;
import com.bird.common.utils.HttpContextUtils;
import com.bird.common.utils.IPUtils;
import com.bird.common.utils.SecurityUtil;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class ArticlesLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(ArticlesLoggingAspect.class);


    @Autowired
    private ArticleActionService articleActionService;


    @Pointcut("execution( * com.bird.app.controller.ArticleController*.*(..))")
    public void articlesAOP() {
        System.out.println("aspect");
    }


    @Before("articlesAOP()")
    public void beforeArticlesAOP() {
        System.out.println("before");
    }


    // execute if not error happend
    @SneakyThrows
    @AfterReturning("articlesAOP()")
    public void afterReturnArticlesAOP(JoinPoint joinPoint){
        ArticleAction articleAction = new ArticleAction();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();

        if("createArticle".equals(methodName)){
            articleAction.setOperationType(OperationType.INSERT);
        }else if("getAllArticlesList".equals(methodName)){
            articleAction.setOperationType(OperationType.QUERYALL);
        }else if("getArticle".equals(methodName)){
            articleAction.setOperationType(OperationType.QUERY);
        }else if("updateArticle".equals(methodName)){
            articleAction.setOperationType(OperationType.UPDATE);
        }else {
            articleAction.setOperationType(OperationType.DELETE);
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
