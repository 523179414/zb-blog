package com.nbclass.service.impl;

import com.nbclass.framework.util.CoreConst;
import com.nbclass.mapper.LinkMapper;
import com.nbclass.model.BlogLink;
import com.nbclass.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkMapper linkMapper;


    @Override
    public List<BlogLink> selectList(BlogLink link) {
        return linkMapper.selectList(link);
    }

    @Override
    public List<BlogLink> selectAll() {
        BlogLink link = new BlogLink();
        link.setStatus(true);
        return linkMapper.selectList(link);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return linkMapper.deleteBatch(ids);
    }
}
