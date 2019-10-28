package com.nbclass.mapper;

import com.nbclass.framework.util.MyMapper;
import com.nbclass.model.BlogCategory;
import com.nbclass.model.BlogUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Repository
public interface CategoryMapper extends MyMapper<BlogCategory> {
    /**
     * 根据参数查询栏目列表
     * @param category 查询参数
     * @return list
     */
    List<BlogCategory> selectList(BlogCategory category);


}
