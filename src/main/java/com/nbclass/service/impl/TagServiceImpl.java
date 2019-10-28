package com.nbclass.service.impl;

import com.nbclass.mapper.TagMapper;
import com.nbclass.model.BlogTag;
import com.nbclass.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TagServiceImpl
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public List<BlogTag> selectAll() {
        return tagMapper.selectAll();
    }
}
