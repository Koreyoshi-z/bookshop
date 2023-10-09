package com.example.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//订单信息
@Data
public class Order {
    private Integer id;
    private String orderCode; //订单码
    private Date createDate; //生成日期
    private Date sendDate; //发送日期
    private String status;
    private Double amount;
    private int userId; //用户id
    private String payStatus; //支付状态
    private List<OrderItem> items = new ArrayList<>(); //关联关系
}
