package com.springcloud.minaservicesdk.interfaces;

import com.springcloud.minabean.entity.TbUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("userService")
public interface UserService {

    @RequestMapping("getUserInfo")
    public TbUser getUserInfo(@RequestParam("id") int id);
}
