package com.zlp.util;

import java.util.UUID;

/**
 * 
 * @author zhoulongpeng
 * @date   2016-02-04
 *
 */
public class UuidUtil {

	public static String getUUID32() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getUUID64() {
		return UUID.randomUUID().toString();
	}
	
}
