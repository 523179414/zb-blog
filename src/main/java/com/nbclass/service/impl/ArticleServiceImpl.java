package com.nbclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.nbclass.enums.CacheKeyPrefix;
import com.nbclass.framework.util.CoreConst;
import com.nbclass.framework.util.ResponseUtil;
import com.nbclass.mapper.ArticleMapper;
import com.nbclass.model.BlogArticle;
import com.nbclass.service.ArticleService;
import com.nbclass.service.RedisService;
import com.nbclass.vo.ArticleVo;
import com.nbclass.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * ArticleServiceImpl
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-21
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<BlogArticle> selectList(ArticleVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<BlogArticle> list = articleMapper.selectList(vo);
        if (!CollectionUtils.isEmpty(list)) {
            List<Integer> ids = new ArrayList<>();
            for (BlogArticle article : list) {
                ids.add(article.getId());
            }
            List<BlogArticle> tagArticles = articleMapper.selectTagsByArticleId(ids);
            Map<Integer, BlogArticle> tagMap = new LinkedHashMap<>(tagArticles.size());
            for (BlogArticle bizArticle : tagArticles) {
                tagMap.put(bizArticle.getId(), bizArticle);
            }

            for (BlogArticle bizArticle : list) {
                BlogArticle tagArticle = tagMap.get(bizArticle.getId());
                if(null!=tagArticle){
                    bizArticle.setTags(tagArticle.getTags());
                }
            }
        }
        return list;
    }

    @Override
    public List<BlogArticle> recommendedList(int pageSize) {
        ArticleVo vo = new ArticleVo();
        vo.withIsRec(CoreConst.IS_YES)
            .withStatus(CoreConst.STATUS_VALID)
            .withIsPublic(CoreConst.IS_YES)
            .setPageSize(pageSize);
        return this.selectList(vo);
    }

    @Override
    public List<BlogArticle> recentList(int pageSize) {
        ArticleVo vo = new ArticleVo();
        vo.withIsRecent(CoreConst.IS_YES)
                .withStatus(CoreConst.STATUS_VALID)
                .withIsPublic(CoreConst.IS_YES)
                .setPageSize(pageSize);
        return this.selectList(vo);
    }

    @Override
    public List<BlogArticle> randomList(int pageSize) {
        ArticleVo vo = new ArticleVo();
        vo.withIsRandom(CoreConst.IS_YES)
                .withStatus(CoreConst.STATUS_VALID)
                .withIsPublic(CoreConst.IS_YES)
                .setPageSize(pageSize);
        return this.selectList(vo);
    }

    @Override
    public List<BlogArticle> hotList(int pageSize) {
        ArticleVo vo = new ArticleVo();
        vo.withIsHot(CoreConst.IS_YES)
                .withStatus(CoreConst.STATUS_VALID)
                .withIsPublic(CoreConst.IS_YES)
                .setPageSize(pageSize);
        return this.selectList(vo);
    }

    @Override
    public BlogArticle selectByAliasName(String aliasName) {
        return articleMapper.selectByAliasName(aliasName);
    }

    @Override
    public Map<String, Object> siteInfoStatistics() {
        return articleMapper.siteInfoStatistics();
    }

    @Override
    public ResponseVo articleLook(Integer articleId, String ip) {
        String cacheKey = CacheKeyPrefix.ARTICLE_LOOK.getPrefix()+articleId+"_"+ip;
        Object obj = redisService.get(cacheKey);
        if(obj==null){
            Map<String,Object> map = new HashMap<>();
            map.put("articleId", articleId);
            map.put("lookNum", true);
            articleMapper.updateNum(map);
            //1小时过期
            redisService.set(cacheKey, true, 1, TimeUnit.HOURS);
        }
        return ResponseUtil.success();
    }

    @Override
    public ResponseVo articleLove(Integer articleId, String ip) {
        String cacheKey = CacheKeyPrefix.ARTICLE_LOVE.getPrefix()+articleId+"_"+ip;
        Object obj = redisService.get(cacheKey);
        if(obj==null){
            Map<String,Object> map = new HashMap<>();
            map.put("articleId", articleId);
            map.put("supportNum", true);
            articleMapper.updateNum(map);
            //1小时过期
            redisService.set(cacheKey, true, 1, TimeUnit.HOURS);
        }else{
            return ResponseUtil.error("您已经点过赞了~");
        }
        return ResponseUtil.success();
    }
}
