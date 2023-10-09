package com.example.bookmanager.mapper;

import com.example.pojo.Order;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerOrderMapper {
    //更改订单状态
    @Update("update ec_order set status=#{status}, send_date=#{sendDate} where id=#{id}")
    void checkOrder(Order order);

    //查询所有订单
    List<Order> getAllOrders();

}
