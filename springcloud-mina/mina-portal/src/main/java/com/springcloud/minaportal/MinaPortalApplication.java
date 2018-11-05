package com.springcloud.minaportal;

import com.springcloud.minabean.common.ResponseContent;
import com.springcloud.minabean.enums.ResponseEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class MinaPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinaPortalApplication.class, args);
	}

	@Bean
	public ResponseContent responseContent(){
		ResponseContent responseContent=new ResponseContent();
		responseContent.setErrorCode(ResponseEnum.FAIL.getCode());
		responseContent.setErrorMsg(ResponseEnum.FAIL.getMsg());
		return responseContent;
	}
}
