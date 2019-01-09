package com.rbook.model;

public class GroupDebtPair {
	private String start;
	private String end;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public GroupDebtPair(String start, String end) {

		this.start = start;
		this.end = end;
	}

	public boolean changePosition() {
		if (start.compareTo(end) < 0) {
			String temp = start;
			start = end;
			end = temp;
			return true;
		} else
			return false;
	}

}
