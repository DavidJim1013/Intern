package com.util;

import java.util.UUID;

public class UUIDTools {

	public UUIDTools() {
		// TODO Auto-generated constructor stub
	}

	// 随机生成主键 返回一个6位的字符串
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6);
	}

}
