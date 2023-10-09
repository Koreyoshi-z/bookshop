package com.example;

import com.sun.mail.smtp.SMTPMessage;
import org.junit.jupiter.api.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import java.util.UUID;

public class MailTest {
    //测试邮件是否发送成功
    @Test
    public void test() {
        try {
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
            smtpMessage.setSubject("用户注册激活，无须回复，按照指引激活！");
            smtpMessage.setContent("<a href='http://127.0.0.1:8088/bookshop/user/active?activeCode=" + UUID.randomUUID().toString() + " 'target='_blank '>注册成功，请点该链接进行激活，无须回复!</a>", "text/html;charset=utf-8");
            smtpMessage.setFrom(new InternetAddress("Ricardo_1225@163.com"));
            //TO 表示收件人 CC 抄送 BCC 密送
            smtpMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("2287160499@qq.com"));
            //发送邮件
            Transport.send(smtpMessage);
            System.out.println("短信发送成功......");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
