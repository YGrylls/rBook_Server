package com.rbook.entity;

import java.time.LocalDate;

public class One2OneDebt implements IEntity {
	private int num;
	private LocalDate date;
	private String desc;
	private User start;
	private User end;

	public One2OneDebt(int num, LocalDate date, String desc, User start, User end) {

		this.num = num;
		this.date = date;
		this.desc = desc;
		this.start = start;
		this.end = end;
	}

}
