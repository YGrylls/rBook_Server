package com.rbook.one2one;

public class ConstructRequest { // construct a new pair
	private String counterName;
	private String desc;

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ConstructRequest [counterName=" + counterName + ", desc=" + desc + "]";
	}

}
