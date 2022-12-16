package com.urlshortenerapi.aspects;

import com.urlshortenerapi.enums.UrlOperations;
import com.urlshortenerapi.services.impl.HistoryService;
import com.urlshortenerapi.services.impl.UrlsService;
import com.urlshortenerapi.services.models.History;
import com.urlshortenerapi.services.models.Url;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Aspect
@RequiredArgsConstructor
public class HistoryAspect {
    private final HistoryService historyService;
    private final UrlsService urlsService;

    @Pointcut("execution(* com.urlshortenerapi.services.UrlShortenerService.shortenUrl(..))")
    public void logShortenActionPointcut() {
    }

    @Pointcut("execution(* com.urlshortenerapi.services.UrlShortenerService.resolveOriginalUrl(..))")
    public void logReadActionPointcut() {
    }


    @AfterReturning(pointcut = "logShortenActionPointcut()")
    public void logShortenAction(JoinPoint jp) {
        Optional<Url> url = urlsService.findByOrgUrl((String) jp.getArgs()[0]);
        logAction(url, UrlOperations.SHORTEN);
    }

    @AfterReturning(pointcut = "logReadActionPointcut()")
    public void logReadAction(JoinPoint jp) {
        Optional<Url> url = urlsService.findByHash((String) jp.getArgs()[0]);
        logAction(url, UrlOperations.READ);
    }

    private void logAction(Optional<Url> url, UrlOperations action) {
        if (url.isEmpty())
            return;
        historyService.save(History.builder().operation(action).url(url.get()).build());
    }

}
