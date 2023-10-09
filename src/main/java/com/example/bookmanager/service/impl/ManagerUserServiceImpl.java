package com.example.bookmanager.service.impl;

import com.example.bookmanager.mapper.ManagerUserMapper;
import com.example.bookmanager.service.ManagerUserService;
import com.example.pojo.User;
import com.sun.mail.smtp.SMTPMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service("managerUserService")
public class ManagerUserServiceImpl implements ManagerUserService {
    @Autowired
    private ManagerUserMapper managerUserMapper;

    //后台用户登录
    @Override
    public User getUserByLoginNameAndPassword(User user) {
        return managerUserMapper.getUserByLoginNameAndPassword(user);
    }


    //保存用户(后台用户注册)
    @Override
    public void saveUser(User user, HttpServletRequest request) {
        try {
            //生成随机激活码
            String active = UUID.randomUUID().toString();
            user.setActive(active);
            //生成注册账号的时间
            user.setCreateDate(new Date());
            managerUserMapper.saveUser(user);
            //设置邮件服务器(163邮箱)
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host", "smtp.163.com");
            //邮件服务需要权限  指用户名必须登录邮件服务器才旋发送邮件
            properties.setProperty("mail.smtp.auth", "true");
            //账号密码授权
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("Ricardo_1225", "UWAZAZDJXUUHYAPG");
                }
            };
            //session对象与服务器连接
            Session session = Session.getInstance(properties, authenticator);
            //SMTPMessage对象 用于封装邮件相关信息（主题 发件人 邮件内容）
            SMTPMessage smtpMessage = new SMTPMessage(session);
            smtpMessage.setSubject("用户注册激活，无须回复，按照指引激活!");
            smtpMessage.setContent("<a href='http://127.0.0.1:8080" + request.getContextPath() + "/user_manager/active?activeCode=" + user.getActive() + " 'target='_blank '>注册成功，请点该链接进行激活，无须回复!</a>", "text/html;charset=utf-8");
            smtpMessage.setFrom(new InternetAddress("Ricardo_1225@163.com"));
            //TO 表示收件人 CC 抄送 BCC 密送
            smtpMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            //发送邮件
            Transport.send(smtpMessage);
        } catch (Exception e) {
            e.getMessage();
        }
    }


    //激活用户(更新激活码)
    @Override
    public void active(String activeCode) {
        managerUserMapper.active(activeCode);
    }


    //验证登录名(是否重复)
    @Override
    public String validateLoginName(String loginName) {
        User user = managerUserMapper.validateLoginName(loginName);
        return user == null ? "not" : "exist";
    }

}
