package com.rbook.model;

import java.time.LocalDate;

import com.rbook.entity.Group;

public class GroupInfo {
	private String uuid;
	private String name;
	private int status; // 0 unconfirmd, 1 confirmed, 2 closed
	private LocalDate updateTime;
	private int member;
	private boolean myConfirm;

	public String getUuid() {
		return uuid;
	}

	public GroupInfo(Group group, int mem, boolean myConfirm) {
		this.uuid = group.getUuid();
		this.name = group.getName();
		this.status = group.getStatus();
		this.updateTime = group.getConfirmTime();
		this.member = mem;
		this.myConfirm = myConfirm;

	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDate updateTime) {
		this.updateTime = updateTime;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public boolean isMyConfirm() {
		return myConfirm;
	}

	public void setMyConfirm(boolean myConfirm) {
		this.myConfirm = myConfirm;
	}

	@Override
	public String toString() {
		return "GroupInfo [uuid=" + uuid + ", name=" + name + ", status=" + status + ", updateTime=" + updateTime
				+ ", member=" + member + ", myConfirm=" + myConfirm + "]";
	}

}
