package com.example.bookmanager.service;

import com.example.pojo.ArticleType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ManagerArticleTypeService {
    //查询所有商品类别
    List<ArticleType> getAllArticleType();

    //查询所有一级商品类别(大类)
    List<ArticleType> findAllFirstArticleType();

    //添加商品类别
    void saveArticleType(ArticleType articleType, String parentCode);

    //修改商品类别信息
    void updateArticleType(ArticleType articleType);

    //删除商品类别信息
    String deleteArticleType(String code);

    //根据code查询商品类别
    ArticleType getArticleTypeByCode(String code);


}
