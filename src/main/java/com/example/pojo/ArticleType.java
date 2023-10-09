package com.example.pojo;

import lombok.Data;

//(图书)商品类别
@Data
public class ArticleType {
    private String code;
    private String name;
    private String remark; //备注
}
