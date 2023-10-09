package com.example.bookapp.service.impl;

import com.example.bookapp.mapper.OrderItemMapper;
import com.example.bookapp.mapper.OrderMapper;
import com.example.bookapp.service.OrderService;
import com.example.pojo.Order;
import com.example.pojo.OrderItem;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;


    //保存订单
    @Override
    public void orderSubmit(String orderInfo) {
        Order order = new Order();
        //解析orderInfo   #76_4_200.0 —> 商品id是76 购买数量是4 价格是200.0
        String[] orderInfos = orderInfo.substring(1).split("#");
        //根据session获取userId
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User) session.getAttribute("session_user");
        int userId = user.getId();
        order.setUserId(userId);
        //生成order_code  PO-yyyyMMddHHmmss+userId
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        order.setOrderCode("PO-" + simpleDateFormat.format(new Date()) + userId);
        order.setCreateDate(new Date());
        //总金额
        double totalAmount = 0.0;
        List<OrderItem> items = new ArrayList<>();
        //遍历获取 商品id 购买数量 总金额
        for (String info : orderInfos) {
            String[] s = info.split("_");
            int articleId = Integer.valueOf(s[0]);
            int buyNum = Integer.valueOf(s[1]);
            OrderItem orderItem = new OrderItem();
            orderItem.setArticleId(articleId);
            orderItem.setOrderNum(buyNum);
            items.add(orderItem);
            totalAmount += Double.valueOf(s[2]);
        }
        order.setAmount(totalAmount);
        orderMapper.saveOrder(order);
        //保存订单明细
        int orderId = order.getId();
        for (OrderItem item : items) {
            item.setOrderId(orderId);
            orderItemMapper.saveItem(item);
        }
    }


    //查询用户订单
    @Override
    public List<Order> getOrderByUserId() {
        //根据session获取userId
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        User user = (User) session.getAttribute("session_user");
        int userId = user.getId();
        List<Order> orderList = orderMapper.getOrderByUserId(userId);
        return orderList;
    }


    //修改支付状态(调用支付宝接口)
    @Override
    public void updateOrderPayStatus(String orderNo) {

    }

}
