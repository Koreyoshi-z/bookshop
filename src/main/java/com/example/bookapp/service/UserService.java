package com.example.bookapp.service;

import com.example.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    //登录功能
    User findUserByNameAndPassword(User user);

    //验证登录名(是否重复)
    String validateLoginName(String loginName);

    //注册功能
    void saveUser(User user, HttpServletRequest request);

    //用户激活功能
    String active(String activeCode);


}
