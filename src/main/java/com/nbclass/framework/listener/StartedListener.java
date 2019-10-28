package com.nbclass.framework.listener;

import com.nbclass.enums.CacheKeyPrefix;
import com.nbclass.framework.theme.ZbTheme;
import com.nbclass.framework.theme.ZbThemeForm;
import com.nbclass.framework.theme.ZbThemeSetting;
import com.nbclass.framework.config.properties.ZbProperties;
import com.nbclass.framework.util.CoreConst;
import com.nbclass.framework.util.FileUtil;
import com.nbclass.framework.util.GsonUtil;
import com.nbclass.service.RedisService;
import com.nbclass.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * StartedListener
 *
 * @version V1.0
 * @date 2019/10/23
 * @author nbclass
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    Environment environment;
    @Autowired
    private ZbProperties zbProperties;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ThemeService themeService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        //初始化主题
        this.initThemes();
        //初始化模板常量
        themeService.initThymeleafVars();
        //打印信息
        this.printStartInfo();
    }

    private void printStartInfo() {
        log.info("Blog started success : port : {}",environment.getProperty("server.port"));
    }

    private void initThemes() {
        try {
            log.info("Blog themes start init");
            String workThemeDir = zbProperties.getWorkDir() + "theme/";
            String themeClassPath = ResourceUtils.CLASSPATH_URL_PREFIX + CoreConst.THEME_FOLDER;
            URI themeUri = ResourceUtils.getURL(themeClassPath).toURI();
            boolean isJarEnv = "jar".equalsIgnoreCase(themeUri.getScheme());
            FileSystem fileSystem = isJarEnv? FileSystems.newFileSystem(themeUri, Collections.emptyMap()):null;
            Path sysSource = isJarEnv? fileSystem.getPath("/BOOT-INF/classes/" + CoreConst.THEME_FOLDER) : Paths.get(themeUri);
            Path userSource = Paths.get(workThemeDir);
            Map<String, ZbTheme> sysThemeMap = FileUtil.scanThemeFolder(sysSource);
            Map<String, ZbTheme> userThemeMap = FileUtil.scanThemeFolder(userSource);
            if (CollectionUtils.isEmpty(sysThemeMap)) {
                throw new RuntimeException("system theme does not exist");
            }
            //用户自定义主题目录和系统主题目录处理
            if (CollectionUtils.isEmpty(userThemeMap)) {
                FileUtil.copyFolder(sysSource, userSource);
                redisService.set(CacheKeyPrefix.THEMES.getPrefix(), GsonUtil.toJson(sysThemeMap));
            } else {
                sysThemeMap.forEach((k,v)->{
                    if(userThemeMap.get(k)==null){
                        try {
                            //系统主题不在用户自定义目录，则copy进用户自定义目录
                            Path systemThemePath = isJarEnv? fileSystem.getPath("/BOOT-INF/classes/" + CoreConst.THEME_FOLDER + k) : Paths.get(ResourceUtils.getURL(themeClassPath + k).toURI());
                            FileUtil.copyFolder(systemThemePath, Paths.get(workThemeDir+k));
                            userThemeMap.put(k,v);
                        } catch (IOException e) {
                            throw new RuntimeException("copy theme to user folder error:{}", e);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
                FileUtil.copyFolder(userSource, sysSource);
                redisService.set(CacheKeyPrefix.THEMES.getPrefix(),  GsonUtil.toJson(userThemeMap));
            }
            //当前主题处理
            String currentThemeJson = redisService.get(CacheKeyPrefix.CURRENT_THEME.getPrefix());
            if (null == currentThemeJson) {
                //第一次启动，初始化当前系统主题
                ZbTheme zbTheme = sysThemeMap.entrySet().iterator().next().getValue();;
                if(zbTheme.getSetFlag().equals(CoreConst.STATUS_VALID)){ //支持配置
                    //载入配置的form
                    ZbThemeSetting themeSetting = GsonUtil.fromJson(GsonUtil.toJson(zbTheme.getSettings()), ZbThemeSetting.class);
                    Map<String,Object> formMap = new LinkedHashMap<>();
                    for(ZbThemeForm themeForm:themeSetting.getForm()){
                        themeForm.setValue(themeForm.getDefaultValue());
                        formMap.put(themeForm.getName(),themeForm);
                    }
                    zbTheme.setForm(formMap);
                }
                redisService.set(CacheKeyPrefix.CURRENT_THEME.getPrefix(), GsonUtil.toJson(zbTheme));
            }
            log.info("Blog themes init success");
        }catch (Exception e){
            throw new RuntimeException("Blog themes init error", e);
        }
    }

}
