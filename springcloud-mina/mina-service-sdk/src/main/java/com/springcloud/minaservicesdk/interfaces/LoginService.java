package com.springcloud.minaservicesdk.interfaces;

import com.springcloud.minabean.common.ResponseContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/loginService")
public interface LoginService {
    @RequestMapping("/checkSession")
    public ResponseContent checkSession(@RequestParam("url") String url,@RequestParam("code")String code);

    @RequestMapping("/decryptData")
    public ResponseContent decryptData(@RequestParam("data")String data,@RequestParam("code")String code);
}
