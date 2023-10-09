package com.example.bookapp.mapper;

import com.example.pojo.ArticleType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTypeMapper {
    //获取所有一级商品类别(大类)
    @Select("select * from ec_article_type where length(code)=4")
    List<ArticleType> getFirstArticleType();

    //查询一级商品类别下所有的二级商品类别
    @Select("select * from ec_article_type where code like #{typeCode} '%' and length(code)=8")
    List<ArticleType> getSecondArticleType(String typeCode);

}
