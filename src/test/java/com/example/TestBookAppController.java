package com.example;

import com.example.bookapp.service.ArticleService;
import com.example.pojo.ArticleType;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestBookAppController {
    //测试Article的业务层
    @Test
    public void test_1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ArticleService articleService = (ArticleService) context.getBean("articleService");
        List<ArticleType> firstArticleType = articleService.findAllFirstArticleType();
        System.out.println(firstArticleType);

    }
}
