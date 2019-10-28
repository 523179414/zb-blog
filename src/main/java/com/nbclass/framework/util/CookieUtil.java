package com.nbclass.framework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * CookieUtil
 *
 * @author nbclass
 * @version V1.0
 * @date 2019-10-12
 */
public class CookieUtil {

    public static String getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name).getValue();
        } else {
            return null;
        }
    }

    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        response.addCookie(cookie);
        return response;
    }
}
