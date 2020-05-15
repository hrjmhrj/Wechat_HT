package com.aisino.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: AisinoCDSLGYSJ
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
       registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/aisino/**").addPathPatterns("/mobile/**")
               .excludePathPatterns("/aisino/*No").excludePathPatterns("/*No")
               .excludePathPatterns("/aisino/loginPage").excludePathPatterns("/aisino/checkLogin")
               .excludePathPatterns("/aisino/selectAllBtnById").excludePathPatterns("/aisino/getMenu")
               .excludePathPatterns("/aisino/receiveData").excludePathPatterns("/mobile/ywUserheckLogin")
               .excludePathPatterns("/aisino/orderhead/getPdf").excludePathPatterns("/aisino/orderhead/downloadTemplate")
               .excludePathPatterns("/aisino/orderhead/uploadGfxx");
   }

    @Bean
    public static HandlerInterceptor getHandlerInterceptor() {
        return new LoginInterceptor();
    }
}

