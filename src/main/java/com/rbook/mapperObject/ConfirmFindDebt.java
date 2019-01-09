package com.rbook.mapperObject;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class ConfirmFindDebt {
	private String start;
	private GroupDebtNode groupDebt;
	private String end;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public GroupDebtNode getGroupDebt() {
		return groupDebt;
	}

	public void setGroupDebt(GroupDebtNode groupDebt) {
		this.groupDebt = groupDebt;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "ConifrmFindDebt [start=" + start + ", groupDebt=" + groupDebt + ", end=" + end + "]";
	}

}
