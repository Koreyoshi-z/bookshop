package com.example.bookmanager.service.impl;

import com.example.bookmanager.mapper.ManagerArticleTypeMapper;
import com.example.bookmanager.service.ManagerArticleTypeService;
import com.example.pojo.ArticleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("managerArticleTypeService")
public class ManagerArticleTypeServiceImpl implements ManagerArticleTypeService {
    @Autowired
    private ManagerArticleTypeMapper managerArticleTypeMapper;

    //查询所有商品类别
    @Override
    public List<ArticleType> getAllArticleType() {
        return managerArticleTypeMapper.getAllArticleType();
    }

    //查询所有一级商品类别(大类)
    @Override
    public List<ArticleType> findAllFirstArticleType() {
        return managerArticleTypeMapper.findAllFirstArticleType();
    }

    //添加商品类别
    @Override
    public void saveArticleType(ArticleType articleType, String parentCode) {
        //创建一个拼接字符串
        StringBuilder code = new StringBuilder();
        //判断传入的parentCode变量是否为空  parentCode —> 例: 0002 0003
        if (parentCode != null && !parentCode.equals("")) { //parentCode变量不为空
            //查找最大的二级商品对应的code值
            String maxSecondCode = managerArticleTypeMapper.findMaxSecondCode(parentCode + "%");
            //判断最大的二级商品对应的code值(maxSecondCode)是否为空
            if (maxSecondCode == null || maxSecondCode.equals("")) { //maxSecondCode为空则代表一级商品下没有二级商品
                //在parentCode后面拼接0001  例: 0002 —>00020001  0003 —>00030001
                code.append(parentCode).append("0001");
            } else { //maxSecondCode不为空则代表一级商品下存在二级商品
                //首先将parentCode拼接到最开始创建的拼接字符串code上  code —> 0002 0003
                code.append(parentCode);
                //将字符串maxSecondCode的第5位开始的子字符串转换为整数，并加1赋值给codeLastHalf变量
                //例: 00020004 —> 5  00030006 —> 7
                Integer codeLastHalf = Integer.parseInt(maxSecondCode.substring(4)) + 1;
                //根据codeLastHalf的长度，补足4位0   例: 5 —> 0005  7 —> 0007
                for (int i = 0; i < 4 - String.valueOf(codeLastHalf).length(); i++) {
                    code.append("0");
                }
                //最后完成所有字符串的拼接得到最终的商品code
                code.append(codeLastHalf);
            }
        } else { //parentCode变量为空
            //查找最大的一级商品对应的code值
            String maxFirstCode = managerArticleTypeMapper.findMaxFirstCode(); // 0010
            //将字符串maxFirstCode转换为整数并加1
            int maxCode = Integer.parseInt(maxFirstCode) + 1; // 0010 —> 11
            //根据maxCode的长度，补足4位0
            for (int i = 0; i < 4 - String.valueOf(maxCode).length(); i++) {
                code.append("0");
            }
            code.append(maxCode);
        }
        //将一个StringBuffer类型的变量code转换为String类型
        articleType.setCode(code.toString());
        managerArticleTypeMapper.saveArticleType(articleType);
    }


    //修改商品类别信息
    @Override
    public void updateArticleType(ArticleType articleType) {
        managerArticleTypeMapper.updateArticleType(articleType);
    }


    //删除商品类别信息
    @Override
    public String deleteArticleType(String code) {
        //根据code和len查询商品类别
        List<ArticleType> articleType = managerArticleTypeMapper.getArticleType(code + "%", code.length() + 4);
        //判断一级类别下是否存在二级类型商品
        if (articleType.size() > 0) {
            return "删除失败,该商品类型存在下级商品类别,不能删除!";
        } else {
            managerArticleTypeMapper.deleteArticleType(code);
        }
        return "删除成功!";
    }


    //根据code查询商品类别
    @Override
    public ArticleType getArticleTypeByCode(String code) {
        return managerArticleTypeMapper.getArticleTypeByCode(code);
    }

}
