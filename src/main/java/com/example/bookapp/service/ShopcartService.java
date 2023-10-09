package com.example.bookapp.service;

import com.example.pojo.Shopcart;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ShopcartService {
    //将商品添加到购物车
    void addArticleToShopcart(HttpSession session, int id, int number);

    //显示购物车的的商品信息
    List<Shopcart> getAllShopcartByUserId(HttpSession session);

    //更新购物车的商品数量
    void updateShopcart(HttpSession session, int id, int number);

    //删除购物车中的商品信息
    void deleteShopcart(HttpSession session, int id);


}
