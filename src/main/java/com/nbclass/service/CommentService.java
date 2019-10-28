package com.nbclass.service;

import com.nbclass.model.BlogComment;
import com.nbclass.vo.CommentVo;
import com.nbclass.vo.ResponseVo;

import java.io.UnsupportedEncodingException;

/**
 * CommentService
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-22
 */
public interface CommentService {

    /**
     * 根据sid查询所有评论
     * @return ResponseVo
     */
    ResponseVo selectBySid(CommentVo vo);

    /**
     * 评论点赞
     * @return ResponseVo
     */
    ResponseVo commentLove(Integer commentId, String ip);

    /**
     * 保存comment
     * @return ResponseVo
     */
    ResponseVo save(BlogComment comment);
}
