package com.example.bookapp.mapper;

import com.example.pojo.Order;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    //保存订单
    void saveOrder(Order order);

    //根据用户id获取该用户所有订单
    List<Order> getOrderByUserId(int userId);

    //根据订单编号修改订单状态(未支付 —> 已支付)
    @Update("update ec_order set pay_Status='1' where order_code=#{orderNo}")
    void updateOrderPayStatusNo(String orderNo);

}
