package com.rbook.util;

import java.util.regex.Pattern;

public class IdentityNum {
	public static Boolean checkValidation(String idNum) {
		String idPtn = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d.";

		return Pattern.matches(idPtn, idNum);

	}

}
