package com.rbook.model;

import java.time.LocalDate;

public class Pair {

	private String name;
	private int totalNum;
	private boolean unread;
	private LocalDate date;
	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Pair [name=" + name + ", totalNum=" + totalNum + ", unread=" + unread + ", date=" + date + "]";
	}

	public Pair(String name, int totalNum, boolean unread, LocalDate date, String nickname) {
		super();
		this.name = name;
		this.totalNum = totalNum;
		this.unread = unread;
		this.date = date;
		this.nickname = nickname;
	}

	public void update(int num, boolean out, LocalDate date, boolean unread) {
		if (out) {
			this.totalNum = this.totalNum + num;
		} else {
			this.totalNum = this.totalNum - num;
		}
		if (date.isAfter(this.date)) {
			this.date = date;
		}
		this.unread = this.unread || unread;
	}

}
