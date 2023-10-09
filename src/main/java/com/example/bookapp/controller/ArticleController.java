package com.example.bookapp.controller;

import com.example.bookapp.service.ArticleService;
import com.example.pojo.Article;
import com.example.pojo.ArticleType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //商品主页
    @RequestMapping("/index")
    public String articleIndex(Model model, String typeCode, String keyword, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        List<ArticleType> articleTypes = articleService.findAllFirstArticleType();
        model.addAttribute("articleTypes", articleTypes);
        //获取二级商品
        if (typeCode != null && !typeCode.equals("")) {
            String code = typeCode.substring(0, 4);
            List<ArticleType> secondArticleTypes = articleService.findAllSecondArticleType(code);
            model.addAttribute("secondArticleTypes", secondArticleTypes);

        }
        //分页功能  设置每页的数据是10条记录
        PageHelper.startPage(pn, 10);
        List<Article> articles = articleService.findAllArticle(typeCode, keyword);
        //役置分页的数据，连续显示的页码数
        PageInfo<Article> pageInfo = new PageInfo<>(articles, 10);
        model.addAttribute("pageInfo", pageInfo);
        return "articleIndex";
    }


    //商品详情
    @RequestMapping("/detail")
    public String articleDetail(Integer id, Model model){
        Article article = articleService.getArticleById(id);
        model.addAttribute("article",article);
        return "articleDetail";
    }

}
