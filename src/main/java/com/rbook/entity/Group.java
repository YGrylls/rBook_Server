package com.rbook.entity;

import java.time.LocalDate;

public class Group implements IEntity {
	private String uuid;
	private String name;
	private int status; // 0 unconfirmd, 1 confirmed, 2 closed
	private LocalDate confirmTime;// if unconfirmed, it's create time; if confirmed, it's confirm time

	public String getUuid() {
		return uuid;
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

	public LocalDate getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(LocalDate confirmTime) {
		this.confirmTime = confirmTime;
	}

	@Override
	public String toString() {
		return "Group [uuid=" + uuid + ", name=" + name + ", status=" + status + ", confirmTime=" + confirmTime + "]";
	}

	public Group(String uuid, String name, int status, LocalDate confirmTime) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.status = status;
		this.confirmTime = confirmTime;
	}

}
