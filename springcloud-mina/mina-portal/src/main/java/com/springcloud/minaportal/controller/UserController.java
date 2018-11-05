package com.springcloud.minaportal.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.minaportal.interfaces.UserServicePotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application.properties")
public class UserController {
    @Value("${baidu_session_key_url}")
    String sessurl;
    @Autowired
    UserServicePotal userServicePotal;
    @RequestMapping(value="/hello/{id}")
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
    public String getUser(@PathVariable("id") int id){
        System.out.println(sessurl);
        return userServicePotal.getUserInfo(id).toString();
    }

    public String error(@PathVariable("id")int id){
        return id+"已进行断路请稍后再试";
    }
}
