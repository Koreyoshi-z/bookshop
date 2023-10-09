package com.example.bookmanager.service;

import com.example.pojo.Order;

import java.util.List;

public interface ManagerOrderService {
    //更改订单状态
    void checkOrder(Order order);

    //查询所有订单
    List<Order> getAllOrders();

}
