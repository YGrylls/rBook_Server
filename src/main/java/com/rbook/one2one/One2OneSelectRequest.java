package com.rbook.one2one;

import java.util.Arrays;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class One2OneSelectRequest implements IUserReq { // for delete and combine operation use
	private UserHeader uh;
	private long[] idList;
	private String desc;

	public long[] getIdList() {
		return idList;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "One2OneSelectRequest [idList=" + Arrays.toString(idList) + ", desc=" + desc + "]";
	}

	public String getUsername() {
		return uh.getUsername();
	}

	public String getPassword() {
		return uh.getPassword();
	}

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
	}

}
