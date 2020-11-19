package com.aisino;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(factory);
            restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
            return restTemplate;
        }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 连接超时
        factory.setConnectTimeout(15000);
        // 数据读取超时时间
        factory.setReadTimeout(5000);
        return factory;
    }
}