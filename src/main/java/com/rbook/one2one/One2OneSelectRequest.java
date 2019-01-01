package com.rbook.one2one;

import java.util.Arrays;

public class One2OneSelectRequest { // for delete and combine operation use
	private String[] idList;
	private String desc;

	public String[] getIdList() {
		return idList;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "One2OneSelectRequest [idList=" + Arrays.toString(idList) + ", desc=" + desc + "]";
	}

}
