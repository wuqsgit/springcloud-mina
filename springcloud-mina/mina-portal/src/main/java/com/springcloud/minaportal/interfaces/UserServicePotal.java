package com.springcloud.minaportal.interfaces;

import com.springcloud.minaservicesdk.interfaces.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "mina-service")
public interface UserServicePotal extends UserService {
}
