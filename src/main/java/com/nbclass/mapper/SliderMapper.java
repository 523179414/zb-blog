package com.nbclass.mapper;

import com.nbclass.framework.util.MyMapper;
import com.nbclass.model.BlogLink;
import com.nbclass.model.BlogSlider;
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
public interface SliderMapper extends MyMapper<BlogSlider> {
    /**
     * 根据type查询轮播/公告列表
     * @param type
     * @return list
     */
    List<BlogSlider> selectByType(Integer type);


}
