package com.example.bookmanager.service.impl;

import com.example.bookmanager.mapper.ManagerOrderMapper;
import com.example.bookmanager.service.ManagerOrderService;
import com.example.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("managerOrderService")
public class ManagerOrderServiceImpl implements ManagerOrderService {
    @Autowired
    private ManagerOrderMapper managerOrderMapper;

    //更改订单状态
    @Override
    public void checkOrder(Order order) {
        //检查订单状态
        if (order.getStatus().equals("0")) {
            //订单状态为"0"
            order.setSendDate(null);
        } else {
            //订单状态为"1"  将当前时间设置为sendDate
            order.setSendDate(new Date());
        }
        managerOrderMapper.checkOrder(order);
    }


    //查询所有订单
    @Override
    public List<Order> getAllOrders() {
        return managerOrderMapper.getAllOrders();
    }

}
