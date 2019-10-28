package com.nbclass.framework.util;
/**
 * PageUtil
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
public class PageUtil {
    public static Integer getPageNo(Integer limit,Integer offset){
        return offset==0 ? 1 : offset / limit + 1;
    }
}
