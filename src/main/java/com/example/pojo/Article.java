package com.example.pojo;

import lombok.Data;

import java.util.Date;

//(图书)商品信息
@Data
public class Article {
    private Integer id;
    private String title;
    private String supplier; //供应商
    private Double price;
    private Double discount; //折扣
    private String locality; //出版地
    private Date putawayDate;
    private int storage; //库存数
    private String image; //图片名称
    private String description; //描述
    private String typeCode; //类别码
    private Date createDate; //生产日期
    private String disabled; //禁用

    //计算折扣价格
    public double getDiscountPrice() {
        return this.getPrice() * this.getDiscount();
    }

}
