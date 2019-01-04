package com.rbook.group;

import java.util.Arrays;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

public class ProposeGroupDebtRequest implements IUserReq {

	private UserHeader uh;
	private String guid;
	private String desc;
	private int num;
	private String[] targetList;

	public String getDesc() {
		return desc;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String[] getTargetList() {
		return targetList;
	}

	public void setTargetList(String[] targetList) {
		this.targetList = targetList;
	}

	@Override
	public String toString() {
		return "ProposeGroupDebtRequest [uh=" + uh + ", guid=" + guid + ", desc=" + desc + ", num=" + num
				+ ", targetList=" + Arrays.toString(targetList) + "]";
	}

	@Override
	public String getUsername() {
		return uh.getUsername();
	}

	@Override
	public String getPassword() {
		return uh.getPassword();
	}

	@Override
	public boolean checkValidate() {
		boolean uhValidate = false;
		boolean contentValidate = false;
		try {
			System.out.println("-------------------req-----------\n" + this + "\n");
			if (uh != null) {
				uhValidate = (getUsername().length() <= 16 && getPassword().length() <= 16);
			}

			if (guid == null)
				return false;
			for (String u : targetList) {
				if (u.equals(getUsername())) {
					return false;
				}
			}
			contentValidate = (desc.length() <= 140 && targetList.length <= 19 && num >= 0 && num <= 1000000);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return uhValidate && contentValidate;
	}

}
