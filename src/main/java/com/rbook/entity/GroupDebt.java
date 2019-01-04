package com.rbook.entity;

import java.time.LocalDate;

public class GroupDebt implements IEntity {
	private String uuid;
	private String desc;
	private int num;
	private LocalDate time;

	public GroupDebt(String uuid, String desc, int num, LocalDate time) {
		super();
		this.uuid = uuid;
		this.desc = desc;
		this.num = num;
		this.time = time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public LocalDate getTime() {
		return time;
	}

	public void setTime(LocalDate time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "GroupDebt [uuid=" + uuid + ", desc=" + desc + ", num=" + num + ", time=" + time + "]";
	}

}
