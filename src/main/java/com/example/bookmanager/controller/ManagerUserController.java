package com.example.bookmanager.controller;

import com.example.bookmanager.service.ManagerUserService;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user_manager")
public class ManagerUserController {
    @Autowired
    private ManagerUserService managerUserService;

    //用户后台注册页面——显示
    @RequestMapping("/showRegisterPage")
    public String showRegisterPage(){
        return "user/register";
    }


    //用户后台登录页面——显示
    @RequestMapping("/showLoginPage")
    public String showLoginPage(){
        return "user/login";
    }


    //用户后台注册
    @RequestMapping("/userRegister")
    public String userRegister(User user, Model model, HttpServletRequest request){
        try {
            managerUserService.saveUser(user,request);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("tip","注册失败!");
        }
        return "user/login";
    }


    //用户后台登录
    @RequestMapping("/userLogin")
    public String userLogin(User user, Model model, HttpSession session){
        User u = managerUserService.getUserByLoginNameAndPassword(user);
        if (u==null){
            model.addAttribute("tip","您输入的账号或密码不正确!");
        }else if ("0".equals(u.getDisabled())){
            model.addAttribute("tip","您尚未激活，请激活后再进行相关操作");
            return "user/login";
        }else {
            session.setAttribute("session_user",u);
            return "redirect:/article_manager/getAll";
        }
        return "user/login";
    }


    //激活用户(更新激活码)
    @RequestMapping("/active")
    public String active(String activeCode,Model model){
        try {
            managerUserService.active(activeCode);
            model.addAttribute("tip","激活成功!");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("tip","激活失败!");
        }
        return "user/login";
    }


    //验证登录名(是否重复)
    @ResponseBody
    @RequestMapping("/validName")
    public String validateLoginName(String loginName){
        String tip = managerUserService.validateLoginName(loginName);
        return tip; //返回json格式
    }


    //退出登录
    @RequestMapping("/logout")
    public String loginOut(HttpSession session){
        session.removeAttribute("session_user");
        return "user/login";
    }

}
