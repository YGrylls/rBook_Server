package com.rbook.mapperObject;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class FindGroup {
	private GroupNode group;
	private int member;

	public GroupNode getGroup() {
		return group;
	}

	public void setGroup(GroupNode group) {
		this.group = group;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "FindGroup [group=" + group + ", member=" + member + "]";
	}

}
