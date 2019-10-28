package com.nbclass.framework.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * GsonUtil
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
public class GsonUtil {

	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T fromJson(String str, Type type) {
		return gson.fromJson(str, type);
	}

	public static Object fromJson(Reader reader, Type type) {
		return gson.fromJson(reader, type);
	}

}
