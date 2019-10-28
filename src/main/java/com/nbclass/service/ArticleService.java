package com.nbclass.service;

import com.nbclass.model.BlogArticle;
import com.nbclass.vo.ArticleVo;
import com.nbclass.vo.ResponseVo;

import java.util.List;
import java.util.Map;

/**
 * ArticleService
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-21
 */
public interface ArticleService {

    /**
     * 分页查询
     * @param vo 查询参数vo
     * @return list
     */
    List<BlogArticle> selectList(ArticleVo vo);

    /**
     * 站长推荐
     *
     * @param pageSize 页数
     * @return list
     */
    List<BlogArticle> recommendedList(int pageSize);

    /**
     * 最近文章
     *
     * @param pageSize 页数
     * @return list
     */

    List<BlogArticle> recentList(int pageSize);

    /**
     * 随机文章
     *
     * @param pageSize 页数
     * @return list
     */
    List<BlogArticle> randomList(int pageSize);
    /**
     * 热门文章
     * @param pageSize 页数
     * @return list
     */
    List<BlogArticle> hotList(int pageSize);

    /**
     * 根据别名获取文章
     * @param aliasName 别名
     * @return article
     */
    BlogArticle selectByAliasName(String aliasName);

    /**
     * 网站信息统计
     * @return map
     */
    Map<String, Object> siteInfoStatistics();

    /**
     * 文章查看
     * @return ResponseVo
     */
    ResponseVo articleLook(Integer articleId, String ip);

    /**
     * 文章点赞
     * @return ResponseVo
     */
    ResponseVo articleLove(Integer articleId, String ip);

}
