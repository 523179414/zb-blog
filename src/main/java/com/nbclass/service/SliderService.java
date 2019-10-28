package com.nbclass.service;

import com.nbclass.model.BlogSlider;

import java.util.List;

/**
 * SliderService
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
public interface SliderService {

    List<BlogSlider> selectByType(Integer type);
}
