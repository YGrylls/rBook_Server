package com.rbook.entity;

import java.time.LocalDate;

public class One2OneDebt implements IEntity {
	private long id;
	private int num;
	private LocalDate date;
	private String desc;
	private int status;						//0-normal , 1-wait to add, 2-wait to combine, 3-wait to delete
	private User start;
	private User end;

	public One2OneDebt(long id, int num, int status, LocalDate date, String desc, User start, User end) {
		this.id = id;
		this.num = num;
		this.status = status;
		this.date = date;
		this.desc = desc;
		this.start = start;
		this.end = end;
	}

}
