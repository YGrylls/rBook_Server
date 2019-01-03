package com.rbook.util;

import java.util.UUID;

public class UID {
	public static String generate() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

}
