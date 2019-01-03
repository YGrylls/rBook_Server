package com.rbook.DAO;

import java.time.LocalDate;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.rbook.entity.One2OneDebt;

@RelationshipEntity(type = "ONE2ONE_DEBT")
public class One2OneDebtRel implements IDataRes {

	public One2OneDebtRel() {
	}

	@Id
	@GeneratedValue
	private long graphId;

	@Property("uuid")
	private String uuid;

	@Property("number")
	private int number;

	@Property("date")
	private LocalDate date;

	@Property("status")
	private int status;

	@Property("desc")
	private String desc;

	@Property("proposal")
	private boolean proposal; // true for starter ,false for ender

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
		return "One2OneDebtRel [graphId=" + graphId + ", uuid=" + uuid + ", number=" + number + ", date=" + date
				+ ", status=" + status + ", desc=" + desc + ", proposal=" + proposal + ", start=" + start + ", end="
				+ end + "]";
	}

	@Override
	public One2OneDebt toEntity() {
		// TODO Auto-generated method stub

		return new One2OneDebt(uuid, number, status, date, desc, start.getUsername(), end.getUsername(), proposal);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getGraphId() {
		return graphId;
	}

	public void setGraphId(long graphId) {
		this.graphId = graphId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isProposal() {
		return proposal;
	}

	public void setProposal(boolean proposal) {
		this.proposal = proposal;
	}

	public UserNode getStart() {
		return start;
	}

	public void setStart(UserNode start) {
		this.start = start;
	}

	public UserNode getEnd() {
		return end;
	}

	public void setEnd(UserNode end) {
		this.end = end;
	}

}
