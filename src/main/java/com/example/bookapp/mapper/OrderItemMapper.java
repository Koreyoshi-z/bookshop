package com.example.bookapp.mapper;

import com.example.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemMapper {
    //保存订单明细
    @Insert("insert into ec_order_item(order_id,article_id,order_num) values(#{orderId},#{articleId},#{orderNum})")
    void saveItem(OrderItem item);

    //根据订单id查询订单明细
    void getOrderItemByOrderId(int orderId);
}
