package com.rbook.model;

import java.time.LocalDate;

public class GroupDebtInfo {

	private String uuid;
	private String desc;
	private int num;
	private LocalDate time;
	private int involve; // 0 not involved, 1 proposal, 2 targeted
	private String proposeName;
	private String proposeNickName;

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

	public int getInvolve() {
		return involve;
	}

	public void setInvolve(int involve) {
		this.involve = involve;
	}

	public String getProposeName() {
		return proposeName;
	}

	public void setProposeName(String proposeName) {
		this.proposeName = proposeName;
	}

	public String getProposeNickName() {
		return proposeNickName;
	}

	public void setProposeNickName(String proposeNickName) {
		this.proposeNickName = proposeNickName;
	}

	@Override
	public String toString() {
		return "GroupDebtInfo [uuid=" + uuid + ", desc=" + desc + ", num=" + num + ", time=" + time + ", involve="
				+ involve + ", proposeName=" + proposeName + ", proposeNickName=" + proposeNickName + "]";
	}

}
