package com.bird.app.aop;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArticlesLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(ArticlesLoggingAspect.class);




}
