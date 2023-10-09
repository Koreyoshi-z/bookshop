package com.example.bookmanager.controller;

import com.example.bookmanager.service.ManagerArticleTypeService;
import com.example.pojo.ArticleType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/articletype_manager")
public class ManagerArticleTypeController {
    @Autowired
    private ManagerArticleTypeService managerArticleTypeService;

    //获取所有商品类别(分页显示)
    @RequestMapping("/getAll")
    public String getAllArticleType(Model model, @RequestParam(value = "pn", defaultValue = "1") int pn) {
        PageHelper.startPage(pn, 10);
        List<ArticleType> articleTypeList = managerArticleTypeService.getAllArticleType();
        PageInfo<ArticleType> pageInfo = new PageInfo<>(articleTypeList, 10);
        model.addAttribute("pageInfo", pageInfo);
        return "articleType/list";
    }


    //显示添加商品类型页面
    @RequestMapping("/showAdd")
    public String showAdd() {
        return "articleType/view";
    }


    //加载一级商品类型
    @ResponseBody
    @RequestMapping("/loadFirstArticleType")
    public String loadFirstArticleType() {
        List<ArticleType> firstArticleType = managerArticleTypeService.findAllFirstArticleType();
        return JSONArray.fromObject(firstArticleType).toString(); //返回json格式
    }


    //添加或修改商品类别(需要做判断)
    @RequestMapping("/addOrUpdate")
    public String updateArticleType(ArticleType type, String parentCode) {
        if (type.getCode() != null && !type.getCode().equals("")) {
            managerArticleTypeService.updateArticleType(type);
        } else {
            managerArticleTypeService.saveArticleType(type, parentCode);
        }
        return "redirect:/articletype_manager/getAll";
    }


    //删除商品类别
    @RequestMapping("/deleteType")
    public String deleteType(@Param("code") String code, Model model) {
        String message = managerArticleTypeService.deleteArticleType(code);
        model.addAttribute("message", message);
        return "forward:/articletype_manager/getAll";
    }


    //修改商品类别
    @RequestMapping("/showUpdate")
    public String updateArticleType(Model model, String code) {
        ArticleType articleType = managerArticleTypeService.getArticleTypeByCode(code);
        model.addAttribute("articleType", articleType);
        return "articleType/view";
    }


}
