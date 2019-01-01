package com.rbook.DAO;

import java.time.LocalDate;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.rbook.entity.One2OneDebt;

@RelationshipEntity(type = "DEBT")
public class One2OneDebtRel implements IDataRes {

	@Id
	@GeneratedValue
	private long graphId;

	@Property("number")
	private int number;

	@Property("date")
	private LocalDate date;

	@Property("status")
	private int status;

	@Property("desc")
	private String desc;

	@StartNode
	private UserNode start;

	@EndNode
	private UserNode end;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	@Override
	public String toString() {
		return "One2OneDebtRel [number=" + number + ", date=" + date + ", desc=" + desc + "]";
	}

	@Override
	public One2OneDebt toEntity() {
		// TODO Auto-generated method stub

		return new One2OneDebt(graphId, number, status, date, desc, start.toEntity(), end.toEntity());
	}

}
