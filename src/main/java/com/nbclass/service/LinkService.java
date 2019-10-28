package com.nbclass.service;

import com.nbclass.model.BlogLink;

import java.util.List;

public interface LinkService {

    List<BlogLink> selectList(BlogLink link);

    List<BlogLink> selectAll();

    int deleteBatch(Integer[] ids);


}
