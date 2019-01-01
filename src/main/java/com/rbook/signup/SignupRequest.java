package com.rbook.signup;

import com.rbook.util.IdentityNum;

public class SignupRequest {
	private String username;
	private String password;
	private String identity;

	public boolean signInCheck() {
		return null != username && null != password && username.length() <= 16 && password.length() <= 16
				&& identity != null && identity.length() == 18 && IdentityNum.checkValidation(identity);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity() {
		return identity;

	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + "]";
	}

}
