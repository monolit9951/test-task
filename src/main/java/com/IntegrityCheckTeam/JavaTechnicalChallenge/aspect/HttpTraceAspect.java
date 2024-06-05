package com.IntegrityCheckTeam.JavaTechnicalChallenge.aspect;

import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.HttpTraceService;
import com.IntegrityCheckTeam.JavaTechnicalChallenge.domain.service.impl.HttpTraceServiceImpl;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class HttpTraceAspect {

    private final HttpTraceService httpTraceService;

    @Autowired
    public HttpTraceAspect(HttpTraceService httpTraceService) {
        this.httpTraceService = httpTraceService;
    }

    @AfterReturning(pointcut = "execution(* com.IntegrityCheckTeam.JavaTechnicalChallenge.api.controller.*.*(..))", returning = "result")
    public void afterReturning(Object result) {
        String httpTrace = "HTTP trace: " + result.toString();
        httpTraceService.sendHttpTrace(httpTrace);
    }
}
