package com.nbclass.controller.blog;

import com.github.pagehelper.PageInfo;
import com.nbclass.enums.SliderType;
import com.nbclass.framework.config.properties.ZbProperties;
import com.nbclass.framework.exception.ResourceNotFoundException;
import com.nbclass.framework.util.CoreConst;
import com.nbclass.framework.theme.ZbTheme;
import com.nbclass.model.BlogArticle;
import com.nbclass.model.BlogCategory;
import com.nbclass.service.*;
import com.nbclass.vo.ArticleVo;
import com.nbclass.vo.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

/**
 * 前端页面控制器
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Controller
public class BlogWebController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SliderService sliderService;
    /**
     * 首页入口
     */
    @GetMapping("/")
    public String index(Model model) {
        ArticleVo vo = new ArticleVo();
        loadArticle(model, vo, "index");
        model.addAttribute("notifyList",sliderService.selectByType(SliderType.NOTIFY.getType()));
        model.addAttribute("sliderList",sliderService.selectByType(SliderType.SLIDER.getType()));
        return String.format("theme/%s/%s", CoreConst.currentTheme, "index");
    }

    @GetMapping("index/{pageNum}")
    public String index(@PathVariable("pageNum") Integer pageNum, Model model) {
        ArticleVo vo = new ArticleVo();
        vo.setPageNum(pageNum);
        loadArticle(model, vo, "index");
        return String.format("theme/%s/%s", CoreConst.currentTheme, "index");
    }

    @GetMapping("/{category}")
    public String category(@PathVariable("category") String category, Model model){
        BlogCategory blogCategory = categoryService.selectByAlias(category);
        if(blogCategory==null){
            throw new ResourceNotFoundException();
        }
        ArticleVo vo = new ArticleVo();
        vo.setCategory(category);
        loadArticle(model, vo, category);
        return String.format("theme/%s/%s", CoreConst.currentTheme,  blogCategory.getTemplate());
    }

    @GetMapping("/{category}/page/{pageNum}")
    public String category(@PathVariable("category") String category, @PathVariable("pageNum") Integer pageNum, Model model){
        BlogCategory blogCategory = categoryService.selectByAlias(category);
        if(blogCategory==null){
            throw new ResourceNotFoundException();
        }
        ArticleVo vo = new ArticleVo();
        vo.setPageNum(pageNum);
        vo.setCategory(category);
        loadArticle(model, vo, category);
        return String.format("theme/%s/%s", CoreConst.currentTheme,  blogCategory.getTemplate());
    }

    @GetMapping("/tag/{tagId}")
    public String test(@PathVariable("tagId") Integer tagId){
        return "";
    }


    /**
     * 文章详情
     *
     * @param model
     * @param alias
     * @return
     */
    @GetMapping("/a/{alias}")
    public String article(Model model, @PathVariable("alias") String alias) {
        BlogArticle article = articleService.selectByAliasName(alias);
        if (article == null || CoreConst.STATUS_INVALID.equals(article.getStatus()) ) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("article", article);
        return String.format("theme/%s/%s", CoreConst.currentTheme,  article.getTemplate());
    }

    private void loadArticle(Model model, ArticleVo vo, String pageUrl) {
        vo.setStatus(CoreConst.STATUS_VALID);
        List<BlogArticle> articleList =  articleService.selectList(vo);
        PageInfo<BlogArticle> pageInfo = new PageInfo<>(articleList);
        PageVo pageVo = new PageVo();
        BeanUtils.copyProperties(pageInfo,pageVo);
        model.addAttribute("page", pageVo);
        model.addAttribute("pageUrl", pageUrl);
        model.addAttribute("articleList",articleList);
    }


}
