package com.rbook.common;

public class ConstructRequest implements IUserReq { // construct a new pair
	private UserHeader uh;
	private String counterName;
	private String desc;

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ConstructRequest [counterName=" + counterName + ", desc=" + desc + "]";
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

	@Override
	public boolean checkValidate() {
		boolean uhValidate = false;
		boolean contentValidate = false;
		if (uh != null) {
			uhValidate = (getUsername().length() <= 16 && getPassword().length() <= 16);
		}
		contentValidate = counterName.length() <= 16 && desc.length() <= 140;
		return uhValidate && contentValidate;
	}

}
