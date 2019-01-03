package com.rbook.entity;

public class GroupRes implements IEntity {
	private String uuid;
	private int num;
	private boolean start;
	private boolean end;
	private boolean status;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GroupRes [uuid=" + uuid + ", num=" + num + ", start=" + start + ", end=" + end + ", status=" + status
				+ "]";
	}
}
