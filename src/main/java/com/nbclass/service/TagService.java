package com.nbclass.service;

import com.nbclass.model.BlogTag;

import java.util.List;

/**
 * TagService
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
public interface TagService {

    List<BlogTag> selectAll();
}
