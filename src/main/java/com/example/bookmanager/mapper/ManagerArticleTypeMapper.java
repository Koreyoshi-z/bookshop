package com.example.bookmanager.mapper;

import com.example.pojo.ArticleType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerArticleTypeMapper {
    //查询所有商品类别
    @Select("select * from ec_article_type")
    List<ArticleType> getAllArticleType();

    //查询所有一级商品类别(大类)
    @Select("select * from ec_article_type where length(code)=4")
    List<ArticleType> findAllFirstArticleType();

    //查询最大的一级商品类别的code
    @Select("select max(code) from ec_article_type where length(code)=4")
    String findMaxFirstCode();

    //添加商品类别
    @Insert("insert into ec_article_type(code,name,remark) values(#{code},#{name},#{remark})")
    void saveArticleType(ArticleType articleType);

    //根据code和len查询商品类别
    @Select("select * from ec_article_type where code like #{code} and length(code)=#{len}")
    List<ArticleType> getArticleType(@Param("code") String code, @Param("len") int len);

    //修改商品类别信息
    @Update("update ec_article_type set name=#{name}, remark=#{remark} where code=#{code}")
    void updateArticleType(ArticleType articleType);

    //删除商品类别信息
    @Delete("delete from ec_article_type where code=#{code}")
    void deleteArticleType(String code);

    //根据code查询商品类别
    @Select("select * from ec_article_type where code=#{code}")
    ArticleType getArticleTypeByCode(String code);

    //查询最大的二级商品类别的code
    @Select("select max(code) from ec_article_type where code like #{parentCode} and length(code)=8")
    String findMaxSecondCode(String parentCode);

}
