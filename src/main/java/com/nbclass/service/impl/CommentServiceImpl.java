package com.nbclass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbclass.enums.CacheKeyPrefix;
import com.nbclass.framework.util.MD5;
import com.nbclass.framework.util.ResponseUtil;
import com.nbclass.mapper.CommentMapper;
import com.nbclass.model.BlogComment;
import com.nbclass.service.CommentService;
import com.nbclass.service.RedisService;
import com.nbclass.vo.CommentVo;
import com.nbclass.vo.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * CommentServiceImpl
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-22
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResponseVo selectBySid(CommentVo vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<BlogComment> comments = commentMapper.selectBySid(vo.getSid());
        PageInfo<BlogComment> pageInfo = new PageInfo<>(comments);
        return ResponseUtil.success("success", pageInfo);
    }

    @Override
    public ResponseVo commentLove(Integer commentId, String ip) {
        String cacheKey = CacheKeyPrefix.COMMENT_LOVE.getPrefix()+commentId+"_"+ip;
        Object obj = redisService.get(cacheKey);
        if(obj==null){
            Map<String,Object> map = new HashMap<>();
            map.put("commentId", commentId);
            map.put("supportNum", true);
            commentMapper.updateNum(map);
            //1小时过期
            redisService.set(cacheKey, true, 1, TimeUnit.HOURS);
        }else{
            return ResponseUtil.error("您已经点过赞了~");
        }
        return ResponseUtil.success();
    }

    @Override
    public ResponseVo save(BlogComment comment){
        try{
            if(null==comment.getMid()){
                String cacheKey = CacheKeyPrefix.COMMENT_LOVE.getPrefix()+comment.getSid();
                Integer floor = redisService.get(cacheKey);
                if(floor==null){
                    floor = commentMapper.selectMaxFloorBySid(comment.getSid());
                    redisService.set(cacheKey, floor + 1);
                }
                comment.setFloor(floor+1);
            }
            if(StringUtils.isNotBlank(comment.getQq())){
                comment.setAvatar("https://q1.qlogo.cn/g?b=qq&nk="+comment.getQq()+"&s=100");
            }
            comment.setCreateTime(new Date());
            commentMapper.insertSelective(comment);
            return ResponseUtil.success("评论提交成功,系统正在审核",comment);
        }catch (Exception e){
            logger.error("system error:{}",e);
            return ResponseUtil.error();
        }

    }
}
