package com.nbclass.framework.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.support.BindingAwareModelMap;

/**
 * CacheKeyUtil
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
public class CacheKeyUtil {

    /**
     * 获取方法参数组成的key
     *
     * @param params
     *         参数数组
     */
    public static String getMethodParamsKey(Object... params) {
        if (StringUtils.isEmpty(params)) {
            return "";
        }
        StringBuilder key = new StringBuilder("(");
        for (Object obj : params) {
            if (obj.getClass().equals(BindingAwareModelMap.class)) {
                continue;
            }
            key.append(GsonUtil.toJson(obj).replaceAll("\"", "'"));
        }
        key.append(")");
        return key.toString();
    }

}
