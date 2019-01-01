package com.rbook.one2one;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class One2OneDebtRequest implements IUserReq { // add new pair debt
	private UserHeader uh;
	private String counterName;
	private int num;
	private String desc;
	private Boolean direct; // true if money out, false if money in

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getDirect() {
		return direct;
	}

	public void setDirect(Boolean direct) {
		this.direct = direct;
	}

	@Override
	public String toString() {
		return "One2OneDebtRequest [counterName=" + counterName + ", num=" + num + ", desc=" + desc + ", direct="
				+ direct + "]";
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
