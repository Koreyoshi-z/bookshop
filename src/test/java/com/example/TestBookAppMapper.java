package com.example;

import com.example.bookapp.service.UserService;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBookAppMapper {
    //测试数据源
    @Test
    public void test_1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        Object druidDataSource = context.getBean("druidDataSource");
        System.out.println(druidDataSource);
    }

    //测试User的业务层方法
    @Test
    public void test_2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setLoginName("kkk");
        user.setPassword("fendou");
        User userByNameAndPassword = userService.findUserByNameAndPassword(user);
        System.out.println(userByNameAndPassword);
    }

    //测试注册功能
    @Test
    public void test_3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setLoginName("bbb");
        user.setPassword("55555");
        user.setEmail("2287160499@qq.com");
        userService.saveUser(user,null);

    }














}
