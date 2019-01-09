package com.rbook.model;

import com.rbook.entity.GroupRes;
import com.rbook.entity.User;

public class GroupResInfo implements Comparable<GroupResInfo> {

	private String counter;
	private String counterNickname;
	private int num;
	private boolean myStatus;
	private boolean counterStatus;
	private String uuid;

	public GroupResInfo(GroupRes res, User counter, String username, boolean ifStart) {
		this.counter = counter.getUsername();
		this.uuid = res.getUuid();
		this.counterNickname = counter.getNickname();
		if (ifStart) {
			myStatus = res.isStart();
			counterStatus = res.isEnd();
			num = res.getNum();
		} else {
			myStatus = res.isEnd();
			counterStatus = res.isStart();
			num = -res.getNum();
		}
	}

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "GroupResInfo [counter=" + counter + ", counterNickname=" + counterNickname + ", num=" + num
				+ ", myStatus=" + myStatus + ", counterStatus=" + counterStatus + ", uuid=" + uuid + "]";
	}

	@Override
	public int compareTo(GroupResInfo o) {
		// TODO Auto-generated method stub
		if (myStatus && !o.myStatus) {
			return 1;
		}
		if (!myStatus && o.myStatus) {
			return -1;
		}
		if (counterStatus && !o.counterStatus) {
			return 1;
		}
		if (!counterStatus && o.counterStatus) {
			return -1;
		}

		return 0;
	}

}
