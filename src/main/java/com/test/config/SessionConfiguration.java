package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: TestCDSLGYSJ
 * @description:
 * @author: Bruse Queen
 * @create: 2019-01-17 15:21
 **/
@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter
{
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       System.out.println("session先执行");
       registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/test/**").addPathPatterns("/mobile/**")
               .excludePathPatterns("/test/*No").excludePathPatterns("/*No")
               .excludePathPatterns("/test/loginPage").excludePathPatterns("/test/checkLogin")
               .excludePathPatterns("/test/selectAllBtnById").excludePathPatterns("/test/getMenu")
               .excludePathPatterns("/test/receiveData").excludePathPatterns("/mobile/ywUserheckLogin")
               .excludePathPatterns("/test/orderhead/getPdf").excludePathPatterns("/test/orderhead/downloadTemplate")
               .excludePathPatterns("/test/orderhead/uploadGfxx");
   }

    @Bean
    public static HandlerInterceptor getHandlerInterceptor() {
        return new LoginInterceptor();
    }
}

