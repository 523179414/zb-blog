package com.nbclass.service.impl;

import com.nbclass.mapper.CategoryMapper;
import com.nbclass.model.BlogCategory;
import com.nbclass.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<BlogCategory> selectAll() {
        return categoryMapper.selectList(new BlogCategory());
    }

    @Override
    public List<BlogCategory> selectList(BlogCategory category) {
        return categoryMapper.selectList(category);
    }

    @Override
    public BlogCategory selectByAlias(String alias) {
        BlogCategory category = new BlogCategory();
        category.setAliasName(alias);
        return categoryMapper.selectOne(category);
    }
}
