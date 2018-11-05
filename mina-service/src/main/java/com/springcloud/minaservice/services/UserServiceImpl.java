package com.springcloud.minaservice.services;

import com.springcloud.minabean.entity.TbUser;
import com.springcloud.minadao.mapper.TbUserMapper;
import com.springcloud.minaservicesdk.interfaces.UserService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class UserServiceImpl implements UserService {
    @Autowired
    TbUserMapper tbUserMapper;

    @Resource
    RedisTemplate<String,Object>redisTemplate;

    @Resource(name="ehcacheManager")
    CacheManager cacheManager;

    @Override
    public TbUser getUserInfo(int id) {
        TbUser user=(TbUser) redisTemplate.opsForValue().get("tbuser");
        Cache cache=cacheManager.getCache("lemonCache");
        Element element1=cache.get(id);
        if(element1!=null && element1.getObjectValue()!=null){
            System.out.println("ehcachezhong:"+element1.getObjectValue());
        }
        if(user==null){
            user=tbUserMapper.select(id);
            Element element=new Element(id,user);
            cache.put(element);
            redisTemplate.opsForValue().set("tbuser",user,1,TimeUnit.HOURS);
        }

        return user;
    }
}
