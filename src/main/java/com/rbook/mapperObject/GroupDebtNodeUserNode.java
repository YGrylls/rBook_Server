package com.rbook.mapperObject;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class GroupDebtNodeUserNode {

	private GroupDebtNode debt;
	private UserNode user;

	public GroupDebtNode getDebt() {
		return debt;
	}

	public void setDebt(GroupDebtNode debt) {
		this.debt = debt;
	}

	public UserNode getUser() {
		return user;
	}

	public void setUser(UserNode user) {
		this.user = user;
	}

}
