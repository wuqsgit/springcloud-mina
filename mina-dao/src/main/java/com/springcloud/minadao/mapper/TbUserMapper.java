package com.springcloud.minadao.mapper;

import com.springcloud.minabean.entity.TbUser;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
    public TbUser select(@Param("id")Integer id);
}
