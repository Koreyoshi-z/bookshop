package com.example.bookmanager.service;

import com.example.pojo.Article;

import java.util.List;

public interface ManagerArticleService {
    //根据商品类别和标题查询商品
    List<Article> getAllArticle(String typeCode, String title);

    //根据商品id查询该商品详情
    Article getArticleById(int id);

    //添加图书
    void saveArticle(Article article);

    //修改图书信息
    void updateArticle(Article article);

    //删除图书(逻辑删除)
    void removeArticleById(int id);

}
