package com.nbclass.service.impl;

import com.nbclass.mapper.ConfigMapper;
import com.nbclass.model.BlogConfig;
import com.nbclass.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ConfigServiceImpl
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-18
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Map<String, String> selectAll() {
        List<BlogConfig> sysConfigs = configMapper.selectAll();
        Map<String,String>  map= new HashMap<String,String>(sysConfigs.size());
        for (BlogConfig config : sysConfigs){
            map.put(config.getSysKey(),config.getSysValue());
        }
        return map;
    }

    @Override
    public int updateByKey(String key,String value) {
        BlogConfig config = new BlogConfig();
        config.setSysKey(key);
        config = configMapper.selectOne(config);
        config.setSysValue(value);
        return configMapper.updateByPrimaryKeySelective(config);
    }
}
