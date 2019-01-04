package com.rbook.model;

public class GroupResInfo {

	private String counter;
	private String counterNickname;
	private int num;
	private boolean myStatus;
	private boolean counterStatus;

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public String getCounterNickname() {
		return counterNickname;
	}

	public void setCounterNickname(String counterNickname) {
		this.counterNickname = counterNickname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isMyStatus() {
		return myStatus;
	}

	public void setMyStatus(boolean myStatus) {
		this.myStatus = myStatus;
	}

	public boolean isCounterStatus() {
		return counterStatus;
	}

	public void setCounterStatus(boolean counterStatus) {
		this.counterStatus = counterStatus;
	}

	@Override
	public String toString() {
		return "GroupResInfo [counter=" + counter + ", counterNickname=" + counterNickname + ", num=" + num
				+ ", myStatus=" + myStatus + ", counterStatus=" + counterStatus + "]";
	}

}
