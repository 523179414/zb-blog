package com.nbclass.service;

import com.nbclass.model.BlogCategory;

import java.util.List;

/**
 * CategoryService
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
public interface CategoryService {

    List<BlogCategory> selectAll();

    List<BlogCategory> selectList(BlogCategory category);

    BlogCategory selectByAlias(String alias);
}
