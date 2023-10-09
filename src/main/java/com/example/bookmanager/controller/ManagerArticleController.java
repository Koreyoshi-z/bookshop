package com.example.bookmanager.controller;

import com.example.bookmanager.service.ManagerArticleService;
import com.example.bookmanager.service.ManagerArticleTypeService;
import com.example.pojo.Article;
import com.example.pojo.ArticleType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/article_manager")
public class ManagerArticleController {
    @Autowired
    private ManagerArticleService managerArticleService;
    @Autowired
    private ManagerArticleTypeService managerArticleTypeService;


    //获取所有商品(分页显示)
    @RequestMapping("/getAll")
    public String getAllArticle(Model model, String typeCode, String title, @RequestParam(value = "pn", defaultValue = "1") int pn) {
        PageHelper.startPage(pn, 10);
        List<Article> allArticle = managerArticleService.getAllArticle(typeCode, title);
        model.addAttribute("articles", allArticle);
        model.addAttribute("typeCode", typeCode);
        model.addAttribute("title", title);
        PageInfo<Article> pageInfo = new PageInfo<>(allArticle, 10);
        model.addAttribute("pageInfo", pageInfo);
        List<ArticleType> firstArticleTypes = managerArticleTypeService.findAllFirstArticleType();
        model.addAttribute("firstArticleTypes", firstArticleTypes);
        return "article/list";
    }


    //下架图书
    @RequestMapping("/removeArticle")
    public String removeArticle(int id) {
        managerArticleService.removeArticleById(id);
        return "redirect:/article_manager/getAll";
    }

    //修改商品信息(前置步骤 —> 查询商品信息)
    @RequestMapping("/showUpdate")
    public String updateArticle(Model model, int id) {
        Article article = managerArticleService.getArticleById(id);
        model.addAttribute("article", article);
        List<ArticleType> articleTypes = managerArticleTypeService.findAllFirstArticleType();
        model.addAttribute("types", articleTypes);
        return "article/updateArticle";
    }


    //添加商品信息(图片在发布目录中,每次重启服务器,图片会找不到,但是信息可以修改成功)
    @RequestMapping("/addArticle")
    public String saveArticle(MultipartFile file, String code, Article article, HttpServletRequest request){
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            //获取保存文件的路径
            String path = request.getServletContext().getRealPath("image/article");
            //如果目录f不存在  就创建该目录
            File f = new File(path);
            if (!f.exists()){
                f.mkdirs();
            }
            //获取原始文件名imageName
            String imageName = file.getOriginalFilename();
            //使用UUID生成一个唯一的字符串，并拼接上原始文件名的后缀部分   生成新的文件名newImageName
            String newImageName = UUID.randomUUID().toString() + StringUtils.substring(imageName,imageName.lastIndexOf("."),imageName.length());
            //创建文件输出流    File.separator是文件分隔符，用于在路径中分隔目录和文件名。
            OutputStream outputStream = new FileOutputStream(path + File.separator + newImageName);
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //循环读取inputStream中的数据，每次读取1024字节，将读取到的数据写入outputStream，并刷新缓冲区
            int len = 0;
            while ((len=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
                outputStream.flush();
            }
            //关闭输出流和输入流
            outputStream.close();
            inputStream.close();
            //设置article属性
            article.setTypeCode(code);
            article.setImage(newImageName);
            //调用service层方法
            managerArticleService.saveArticle(article);
            request.setAttribute("tip","商品添加成功!");
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("tip","商品添加失败!");
        }
        return "redirect:/article_manager/getAll";
    }


    //显示商品详情
    @RequestMapping("/preArticle")
    public String detailArticle(Model model,int id){
        Article article = managerArticleService.getArticleById(id);
        model.addAttribute("article",article);
        return "article/preArticle";
    }


    //添修改商品信息(真正的修改操作  步骤和添加几乎一样)
    @RequestMapping("/updateArticle")
    public String updateArticle(MultipartFile file, String code, Article article, HttpServletRequest request){
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            //获取保存文件的路径
            String path = request.getServletContext().getRealPath("image/article");
            //如果目录f不存在  就创建该目录
            File f = new File(path);
            if (!f.exists()){
                f.mkdirs();
            }
            //获取原始文件名imageName
            String imageName = file.getOriginalFilename();


            //使用UUID生成一个唯一的字符串，并拼接上原始文件名的后缀部分   生成新的文件名newImageName
            String newImageName = UUID.randomUUID().toString() + StringUtils.substring(imageName,imageName.lastIndexOf("."),imageName.length());
            //创建文件输出流    File.separator是文件分隔符，用于在路径中分隔目录和文件名。
            OutputStream outputStream = new FileOutputStream(path + File.separator + newImageName);
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //循环读取inputStream中的数据，每次读取1024字节，将读取到的数据写入outputStream，并刷新缓冲区
            int len = 0;
            while ((len=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,len);
                outputStream.flush();
            }
            //关闭输出流和输入流
            outputStream.close();
            inputStream.close();
            //设置article属性
            article.setTypeCode(code);
            article.setImage(newImageName);
            //调用service层方法
            managerArticleService.updateArticle(article);
            request.setAttribute("tip","商品信息修改成功!");
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("tip","商品信息修改失败!");
        }
        return "redirect:/article_manager/getAll";
    }

}
