package id.my.hilmiat.spring_mysql.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {
    private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);
    @Bean
    ThreadPoolTaskExecutor taskConfig(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("AsyncTaskSaya-");
        executor.setRejectedExecutionHandler((r,t)-> log.warn("PENUH>>Task Rejected"));
        executor.initialize();
        return executor;
    }
}
