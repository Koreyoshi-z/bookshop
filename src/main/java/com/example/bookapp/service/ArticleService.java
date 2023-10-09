package com.example.bookapp.service;

import com.example.pojo.Article;
import com.example.pojo.ArticleType;

import java.util.List;

public interface ArticleService {
    //查询所有父类商品类别
    List<ArticleType> findAllFirstArticleType();

    //查询所有子类商品类别
    List<ArticleType> findAllSecondArticleType(String typeCode);

    //根据商品类型和关键字查询商品信息
    List<Article> findAllArticle(String typeCode, String keyword);

    //根据商品id查询次商品详细信息
    Article getArticleById(int id);

    //查询商品的总记录数(分页)
    int findArticleNum(String typeCode, String keyword);

}
