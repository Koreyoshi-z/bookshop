package com.example.pojo;

import lombok.Data;

import java.util.Date;

//用户信息
@Data
public class User {
    private Integer id;
    private String loginName;
    private String password;
    private String name;
    private int sex;
    private String email;
    private String phone;
    private String address;
    private int role; //角色
    private Date createDate;
    private String disabled; //禁用
    private String active; //活跃
}
