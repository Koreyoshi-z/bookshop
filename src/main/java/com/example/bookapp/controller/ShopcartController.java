package com.example.bookapp.controller;

import com.example.bookapp.service.ShopcartService;
import com.example.pojo.Shopcart;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/shopcart")
public class ShopcartController {
    @Autowired
    private ShopcartService shopcartService;

    //添加商品到购物车
    @RequestMapping("/addToShopCart")
    public String addShopCart(HttpSession session, @Param("id") int id, @Param("number") int number) {
        shopcartService.addArticleToShopcart(session, id, number);
        return "redirect:/shopcart/showShopcart";
    }


    //显示购物车详细信息
    @RequestMapping("/showShopcart")
    public String showShopCart(HttpSession session, Model model) {
        List<Shopcart> shopcartList = shopcartService.getAllShopcartByUserId(session);
        model.addAttribute("shopcarts", shopcartList);
        //定义总金额变量
        double totalPrice = 0.0;
        for (Shopcart shopcart : shopcartList) {
            totalPrice += shopcart.getArticle().getDiscountPrice() * shopcart.getBuyNum();
        }
        model.addAttribute("totalPrice", totalPrice);
        return "shopCar";
    }


    //修改商品数量
    @RequestMapping("/updateShopcart")
    public String updateShopcart(HttpSession session, @Param("id") int id, @Param("number") int number) {
        shopcartService.updateShopcart(session, id, number);
        return "redirect:/shopcart/showShopcart";
    }


    //删除商品
    @RequestMapping("/deleteShopcart")
    public String deleteShopcart(HttpSession session, @Param("id") int id) {
        shopcartService.deleteShopcart(session, id);
        return "redirect:/shopcart/showShopcart";
    }

}
