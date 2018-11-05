package com.springcloud.minaservice;

import com.springcloud.minabean.common.ResponseContent;
import com.springcloud.minabean.enums.ResponseEnum;
import net.sf.ehcache.CacheManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.springcloud.minadao.mapper")
@EnableCaching
public class MinaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinaServiceApplication.class, args);
	}

	@Bean(name="ehcacheManager")
	public CacheManager ehcacheManager(){
	    CacheManager ehcacheManager=CacheManager.create(this.getClass().getClassLoader().getResourceAsStream("ehcache-config.xml"));
	    return ehcacheManager;
    }

    @Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate;
	}

	@Bean
	public ResponseContent responseContent(){
		ResponseContent responseContent=new ResponseContent();
		responseContent.setErrorCode(ResponseEnum.FAIL.getCode());
		responseContent.setErrorMsg(ResponseEnum.FAIL.getMsg());
		return responseContent;
	}
}
