package com.nbclass.service.impl;

import com.nbclass.mapper.CategoryMapper;
import com.nbclass.mapper.SliderMapper;
import com.nbclass.model.BlogCategory;
import com.nbclass.model.BlogSlider;
import com.nbclass.service.CategoryService;
import com.nbclass.service.SliderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SliderServiceImpl
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
@Service
public class SliderServiceImpl implements SliderService {

    @Autowired
    private SliderMapper sliderMapper;

    @Override
    public List<BlogSlider> selectByType(Integer type) {
        return sliderMapper.selectByType(type);
    }
}
