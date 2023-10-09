package com.example.bookapp.service.impl;

import com.example.bookapp.mapper.ArticleMapper;
import com.example.bookapp.mapper.ArticleTypeMapper;
import com.example.bookapp.service.ArticleService;
import com.example.pojo.Article;
import com.example.pojo.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    //查询所有父类商品类别
    @Override
    public List<ArticleType> findAllFirstArticleType() {
        List<ArticleType> articleType = articleTypeMapper.getFirstArticleType();
        return articleType;
    }

    //查询所有子类商品类别
    @Override
    public List<ArticleType> findAllSecondArticleType(String typeCode) {
        List<ArticleType> secondArticleType = articleTypeMapper.getSecondArticleType(typeCode);
        return secondArticleType;
    }

    //根据商品类型和关键字查询商品信息
    @Override
    public List<Article> findAllArticle(String typeCode, String keyword) {
        List<Article> articles = articleMapper.queryAllArticle(typeCode, keyword);
        return articles;
    }

    //根据商品id查询次商品详细信息
    @Override
    public Article getArticleById(int id) {
        Article article = articleMapper.queryArticleById(id);
        return article;
    }

    //查询商品的总记录数(分页)
    @Override
    public int findArticleNum(String typeCode, String keyword) {
        return articleMapper.getArticleNum(typeCode, keyword);
    }

}
