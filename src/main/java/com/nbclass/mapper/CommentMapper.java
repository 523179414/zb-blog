package com.nbclass.mapper;

import com.nbclass.framework.util.MyMapper;
import com.nbclass.model.BlogArticle;
import com.nbclass.model.BlogComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * CommentMapper
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Repository
public interface CommentMapper extends MyMapper<BlogComment> {
    /**
     * 根据sid查询评论列表
     * @param sid 评论主体id
     * @return list
     */
    List<BlogComment> selectBySid(Integer sid);

    /**
     * 更新评论相关数量
     * @return int
     */
    int updateNum(Map<String,Object> map);

    /**
     * 根据sid查询最大楼层
     * @param sid 评论主体id
     * @return int
     */
    int selectMaxFloorBySid(Integer sid);

}
