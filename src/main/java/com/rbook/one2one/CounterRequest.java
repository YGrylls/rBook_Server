package com.rbook.one2one;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class CounterRequest implements IUserReq {
	private UserHeader uh;
	private String counter;

	@Override
	public String toString() {
		return "BrowseRequest [uh=" + uh + ", counter=" + counter + "]";
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

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

}
