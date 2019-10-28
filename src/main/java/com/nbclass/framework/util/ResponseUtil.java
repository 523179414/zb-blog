package com.nbclass.framework.util;

import com.nbclass.vo.ResponseVo;

/**
 * ResponseUtil
 *
 * @version V1.0
 * @date 2019/10/11
 * @author nbclass
 */
public class ResponseUtil {

	public static ResponseVo success(){
		return vo(CoreConst.SUCCESS_CODE,null,null);
	}

	public static ResponseVo success(String msg){
		return vo(CoreConst.SUCCESS_CODE, msg,null);
	}

	public static ResponseVo success(String msg, Object data){
		return vo(CoreConst.SUCCESS_CODE, msg, data);
	}

	public static ResponseVo error(){
		return vo(CoreConst.FAIL_CODE,null,null);
	}

	public static ResponseVo error(String msg){
		return vo(CoreConst.FAIL_CODE, msg,null);
	}

	public static ResponseVo error(String msg, Object data){
		return vo(CoreConst.FAIL_CODE, msg,data);
	}

	public static ResponseVo vo(Integer status, String message, Object data) {
		return new ResponseVo<>(status, message, data);
	}



}
