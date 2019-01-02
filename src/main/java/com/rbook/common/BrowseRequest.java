package com.rbook.common;
// for browse pair list and group list in brief

public class BrowseRequest implements IUserReq {

	private UserHeader uh;

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
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
		try {
			System.out.println("-------------------req-----------\n" + this + "\n");
			if (uh != null) {
				uhValidate = (getUsername().length() <= 16 && getPassword().length() <= 16);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return uhValidate;

	}
}
