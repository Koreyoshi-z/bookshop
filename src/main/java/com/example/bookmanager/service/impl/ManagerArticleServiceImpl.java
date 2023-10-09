package com.example.bookmanager.service.impl;

import com.example.bookmanager.mapper.ManagerArticleMapper;
import com.example.bookmanager.service.ManagerArticleService;
import com.example.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("managerArticleService")
public class ManagerArticleServiceImpl implements ManagerArticleService {
    @Autowired
    private ManagerArticleMapper managerArticleMapper;

    //根据商品类别和标题查询商品
    @Override
    public List<Article> getAllArticle(String typeCode, String title) {
        return managerArticleMapper.getAllArticle(typeCode, title);
    }

    //根据商品id查询该商品详情
    @Override
    public Article getArticleById(int id) {
        return managerArticleMapper.getArticleById(id);
    }

    //添加图书
    @Override
    public void saveArticle(Article article) {
        //生成 createDate 和 putawayDate
        article.setCreateDate(new Date());
        article.setPutawayDate(new Date());
        managerArticleMapper.saveArticle(article);
    }

    //修改图书信息
    @Override
    public void updateArticle(Article article) {
        managerArticleMapper.updateArticle(article);
    }

    //删除图书(逻辑删除)
    @Override
    public void removeArticleById(int id) {
        managerArticleMapper.removeArticleById(id);
    }

}
