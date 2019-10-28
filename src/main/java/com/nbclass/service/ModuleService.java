package com.nbclass.service;

import com.nbclass.enums.SliderType;
import com.nbclass.framework.util.CoreConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 权限模块，thymeleaf调用
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-12
 */
@Component("module")
public class ModuleService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private SliderService sliderService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private LinkService linkService;

    public Object get(String moduleName) {
        switch (moduleName) {
            case "categoryList":        //分类
                return categoryService.selectAll();
            case "tagList":             //标签
                return tagService.selectAll();
            case "linkList":            //友链
                return linkService.selectAll();
            case "notifyList":          //系统公告
                return sliderService.selectByType(SliderType.NOTIFY.getType());
            case "sliderList":          //轮播文章
                return sliderService.selectByType(SliderType.SLIDER.getType());
            case "recentList":          //最近文章
                return articleService.recentList(CoreConst.PAGE_SIZE);
            case "recommendedList":    //推荐文章
                return articleService.recommendedList(CoreConst.PAGE_SIZE);
            case "hotList":             //热门文章
                return articleService.hotList(CoreConst.PAGE_SIZE);
            case "randomList":         //随机文章
                return articleService.randomList(CoreConst.PAGE_SIZE);
            case "siteInfo":            //网站信息统计
                return articleService.siteInfoStatistics();
            case "sysConfig":           //网站基本信息
                return configService.selectAll();
           /*
            case "latestComments":      //最新评论
                PageHelper.startPage(1,10);
                CommentConditionVo commentConditionVo = new CommentConditionVo();
                commentConditionVo.setStatus(1);
                return commentService.selectComments(commentConditionVo);*/

            default:
                return null;
        }
    }
}
