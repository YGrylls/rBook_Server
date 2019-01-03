package com.rbook.group;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class CreateGroupRequest implements IUserReq {

	private UserHeader uh;
	private String groupName;

	@Override
	public String getUsername() {
		return uh.getUsername();
	}

	@Override
	public String getPassword() {
		return uh.getPassword();
	}

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "CreateGroupRequest [uh=" + uh + ", groupName=" + groupName + "]";
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
			contentValidate = groupName.length() <= 40;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return uhValidate && contentValidate;
	}

}
