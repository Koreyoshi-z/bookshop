package com.example.bookmanager.mapper;

import com.example.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerArticleMapper {
    //根据商品类别和标题查询商品
    List<Article> getAllArticle(@Param("typeCode") String typeCode, @Param("title") String title);

    //根据商品id查询该商品详情
    @Select("select * from ec_article where id=#{id}")
    Article getArticleById(int id);

    //添加图书
    void saveArticle(Article article);

    //修改图书信息
    void updateArticle(Article article);

    //删除(下架)图书(逻辑删除)
    @Update("update ec_article set disabled='1' where id=#{id}")
    void removeArticleById(int id);

}
