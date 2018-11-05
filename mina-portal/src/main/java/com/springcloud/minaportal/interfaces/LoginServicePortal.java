package com.springcloud.minaportal.interfaces;

import com.springcloud.minaservicesdk.interfaces.LoginService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "mina-service")
public interface LoginServicePortal extends LoginService {
}
