package com.example.bookmanager.controller;

import com.example.bookmanager.service.ManagerOrderService;
import com.example.pojo.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order_manager")
public class ManagerOrderController {
    @Autowired
    private ManagerOrderService managerOrderService;

    //查询所有订单(分页)
    @RequestMapping("/getAll")
    public String getAllOrders(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 2);
        List<Order> orderList = managerOrderService.getAllOrders();
        System.out.println("orderList==" + orderList);
        PageInfo<Order> pageInfo = new PageInfo<>(orderList, 10);
        System.out.println("pageInfo==" + pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        return "order/list";
    }


    //更改订单状态(*********)
    @RequestMapping("/checkOrder")
    public String checkOrder(Order order) {
        managerOrderService.checkOrder(order);
        return "redirect:/order_manager/getAll";
    }


}
