package com.rbook.one2one;

public class One2OneDebtRequest { // add new pair debt
	private String counterName;
	private int num;
	private String desc;
	private Boolean direct; // true if money out, false if money in

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getDirect() {
		return direct;
	}

	public void setDirect(Boolean direct) {
		this.direct = direct;
	}

	@Override
	public String toString() {
		return "One2OneDebtRequest [counterName=" + counterName + ", num=" + num + ", desc=" + desc + ", direct="
				+ direct + "]";
	}

}
