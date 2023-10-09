package com.example.bookapp.controller;

import com.example.bookapp.service.UserService;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录跳转
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //注册跳转
    @RequestMapping("/reg")
    public String register() {
        return "register";
    }


    //登录功能
    @RequestMapping("/userLogin")
    public String userLogin(User user, Model model, HttpSession session) {
        User u = userService.findUserByNameAndPassword(user);
        if (u == null) {
            model.addAttribute("error_message", "您输入的账号或密码错误，请核实！");
            return "login";
        } else if (u.getDisabled().equals("0")) {
            model.addAttribute("error_message", "您的账号尚未激活，请打开你的邮箱激活！");
            return "login";
        } else {
            session.setAttribute("session_user", u);
            return "redirect:/article/index";
        }
    }


    //登出功能
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        //将用户信息从session中删除
        session.removeAttribute("session_user");
        return "redirect:/article/index";
    }


    //注册功能
    @RequestMapping("/userRegister")
    public String userRegister(User user, HttpServletRequest request, Model model) {
        try {
            userService.saveUser(user, request);
            model.addAttribute("message", "注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "注册失败!");
        }
        return "register";
    }


    //激活账号
    @RequestMapping("/active")
    public String active(Model model, String activeCode) {
        String message = userService.active(activeCode);
        try {
            model.addAttribute("message", !message.equals("") ? message : "激活成功!");
        } catch (Exception e) {
            model.addAttribute("message", "激活失败!");
        }
        return "login";
    }

    //验证登录名是否重复
    @ResponseBody
    @RequestMapping(value = "/validateLoginName", produces = {"application/text;charset=utf-8"})
    public  String validateLoginName(String loginName){
        String result = userService.validateLoginName(loginName);
        return result;
    }

}
