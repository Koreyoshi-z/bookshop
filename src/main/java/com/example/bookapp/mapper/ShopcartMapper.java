package com.example.bookapp.mapper;

import com.example.pojo.Shopcart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopcartMapper {
    //添加商品到购物车
    @Insert("insert into ec_shopcart(buynum,user_id,article_id) values(#{number},#{userId},#{articleId})")
    void addShopcart(@Param("userId") int userId, @Param("articleId") int articleId, @Param("number") int number);

    //根据用户id及商品1d删除购物车的信息
    @Delete("delete from ec_shopcart where user_id=#{userId} and article_id=#{articleId}")
    void deleteShopcart(@Param("userId") int userId, @Param("articleId") int articleId);

    //更新购物车信息(基本上就只是更改商品数量)
    @Update("update ec_shopcart set buynum=#{number}+buynum where user_id=#{userId} and article_id=#{articleId}")
    void updateShopcart(@Param("userId") int userId, @Param("articleId") int articleId, @Param("number") int number);

    @Update("update ec_shopcart set buynum=#{buynum} where user_id=#{userId} and article_id=#{articleId}")
    void updateShopcart2(@Param("userId") int userId, @Param("articleId") int articleId, @Param("buynum") int buynum);

    //查询此用户的购物车信息
    @Select("select * from ec_shopcart where user_id=#{userId} and article_id=#{articleId}")
    Shopcart getShopcartByUserIdAndArticleId(@Param("userId") int userId,@Param("articleId") int articleId);

    //查询购物车详情(商品信息)
    List<Shopcart> getAllShopcartByUserId(int userId);

}
