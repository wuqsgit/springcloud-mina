package com.springcloud.minabean.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class TbUser implements Serializable {

    private Integer id;
    private String address;
    private Date birthday;
    private Date createTime;
    private Integer deleteStatus;
    private String description;
    private String email;
    private Integer locked;
    private String nickName;
    private String password;
    private Integer sex;
    private String telephone;
    private Date updateTime;
    private String userName;
}
