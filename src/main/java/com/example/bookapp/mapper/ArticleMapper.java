package com.example.bookapp.mapper;

import com.example.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    //根据商品类别和关键字查询商品
    List<Article> queryAllArticle(@Param("typeCode") String typeCode, @Param("keyword") String keyword);

    //条件过滤搜索之后的总记录数(分页)
    int getArticleNum(@Param("typeCode") String typeCode, @Param("keyword") String keyword);

    //根据商品id查询该商品详情
    @Select("select * from ec_article where id=#{id}")
    Article queryArticleById(int id);

}
