package com.example.bookmanager.service;

import com.example.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface ManagerUserService {
    //后台用户登录
    User getUserByLoginNameAndPassword(User user);

    //保存用户
    void saveUser(User user, HttpServletRequest request);

    //激活用户(更新激活码)
    void active(String activeCode);

    //验证登录名(是否重复)
    String validateLoginName(String loginName);

}
