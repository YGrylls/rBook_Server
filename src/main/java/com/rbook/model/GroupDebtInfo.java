package com.rbook.model;

import java.time.LocalDate;

public class GroupDebtInfo {

	private String uuid;
	private String desc;
	private int num;
	private LocalDate time;
	private int involve; // 0 not involved, 1 proposal, 2 targeted
	private String proposeName;
	private String proposeNickName;

}
