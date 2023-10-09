package com.example.bookapp.service;


import com.example.pojo.Order;

import java.util.List;

public interface OrderService {
    //保存订单
    void orderSubmit(String orderInfo);

    //查询用户订单
    List<Order> getOrderByUserId();

    //修改支付状态
    void updateOrderPayStatus(String orderNo);


}
