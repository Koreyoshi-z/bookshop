package com.example.pojo;

import lombok.Data;

//订单明细
@Data
public class OrderItem {
    private Integer orderId; //订单id
    private Integer articleId; //商品id
    private Integer orderNum; //订单数
    private Article article; //关联关系  通过订单明细可以查看商品信息
}
