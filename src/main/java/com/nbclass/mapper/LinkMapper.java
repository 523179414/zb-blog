package com.nbclass.mapper;

import com.nbclass.framework.util.MyMapper;
import com.nbclass.model.BlogConfig;
import com.nbclass.model.BlogLink;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LinkMapper
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Repository
public interface LinkMapper extends MyMapper<BlogLink> {

    /**
     * 根据参数查询友链列表
     * @param link 查询参数
     * @return list
     */
    List<BlogLink> selectList(BlogLink link);

    /**
     * 根据参数查询友链列表
     * @param ids id集合
     * @return int
     */
    int deleteBatch(Integer[] ids);
}
