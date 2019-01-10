package com.rbook.common;

import com.rbook.util.IdentityNum;

public class SignupRequest {
	private String username;
	private String password;
	private String identity;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean signInCheck() {
		System.out.println("----------SignupReq------\n" + this + "\n------------\n");
		return null != username && null != password && nickname != null && username.length() <= 16
				&& password.length() <= 16 && nickname.length() <= 16 && identity != null && identity.length() == 18
				&& IdentityNum.checkValidation(identity);
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
		return "SignupRequest [username=" + username + ", password=" + password + ", identity=" + identity
				+ ", nickname=" + nickname + "]";
	}

}
