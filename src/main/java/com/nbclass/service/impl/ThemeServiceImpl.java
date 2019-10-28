package com.nbclass.service.impl;

import com.nbclass.enums.BlogConfigKey;
import com.nbclass.enums.CacheKeyPrefix;
import com.nbclass.framework.theme.ZbTheme;
import com.nbclass.framework.util.CoreConst;
import com.nbclass.framework.util.GsonUtil;
import com.nbclass.service.ConfigService;
import com.nbclass.service.RedisService;
import com.nbclass.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    private ConfigService configService;
    @Autowired
    private RedisService redisService;

    @Override
    public void useTheme(ZbTheme theme) {
        redisService.set(CacheKeyPrefix.CURRENT_THEME.getPrefix(), theme);;
    }

    @Override
    public ZbTheme selectCurrent() {
        String settings = redisService.get(CacheKeyPrefix.CURRENT_THEME.getPrefix());
        return GsonUtil.fromJson(settings, ZbTheme.class);
    }

    @Override
    public void initThymeleafVars() {
        if (thymeleafViewResolver != null) {
            ZbTheme currentTheme = selectCurrent();
            Map<String, Object> vars = new HashMap<>();
            String cdn = configService.selectAll().get(BlogConfigKey.SITE_CDN.getValue());
            String staticPath = String.format("%s/theme/%s/static",cdn!=null ? cdn : "",currentTheme.getId());
            vars.put("static", staticPath);
            vars.put("currentTheme", currentTheme);
            CoreConst.currentTheme=currentTheme.getId();
            thymeleafViewResolver.setStaticVariables(vars);
        }
    }

    @Override
    public void updateSettings(String settingsJson) {
        //TODO:修改主题设置时，需要检查当前主题CURRENT_THEME，和缓存中的主题THEME_XXX,赋值form属性;

        //重新初始化模板常量
        initThymeleafVars();
    }

}
