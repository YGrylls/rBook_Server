package com.rbook.entity;

public class User implements IEntity {

	@Override
	public String toString() {
		return "User [username=" + username + ", totalAccount=" + totalAccount + ", rantStatus=" + rankStatus + "]";
	}

	public User(String username, int totalAccount, int rankStatus, String nickname) {
		this.username = username;
		this.totalAccount = totalAccount;
		this.rankStatus = rankStatus;
		this.nickname = nickname;
	}

	private String username;
	private int totalAccount;
	private int rankStatus;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalAccount() {
		return totalAccount;
	}

	public void setTotalAccount(int totalAccount) {
		this.totalAccount = totalAccount;
	}

	public int getRankStatus() {
		return rankStatus;
	}

	public void setRankStatus(int rankStatus) {
		this.rankStatus = rankStatus;
	}

}
