package com.springcloud.minaservice.services;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.minabean.common.BaiduDecrypt;
import com.springcloud.minabean.common.ResponseContent;
import com.springcloud.minabean.enums.ResponseEnum;
import com.springcloud.minaservicesdk.interfaces.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginServiceImpl implements LoginService {
    @Autowired
    private ResponseContent responseContent;

    @Autowired
    RestTemplate restTemplate;

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    private static final String BAIDUSESSIONKEY="baidu_session_key_";
    @Override
    public ResponseContent checkSession(String url,String code) {
        String sessionKey=restTemplate.getForObject(url,String.class);
        JSONObject jsonObject=JSONObject.parseObject(sessionKey);
        if(jsonObject!=null){
            if(jsonObject.containsKey("session_key")){
                String sesKey=jsonObject.getString("session_key");
                String redisKey=BAIDUSESSIONKEY+code;
                redisTemplate.opsForValue().set(redisKey,sesKey,10, TimeUnit.MINUTES);
                responseContent.setErrorCode(ResponseEnum.SUCCESS.getCode());
                responseContent.setErrorMsg(ResponseEnum.SUCCESS.getMsg());
            }
        }
        return responseContent;
    }

    @Override
    public ResponseContent decryptData(String data,String code) {
        BaiduDecrypt baidu=new BaiduDecrypt();
        try {
            String key=BAIDUSESSIONKEY+code;
            String sessionKey=redisTemplate.opsForValue().get(key).toString();
            if(StringUtils.isNotBlank(sessionKey)) {
                String result = baidu.decrypt(data, sessionKey);
                responseContent.setErrorMsg(ResponseEnum.SUCCESS.getMsg());
                responseContent.setErrorCode(ResponseEnum.SUCCESS.getCode());
                responseContent.setResult(result);
            }
            responseContent.setErrorMsg("该code尚未进行获取sessionkey校验，或已超时，请核对后重试");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }
}
