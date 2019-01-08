package com.rbook.model;

import java.time.LocalDate;

import com.rbook.entity.GroupDebt;
import com.rbook.entity.User;

public class GroupDebtInfo {

	private String uuid;
	private String desc;
	private int num;
	private LocalDate time;
	private boolean propose; // true if user proposes it , false if not
	private String proposeName;
	private String proposeNickName;

	public String getUuid() {
		return uuid;
	}

	public GroupDebtInfo(GroupDebt debt, User proposer, String username) {
		uuid = debt.getUuid();
		desc = debt.getDesc();
		num = debt.getNum();
		time = debt.getTime();
		propose = proposer.getUsername() == username;
		proposeName = proposer.getUsername();
		proposeNickName = proposer.getNickname();
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

	public boolean isPropose() {
		return propose;
	}

	public void setPropose(boolean propose) {
		this.propose = propose;
	}

	@Override
	public String toString() {
		return "GroupDebtInfo [uuid=" + uuid + ", desc=" + desc + ", num=" + num + ", time=" + time + ", propose="
				+ propose + ", proposeName=" + proposeName + ", proposeNickName=" + proposeNickName + "]";
	}

}
