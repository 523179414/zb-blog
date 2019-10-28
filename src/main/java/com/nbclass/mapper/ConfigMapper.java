package com.nbclass.mapper;

import com.nbclass.framework.util.MyMapper;
import com.nbclass.model.BlogArticle;
import com.nbclass.model.BlogConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ConfigMapper
 *
 * @version V1.0
 * @date 2019/10/10
 * @author nbclass
 */
@Repository
public interface ConfigMapper extends MyMapper<BlogConfig> {
    /**
     * 根据参数查询配置列表
     * @param config
     * @return list
     */
    List<BlogConfig> selectList(BlogConfig config);


}
