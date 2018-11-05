package com.springcloud.zikpinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
public class ZikpinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZikpinServerApplication.class, args);
	}
}
