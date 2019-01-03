package com.rbook.group;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class GroupRequest implements IUserReq {

	private UserHeader uh;
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
	public boolean checkValidate() {
		boolean uhValidate = false;
		boolean contentValidate = false;
		try {
			System.out.println("-------------------req-----------\n" + this + "\n");
			if (uh != null) {
				uhValidate = (getUsername().length() <= 16 && getPassword().length() <= 16);
			}
			contentValidate = uuid != null && uuid.length() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return uhValidate && contentValidate;
	}

}
