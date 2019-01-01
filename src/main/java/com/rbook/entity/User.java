package com.rbook.entity;

public class User implements IEntity {

	@Override
	public String toString() {
		return "User [username=" + username + ", totalAccount=" + totalAccount + ", rantStatus=" + rankStatus + "]";
	}

	public User(String username, int totalAccount, int rankStatus) {
		this.username = username;
		this.totalAccount = totalAccount;
		this.rankStatus = rankStatus;
	}

	private String username;
	private int totalAccount;
	private int rankStatus;

}
