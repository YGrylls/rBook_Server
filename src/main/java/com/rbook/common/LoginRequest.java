package com.rbook.common;

public class LoginRequest {
	private String username;
	private String password;

	public boolean signInCheck() {
		System.out.println("---------signinReq---\n" + this + "\n---------\n");
		return null != username && null != password && username.length() <= 16 && password.length() <= 16;
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

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + password + "]";
	}
}
