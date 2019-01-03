package com.rbook.one2one;

import java.util.Arrays;

import com.rbook.common.IUserReq;
import com.rbook.common.UserHeader;

// for combine and delete request
public class One2OneSelectRequest implements IUserReq { // for delete and combine operation use
	private UserHeader uh;
	private String counter;

	public String getCounter() {
		return counter;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String[] idList;
	private String desc;

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "One2OneSelectRequest [uh=" + uh + ", counter=" + counter + ", idList=" + Arrays.toString(idList)
				+ ", desc=" + desc + "]";
	}

	public String getUsername() {
		return uh.getUsername();
	}

	public String getPassword() {
		return uh.getPassword();
	}

	public UserHeader getUh() {
		return uh;
	}

	public void setUh(UserHeader uh) {
		this.uh = uh;
	}

	public boolean checkValidate() {
		boolean uhValidate = false;
		boolean contentValidate = false;

		try {
			System.out.println("-------------------req-----------\n" + this + "\n");
			if (uh != null) {
				uhValidate = (getUsername().length() <= 16 && getPassword().length() <= 16);
			}
			contentValidate = (counter.length() <= 16 && idList != null) && idList.length <= 100
					&& desc.length() <= 140;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return uhValidate && contentValidate;
	}

}
