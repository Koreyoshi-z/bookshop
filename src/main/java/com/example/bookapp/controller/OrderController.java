package com.example.bookapp.controller;

import com.example.bookapp.service.OrderService;
import com.example.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //提交订单
    @RequestMapping("/orderSubmit")
    public String orderSubmit(String orderInfo){
        orderService.orderSubmit(orderInfo);
        return "redirect:/order/showOrder";
    }

    //显示订单
    @RequestMapping("/showOrder")
    public String showOrder(Model model){
        List<Order> orderList = orderService.getOrderByUserId();
        model.addAttribute("orders",orderList);
        return "order";
    }

}
