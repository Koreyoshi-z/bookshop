package com.example.pojo;

import lombok.Data;

//购物车
@Data
public class Shopcart {
    private Integer buyNum;
    private Integer userId;
    private Integer articleId;
    private Article article; //关联关系  通过购物车的商品id 查看商品信息
}
