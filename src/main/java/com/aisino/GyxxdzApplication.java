package com.aisino;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.cloud.netflix.eureka.EnableEurekaClient;*/
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
/*@EnableEurekaClient*/
@EnableAsync
@MapperScan("com.aisino.mapper")
public class GyxxdzApplication {
	public static void main(String[] args) {
		SpringApplication.run(GyxxdzApplication.class, args);
	}
}
