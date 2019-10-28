package com.nbclass.framework.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JwtProp
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-12
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProp {
    private String secret;
    private String issuer;
    private int expire;
}
