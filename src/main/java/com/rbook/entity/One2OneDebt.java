package com.rbook.entity;

import java.time.LocalDate;

public class One2OneDebt implements IEntity {
	private long id;
	private int num;
	private LocalDate date;
	private String desc;
	private int status; // 0-normal , 1-wait to add, 2-wait to delete, 3-combine to delete, 4-combine to
						// add
	private String start;
	private String end;
	private boolean proposal; // true for starter, false for ender

	public One2OneDebt(long id, int num, int status, LocalDate date, String desc, String start, String end,
			boolean proposal) {
		this.id = id;
		this.num = num;
		this.status = status;
		this.date = date;
		this.desc = desc;
		this.start = start;
		this.end = end;
		this.proposal = proposal;
	}

	public boolean isProposal() {
		return proposal;
	}

	public void setProposal(boolean proposal) {
		this.proposal = proposal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

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

	@Override
	public String toString() {
		return "One2OneDebt [id=" + id + ", num=" + num + ", date=" + date + ", desc=" + desc + ", status=" + status
				+ ", start=" + start + ", end=" + end + ", proposal=" + proposal + "]";
	}

}
