package com.rbook.entity;

public class LoopCheck implements IEntity {

	private String start;
	private String end;
	private int scale;

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

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public LoopCheck(String start, String end, int scale) {
		super();
		this.start = start;
		this.end = end;
		this.scale = scale;
	}

	@Override
	public String toString() {
		return "LoopCheck [start=" + start + ", end=" + end + ", scale=" + scale + "]";
	}

}
