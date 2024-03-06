package id.my.inienun.spring_mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter.EventPublisher;
import jakarta.annotation.PostConstruct;

@Service
public class RateLimiterService {
    @Autowired
    RateLimiterRegistry registry;

    @PostConstruct
    public void postConstruct(){
        //register ke event rate limiter
        EventPublisher eventPublisher = registry.rateLimiter("rateLimitEvents").getEventPublisher();
        eventPublisher.onSuccess(System.out::println);
        eventPublisher.onFailure(System.out::println);
        eventPublisher.onEvent(System.out::println);
    }
}