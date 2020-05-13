package com.aisino.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@ComponentScan(basePackages = {"com.aisino.service"})
//@ImportResource(value = { "classpath:spring-tasks.xml" })
//@EnableScheduling
@EnableAsync
public class MultiThreadConfig implements AsyncConfigurer {
    // 这里是声明一个bean，类似于xml中的<bean>标签。
    // Executor 就是一个线程池
    @Override
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 可异步开启线程数
        taskExecutor.setCorePoolSize(10);
        // 最大线程数
        taskExecutor.setMaxPoolSize(20);
        // 每个线程可排队数
        taskExecutor.setQueueCapacity(500);
        //配置线程池的前缀
        taskExecutor.setThreadNamePrefix("async-insertdata-");
        // 拒绝策略为重试
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
