package com.rbook.group;

import java.util.Arrays;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class DeleteGroupDebtRequest implements IUserReq {

	private UserHeader uh;
	private String[] list;
	private String uuid;

	@Override
	public String getUsername() {
		return uh.getUsername();
	}

	@Override
	public String getPassword() {
		return uh.getPassword();
	}

	@Override
	public String toString() {
		return "DeleteGroupDebtRequest [uh=" + uh + ", list=" + Arrays.toString(list) + ", uuid=" + uuid + "]";
	}

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
			contentValidate = list.length > 0 && uuid != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return uhValidate && contentValidate;
	}

}
