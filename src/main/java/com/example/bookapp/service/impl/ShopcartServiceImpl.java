package com.example.bookapp.service.impl;

import com.example.bookapp.mapper.ShopcartMapper;
import com.example.bookapp.service.ShopcartService;
import com.example.pojo.Shopcart;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("shopcartService")
public class ShopcartServiceImpl implements ShopcartService {
    @Autowired
    private ShopcartMapper shopcartMapper;

    //将商品添加到购物车
    @Override
    public void addArticleToShopcart(HttpSession session, int id, int number) {
        User user = (User) session.getAttribute("session_user");
        Integer userId = user.getId();
        //先查询用户的购物车是否存在id这件商品
        //如果存在就在原来的数量上+1    不存在就在购物车中添加商品信息
        Shopcart shopcart = shopcartMapper.getShopcartByUserIdAndArticleId(userId, id);
        if (shopcart != null) {
            shopcartMapper.updateShopcart(userId, id, number);
        } else {
            shopcartMapper.addShopcart(userId, id, number);
        }
    }


    //显示购物车的的商品信息
    @Override
    public List<Shopcart> getAllShopcartByUserId(HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        Integer userId = user.getId();
        List<Shopcart> shopcarts = shopcartMapper.getAllShopcartByUserId(userId);
        return shopcarts;
    }


    //更新购物车的商品数量
    @Override
    public void updateShopcart(HttpSession session, int id, int number) {
        User user = (User) session.getAttribute("session_user");
        Integer userId = user.getId();
        shopcartMapper.updateShopcart2(userId, id, number);

    }


    //删除购物车中的商品信息
    @Override
    public void deleteShopcart(HttpSession session, int id) {
        User user = (User) session.getAttribute("session_user");
        Integer userId = user.getId();
        shopcartMapper.deleteShopcart(userId, id);
    }

}
