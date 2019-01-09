package com.rbook.mapperObject;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class GroupNodeBoolean {

	private GroupNode group;
	private boolean confirm;

	public GroupNode getGroup() {
		return group;
	}

	public void setGroup(GroupNode group) {
		this.group = group;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

}
