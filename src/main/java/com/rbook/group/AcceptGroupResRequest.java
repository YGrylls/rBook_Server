package com.rbook.group;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class AcceptGroupResRequest implements IUserReq {

	private UserHeader uh;
	private String uuid;
	private boolean ifStart;

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "AcceptGroupResRequest [uh=" + uh + ", uuid=" + uuid + ", ifStart=" + ifStart + "]";
	}

	public boolean isIfStart() {
		return ifStart;
	}

	public void setIfStart(boolean ifStart) {
		this.ifStart = ifStart;
	}

	@Override
	public String getUsername() {
		return uh.getUsername();
	}

	@Override
	public String getPassword() {
		return uh.getPassword();
	}

	@Override
	public boolean checkValidate() {
		boolean uhValidate = false;
		boolean contentValidate = false;
		try {
			System.out.println("-------------------req-----------\n" + this + "\n");
			if (uh != null) {
				uhValidate = (getUsername().length() <= 16 && getPassword().length() <= 16);
			}
			contentValidate = uuid != null && uuid.length() >= 32;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return uhValidate && contentValidate;
	}

}
