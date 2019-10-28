package com.nbclass.framework.config;

import com.nbclass.framework.config.properties.ZbProperties;
import com.nbclass.framework.util.CoreConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * WebMvcConfig配置类-资源映射
 *
 * @version V1.0
 * @date 2019/10/24
 * @author nbclass
 */
@Configuration
@Order(1)
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String FILE_PROTOCOL = "file:///";

    @Autowired
    private  ZbProperties zbProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String workDir = FILE_PROTOCOL + zbProperties.getWorkDir();
        registry.addResourceHandler("/theme/*/static/**")
                .addResourceLocations(workDir + "theme/");
        registry.addResourceHandler("/file/**")
                .addResourceLocations(workDir + CoreConst.FILE_FOLDER);
    }

}
