package com.springcloud.minaportal.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.minabean.common.ResponseContent;
import com.springcloud.minaportal.interfaces.LoginServicePortal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mina/userLogin")
@PropertySource("classpath:application.properties")
public class LoginController {

    @Value("${baidu_session_key_url}")
    private String sessurl;

    @Value("${baidu_client_id}")
    private String clientId;

    @Value("${baidu_securit_key}")
    private String sk;

    @Autowired
    private LoginServicePortal loginServicePortal;

    @Autowired
    private ResponseContent responseContent;

    /**
     * 获取sessionKey和openid
     * @param code(用户登录凭证（有效期五分钟）,开发者需要在开发者服务器后台调用 api，使用 code 换取 session_key 等信息。)
    */
    @RequestMapping("/checkSession")
    @HystrixCommand(fallbackMethod = "checkError", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
    public ResponseContent checkLogin(HttpServletRequest request,@RequestParam("code")String code){
        String url=new StringBuilder(sessurl).append("?code=").append(code).append("&client_id=").append(this.clientId).append("&sk=").append(this.sk).toString();
        ResponseContent responseContent=loginServicePortal.checkSession(url,code);
        return responseContent;
    }

    /**解密用户数据*/
    @RequestMapping("/decryptData")
    @HystrixCommand(fallbackMethod = "decryptDataError", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
    public ResponseContent decryptData(HttpServletRequest request,@RequestParam("data")String data,@RequestParam("code")String code){
        ResponseContent responseContent=loginServicePortal.decryptData(data,code);
        return responseContent;
    }

    public ResponseContent checkError(HttpServletRequest request,@RequestParam("code")String code){
        String errmsg="code: "+code+"请求超时，请稍后再试！！！";
        responseContent.setResult(errmsg);
        return responseContent;
    }

    public ResponseContent decryptDataError(HttpServletRequest request,@RequestParam("data")String data,@RequestParam("code")String code){
        responseContent.setResult("解密失败或code已失效（解密时效10分钟）！！！");
        return  responseContent;
    }
}
